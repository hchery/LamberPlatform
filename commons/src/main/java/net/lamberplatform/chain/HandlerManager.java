package net.lamberplatform.chain;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
public abstract class HandlerManager<T extends Handler<T>> implements ApplicationContextAware {

    private T headNodeHandler;

    public T fetchHead() throws CallHandlerException {
        if (headNodeHandler != null) {
            return headNodeHandler;
        }
        String message = "Head handler is null, maybe you need to check applyHandlers()?";
        throw new CallHandlerException(message);
    }

    protected abstract void applyHandlers(HandlerApply<T> apply);

    private T buildLinkedList(ApplicationContext ctx, List<Class<? extends T>> handlerTypeList, int index) {
        if (index >= handlerTypeList.size()) {
            return null;
        }
        // 从SpringContext中获取bean构造链表
        T handler = ctx.getBean(handlerTypeList.get(index));
        handler.configureNextHandler(buildLinkedList(ctx, handlerTypeList, index + 1));
        return handler;
    }

    @Override
    public final void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        List<Class<? extends T>> handlerTypeList = new ArrayList<>();
        applyHandlers(handlerTypeList::add);
        this.headNodeHandler = buildLinkedList(applicationContext, handlerTypeList, 0);
    }
}
