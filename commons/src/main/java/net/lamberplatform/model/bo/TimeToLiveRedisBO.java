package net.lamberplatform.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;

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

    private long timeToLiveSeconds = 300;
}
