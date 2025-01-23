package net.lamberplatform.model.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Field;

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
public abstract class ConstMongoPO extends MongoPO {

    @Serial
    private static final long serialVersionUID = -2309548629768909632L;

    @Field("CREATE_TIME")
    @CreatedDate
    private LocalDateTime createTime;
}
