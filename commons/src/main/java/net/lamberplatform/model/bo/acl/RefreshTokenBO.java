package net.lamberplatform.model.bo.acl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.lamberplatform.data.redis.RedisKey;
import net.lamberplatform.model.bo.TimeToLiveRedisBO;

import java.io.Serial;

/**
 * DATE: 2025/1/25
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@RedisKey("refresh_token")
public class RefreshTokenBO extends TimeToLiveRedisBO {

    @Serial
    private static final long serialVersionUID = 1198654398867494561L;
}
