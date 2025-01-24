package net.lamberplatform.data.redis;

import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * DATE: 2025/1/24
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
public interface ConfigurableRedisSerializer extends RedisSerializer<String> {

    String getKeyPrefix();
}
