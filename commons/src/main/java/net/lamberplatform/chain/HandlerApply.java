package net.lamberplatform.chain;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@FunctionalInterface
public interface HandlerApply<T extends Handler<T>> {
    void $(Class<? extends T> handlerClass);
}
