package net.lamberplatform.chain;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
public final class HandlerUnitTestTool {

    private HandlerUnitTestTool() {}

    public static <T extends Handler<T>> void invokeConfigureNextHandler(T handler, T next) {
        handler.configureNextHandler(next);
    }
}
