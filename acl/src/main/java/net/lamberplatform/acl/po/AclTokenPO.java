package net.lamberplatform.acl.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.lamberplatform.model.po.ValueMongoPO;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
@Document("acl_token")
public class AclTokenPO extends ValueMongoPO {

    @Serial
    private static final long serialVersionUID = -6509568670877546742L;

    @Field("user_id")
    @Indexed
    private String userId;

    @Field("token")
    @Indexed
    private String token;
}
