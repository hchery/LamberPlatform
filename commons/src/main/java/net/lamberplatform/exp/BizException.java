package net.lamberplatform.exp;

import lombok.Getter;

import java.io.Serial;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Getter
public class BizException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 873299672735540232L;

    private final Code code;
    private final MsgKey msgKey;

    public BizException(Code code, MsgKey msgKey, String message) {
        this(code, msgKey, message, null);
    }

    public BizException(Code code, MsgKey msgKey, Throwable cause) {
        this(code, msgKey, cause.getMessage(), cause);
    }

    public BizException(Code code, MsgKey msgKey, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.msgKey = msgKey;
    }
}
