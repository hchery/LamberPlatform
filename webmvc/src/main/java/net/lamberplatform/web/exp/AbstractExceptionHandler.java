package net.lamberplatform.web.exp;

import jakarta.annotation.Resource;
import net.lamberplatform.exp.BizException;
import net.lamberplatform.model.dto.Response;
import net.lamberplatform.web.I18n;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
public abstract class AbstractExceptionHandler {

    @Resource
    private I18n i18n;

    protected Response<Void> handle(BizException ex) {
        return new Response<>(
            ex.getCode()._int_,
            i18n.$(ex.getMsgKey()._str_)
        );
    }
}
