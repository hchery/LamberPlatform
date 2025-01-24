package net.lamberplatform.model.bo.acl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.lamberplatform.model.bo.ValueMongoBO;

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
public class AclTokenBO extends ValueMongoBO {

    @Serial
    private static final long serialVersionUID = 5273847935123141822L;
}
