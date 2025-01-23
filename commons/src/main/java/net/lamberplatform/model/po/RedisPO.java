package net.lamberplatform.model.po;

import lombok.Data;
import net.lamberplatform.data.redis.RedisId;

import java.io.Serial;
import java.io.Serializable;

/**
 * DATE: 2025/1/23
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Data
public class RedisPO implements Serializable {

    @Serial
    private static final long serialVersionUID = -274921269462359458L;

    @RedisId
    private String id;
}
