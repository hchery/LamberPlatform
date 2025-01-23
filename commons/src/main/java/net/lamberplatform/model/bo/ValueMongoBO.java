package net.lamberplatform.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * DATE: 2025/1/23
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public abstract class ValueMongoBO extends ConstMongoBO {

    @Serial
    private static final long serialVersionUID = -4785941239832437489L;

    private LocalDateTime updateTime;
}
