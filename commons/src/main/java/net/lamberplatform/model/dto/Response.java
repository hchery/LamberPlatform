package net.lamberplatform.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@RequiredArgsConstructor
@Data
public class Response<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -6114566587270407719L;

    private final int code;
    private final String desc;
    private final T data;
    private final LocalDateTime time = LocalDateTime.now();

    public Response(T data) {
        this(0, null, data);
    }

    public Response(int code, String desc) {
        this(code, desc, null);
    }
}
