package net.lamberplatform.web.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Configuration(proxyBeanMethods = false)
public class RequestContextListenerConfiguration {

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
}
