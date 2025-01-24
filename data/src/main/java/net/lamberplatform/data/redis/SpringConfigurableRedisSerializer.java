package net.lamberplatform.data.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * DATE: 2025/1/24
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@ConfigurationProperties(prefix = "net.lamberplatform.redis")
@Data
public class SpringConfigurableRedisSerializer implements ConfigurableRedisSerializer {

    private String keyPrefix = "net.lamberplatform";

    private Charset charset = StandardCharsets.UTF_8;
}
