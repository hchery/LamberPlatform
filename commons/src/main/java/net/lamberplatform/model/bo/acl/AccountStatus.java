package net.lamberplatform.model.bo.acl;

import java.io.Serial;
import java.io.Serializable;

/**
 * DATE: 2025/2/5
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
public enum AccountStatus implements Serializable {
    USING,
    DISABLED,
    ;

    @Serial
    private static final long serialVersionUID = 2168036327544957314L;
}
