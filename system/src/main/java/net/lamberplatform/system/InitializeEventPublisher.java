package net.lamberplatform.system;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.lamberplatform.event.AccountInitializeEvent;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * DATE: 2025/2/5
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Component
@Slf4j
public class InitializeEventPublisher implements InitializingBean {

    @Resource
    private ApplicationContext context;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Initialize system now, please wait a moment...");
        context.publishEvent(new AccountInitializeEvent(this));
        log.info("Initialize system completed, have a nice day!");
    }
}
