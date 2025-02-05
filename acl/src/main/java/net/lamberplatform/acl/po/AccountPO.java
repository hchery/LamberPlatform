package net.lamberplatform.acl.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.lamberplatform.model.bo.acl.AccountRole;
import net.lamberplatform.model.bo.acl.AccountStatus;
import net.lamberplatform.model.po.ValueMongoPO;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
@Document("account_detail")
public class AccountPO extends ValueMongoPO {

    @Serial
    private static final long serialVersionUID = -938456048488912505L;

    @Field("account")
    @Indexed(unique = true)
    private String account;

    @Field("role")
    @Indexed
    private AccountRole role;

    @Field("status")
    @Indexed
    private AccountStatus status;
}
