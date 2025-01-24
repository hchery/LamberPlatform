package net.lamberplatform.data.redis;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * DATE: 2025/1/24
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
public interface ConfigurableRedisSerializer extends RedisSerializer<String> {

    String getKeyPrefix();

    Charset getCharset();

    @Override
    default byte[] serialize(String value) throws SerializationException {
        String keyPrefix = getKeyPrefix();
        Charset charset = getCharset();
        return "%s:%s".formatted(keyPrefix, value).getBytes(charset);
    }

    @Override
    default String deserialize(byte[] bytes) throws SerializationException {
        String keyPrefix = getKeyPrefix();
        Charset charset = getCharset();
        String key = new String(bytes, charset);
        if (!key.startsWith(keyPrefix)) {
            return key;
        }
        return key.substring(keyPrefix.length());
    }
}
