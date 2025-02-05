package net.lamberplatform.data.redis;

import net.lamberplatform.model.bo.TimeToLiveRedisBO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serial;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * DATE: 2025/1/23
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@ExtendWith(MockitoExtension.class)
public class RedisDAOTest {

    @InjectMocks
    private TestRedisDAO client;

    @Mock
    private AnyRedisTemplate<TestRedisBO> redisTemplate;

    @Mock
    private ValueOperations<String, TestRedisBO> operations;

    @BeforeEach
    public void invokeRedisTemplateOperations() throws Exception {
        Class<?> clientTypeClazz = RedisDAO.class;
        Field rt = clientTypeClazz.getDeclaredField("redisTemplate");
        rt.setAccessible(true);
        rt.set(client, redisTemplate);
        Field ops = clientTypeClazz.getDeclaredField("valueOperations");
        ops.setAccessible(true);
        ops.set(client, operations);
    }

    @Test
    public void makeMetadataAfterProperties() throws Exception {
        Class<?> clientTypeClazz = RedisDAO.class;
        client.makeMetadataAfterProperties();
        Field valueTypeClazz = clientTypeClazz.getDeclaredField("valueTypeClazz");
        valueTypeClazz.setAccessible(true);
        assertEquals(TestRedisBO.class, valueTypeClazz.get(client));
        Field valueTypeName = clientTypeClazz.getDeclaredField("valueTypeName");
        valueTypeName.setAccessible(true);
        assertEquals(TestRedisBO.class.getName(), valueTypeName.get(client));
        Field keyAnnotation = clientTypeClazz.getDeclaredField("keyAnnotation");
        keyAnnotation.setAccessible(true);
        assertEquals("TEST_REDIS_PO", ((RedisKey) keyAnnotation.get(client)).value());
        Field idAnnotation = clientTypeClazz.getDeclaredField("idAnnotation");
        idAnnotation.setAccessible(true);
        assertNotNull(idAnnotation.get(client));
        Field idAnnotationField = clientTypeClazz.getDeclaredField("idAnnotationField");
        idAnnotationField.setAccessible(true);
        assertNotNull(idAnnotationField.get(client));
        Field timeToLiveAnnotation = clientTypeClazz.getDeclaredField("timeToLiveAnnotation");
        timeToLiveAnnotation.setAccessible(true);
        assertNotNull(timeToLiveAnnotation.get(client));
        Field timeToLiveAnnotationField = clientTypeClazz.getDeclaredField("timeToLiveAnnotationField");
        timeToLiveAnnotationField.setAccessible(true);
        assertNotNull(timeToLiveAnnotationField.get(client));
        Field idResolver = clientTypeClazz.getDeclaredField("idResolver");
        idResolver.setAccessible(true);
        assertNotNull(idResolver.get(client));
        Field timeToLiveResolver = clientTypeClazz.getDeclaredField("timeToLiveResolver");
        timeToLiveResolver.setAccessible(true);
        assertNotNull(timeToLiveResolver.get(client));
    }

    @RedisKey("TEST_REDIS_PO")
    private static class TestRedisBO extends TimeToLiveRedisBO {
        @Serial
        private static final long serialVersionUID = -7329378482217381303L;
    }

    private static class TestRedisDAO extends RedisDAO<TestRedisBO> {
    }
}
