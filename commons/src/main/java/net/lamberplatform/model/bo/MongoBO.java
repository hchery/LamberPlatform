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
public class MongoBO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8772335601629223472L;

    private String id;
}
