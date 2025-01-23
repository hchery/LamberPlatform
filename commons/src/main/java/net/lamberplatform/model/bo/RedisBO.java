package net.lamberplatform.model.bo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * DATE: 2025/1/23
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Data
public class RedisBO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6304961557902717888L;

    private String id;
}
