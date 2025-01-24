package net.lamberplatform.data.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * DATE: 2025/1/25
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Configuration(proxyBeanMethods = false)
public class CacheDependenciesConfiguration {

    @Bean
    public RedisCacheWriter cacheWriter(RedisConnectionFactory factory) {
        return RedisCacheWriter.nonLockingRedisCacheWriter(factory);
    }

    @Bean
    public RedisCacheConfiguration defaultCacheConfiguration(ConfigurableRedisSerializer redisSerializer) {
        return RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(5))
            .serializeKeysWith(RedisSerializationContext
                .<String, Object>newSerializationContext(new CacheConfigurableRedisSerializer(redisSerializer))
                .build()
                .getKeySerializationPair()
            )
            .serializeValuesWith(RedisSerializationContext.java().getValueSerializationPair())
            .disableKeyPrefix();
    }
}
