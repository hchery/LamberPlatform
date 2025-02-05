package net.lamberplatform.event;

import org.springframework.context.ApplicationEvent;

import java.io.Serial;

/**
 * DATE: 2025/2/5
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
public class AccountInitializeEvent extends ApplicationEvent {

    @Serial
    private static final long serialVersionUID = -8685159537517215186L;

    public AccountInitializeEvent(Object source) {
        super(source);
    }
}
