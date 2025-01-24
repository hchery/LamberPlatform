package net.lamberplatform.data.redis;

import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * DATE: 2025/1/25
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Component
@Primary
public class ConfigurableRedisCacheManager extends RedisCacheManager {

    public ConfigurableRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    @Override
    @NonNull
    protected RedisCache createRedisCache(@NonNull String name, RedisCacheConfiguration cacheConfiguration) {
        return super.createRedisCache(name, cacheConfiguration);
    }
}
