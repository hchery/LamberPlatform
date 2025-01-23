package net.lamberplatform.model.po;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serial;
import java.io.Serializable;

/**
 * DATE: 2025/1/23
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Data
public class MongoPO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5356711247762615532L;

    @MongoId
    private String id;
}
