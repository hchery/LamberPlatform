package net.lamberplatform.chain;

import java.io.Serial;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
public class CallHandlerException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -4321338596010529514L;

    public CallHandlerException(String message) {
        super(message);
    }
}
