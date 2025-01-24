package net.lamberplatform.data.redis;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * DATE: 2025/1/23
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
public class AnyRedisTemplate<T> extends RedisTemplate<String, T> {

    public AnyRedisTemplate(ConfigurableRedisSerializer keySerializer) {
        setKeySerializer(keySerializer);
        setHashKeySerializer(RedisSerializer.string());
        setValueSerializer(RedisSerializer.java());
        setHashValueSerializer(RedisSerializer.java());
    }

    public AnyRedisTemplate(RedisConnectionFactory factory, ConfigurableRedisSerializer keySerializer) {
        this(keySerializer);
        setConnectionFactory(factory);
        afterPropertiesSet();
    }
}
