package net.lamberplatform.i18n;

import jakarta.annotation.Resource;
import net.lamberplatform.web.HttpTraceRequest;
import net.lamberplatform.web.I18n;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@RequestScope
@Component
public class MessageSourceI18n implements I18n {

    @Resource
    private HttpTraceRequest request;

    @Resource
    private LocaleResolver localeResolver;

    @Resource
    private MessageSource messageSource;

    @Override
    public String $(String key) {
        Locale locale = localeResolver.resolveLocale(request);
        return messageSource.getMessage(key, null, locale);
    }
}
