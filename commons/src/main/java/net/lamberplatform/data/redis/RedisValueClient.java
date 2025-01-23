package net.lamberplatform.data.redis;

import com.google.common.annotations.VisibleForTesting;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.ValueOperations;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Optional;

/**
 * DATE: 2025/1/23
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Getter(AccessLevel.PROTECTED)
@Accessors(fluent = true)
public abstract class RedisValueClient<T> {

    private AnyRedisTemplate<T> redisTemplate;
    private ValueOperations<String, T> valueOperations;

    @Resource
    private void setValueOperations(RedisConnectionFactory factory) {
        this.redisTemplate = new AnyRedisTemplate<>(factory);
        this.valueOperations = this.redisTemplate.opsForValue();
    }

    private Class<?> valueTypeClazz;
    private String valueTypeName;
    private RedisKey keyAnnotation;
    private RedisId idAnnotation;
    private Field idAnnotationField;
    private TimeToLive timeToLiveAnnotation;
    private Field timeToLiveAnnotationField;
    private FieldValueResolver<String> idResolver;
    private FieldValueResolver<Long> timeToLiveResolver;

    @SneakyThrows
    private Long resolveTimeToLive(T entity) {
        return timeToLiveResolver.resolve(entity);
    }

    @SneakyThrows
    private String resolveId(T entity) {
        return idResolver.resolve(entity);
    }

    private String makeKey(String id) {
        return "%s:%s".formatted(keyAnnotation.value(), id);
    }

    public Optional<T> read(T entity) {
        return readById(resolveId(entity));
    }

    public Optional<T> readById(String id) {
        String key = makeKey(id);
        return Optional.ofNullable(valueOperations.get(key));
    }

    public T write(T entity) {
        String key = makeKey(resolveId(entity));
        if (timeToLiveAnnotationField == null) {
            valueOperations.set(key, entity);
        } else {
            Long ttl = resolveTimeToLive(entity);
            if (ttl == null) {
                throw new IllegalArgumentException("TimeToLive cannot be null.");
            }
            valueOperations.set(key, entity, ttl, timeToLiveAnnotation.unit());
        }
        return entity;
    }

    public void delete(T entity) {
        deleteById(resolveId(entity));
    }

    public void deleteById(String id) {
        String key = makeKey(id);
        redisTemplate.delete(key);
    }

    @VisibleForTesting
    @PostConstruct
    protected void makeMetadataAfterProperties() throws Exception {
        // 查找RedisValueClient定义
        Class<?> clientTypeClazz = this.getClass();
        while (clientTypeClazz.getSuperclass() != RedisValueClient.class) {
            clientTypeClazz = clientTypeClazz.getSuperclass();
        }
        // 获取实际value类型相关信息
        this.valueTypeName = ((ParameterizedType) clientTypeClazz.getGenericSuperclass())
            .getActualTypeArguments()[0]
            .getTypeName();
        this.valueTypeClazz = Class.forName(this.valueTypeName);
        // 解析关键字段信息
        this.resolveKeyFields();
        this.checkIdAnnotationField();
    }

    private void resolveKeyFields() throws Exception {

        Class<?> currentModelTypeClazz = this.valueTypeClazz;

        if (!currentModelTypeClazz.isAnnotationPresent(RedisKey.class)) {
            String message = "RedisModelPO impl: '%s' is not annotated with @RedisKey"
                .formatted(
                    currentModelTypeClazz.getName()
                );
            throw new BeanInitializationException(message);
        }
        this.keyAnnotation = currentModelTypeClazz.getAnnotation(RedisKey.class);

        while (currentModelTypeClazz != Object.class) {
            for (Field field : currentModelTypeClazz.getDeclaredFields()) {
                resolveIdAnnotationField(field);
                resolveTimeToLiveAnnotationField(field);
            }
            currentModelTypeClazz = currentModelTypeClazz.getSuperclass();
        }
    }

    private void resolveIdAnnotationField(Field field) throws Exception {
        if (field.isAnnotationPresent(RedisId.class)) {
            if (this.idAnnotationField != null) {
                String message = "Duplicate @RedisId annotation on field at model: '%s'"
                    .formatted(
                        this.valueTypeName
                    );
                throw new BeanInitializationException(message);
            }
            this.idAnnotation = field.getAnnotation(RedisId.class);
            this.idAnnotationField = field;
            field.setAccessible(true);
            this.idResolver = o -> (String) field.get(o);
        }
    }

    private void resolveTimeToLiveAnnotationField(Field field) throws Exception {
        if (field.isAnnotationPresent(TimeToLive.class)) {
            if (this.timeToLiveAnnotationField != null) {
                String message = "Duplicate @TimeToLive annotation on field at model: '%s'"
                    .formatted(
                        this.valueTypeName
                    );
                throw new BeanInitializationException(message);
            }
            this.timeToLiveAnnotation = field.getAnnotation(TimeToLive.class);
            this.timeToLiveAnnotationField = field;
            field.setAccessible(true);
            this.timeToLiveResolver = o -> (Long) field.get(o);
        }
    }

    private void checkIdAnnotationField() {
        if (idAnnotationField == null) {
            String message = "RedisValueClient impl: '%s' is not annotated with @RedisId"
                .formatted(
                    this.valueTypeName
                );
            throw new BeanInitializationException(message);
        }
    }

    public interface FieldValueResolver<T> {
        T resolve(Object target) throws Exception;
    }
}
