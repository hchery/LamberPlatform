package net.lamberplatform.data.redis;

import java.nio.charset.Charset;

/**
 * DATE: 2025/1/25
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
public record CacheConfigurableRedisSerializer(ConfigurableRedisSerializer serializerBean) implements ConfigurableRedisSerializer {

    @Override
    public String getKeyPrefix() {
        return "%s:%s".formatted(serializerBean.getKeyPrefix(), "SPRING-CACHE");
    }

    @Override
    public Charset getCharset() {
        return serializerBean.getCharset();
    }
}
