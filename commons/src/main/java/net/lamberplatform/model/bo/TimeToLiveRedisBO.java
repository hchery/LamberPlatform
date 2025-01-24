package net.lamberplatform.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serial;
import java.util.concurrent.TimeUnit;

/**
 * DATE: 2025/1/23
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public abstract class TimeToLiveRedisBO extends RedisBO {

    @Serial
    private static final long serialVersionUID = 5441423573752025749L;

    @TimeToLive(unit = TimeUnit.SECONDS)
    private long timeToLiveSeconds = 300;
}
