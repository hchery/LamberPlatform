package net.lamberplatform.chain;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
public abstract class Handler<T extends Handler<T>> {

    private T nextHandler;

    void configureNextHandler(T nextHandler) {
        this.nextHandler = nextHandler;
    }

    protected T maybeNext() throws CallHandlerException {
        if (nextHandler != null) {
            return nextHandler;
        }
        throw new CallHandlerException("No next handler available");
    }
}
