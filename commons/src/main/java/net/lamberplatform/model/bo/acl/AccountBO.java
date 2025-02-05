package net.lamberplatform.model.bo.acl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.lamberplatform.model.bo.ValueMongoBO;

import java.io.Serial;

/**
 * DATE: 2025/2/5
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class AccountBO extends ValueMongoBO {

    public interface Specials {

        String ADMIN = "admin";
    }

    @Serial
    private static final long serialVersionUID = -3498167563884330634L;

    private String account;
    private AccountRole role;
    private AccountStatus status;
}
