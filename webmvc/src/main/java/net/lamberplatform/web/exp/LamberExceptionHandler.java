package net.lamberplatform.web.exp;

import net.lamberplatform.exp.BizException;
import net.lamberplatform.exp.Code;
import net.lamberplatform.exp.MsgKey;
import net.lamberplatform.model.dto.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@RestControllerAdvice
public class LamberExceptionHandler extends AbstractExceptionHandler {

    @ExceptionHandler(BizException.class)
    public Response<Void> bizException(BizException ex) {
        return handle(ex);
    }

    @ExceptionHandler(Throwable.class)
    public Response<Void> allThrows(Throwable ex) {
        BizException bizEx = new BizException(Code.STD_ServerError, MsgKey.BIZ_ServerError, ex);
        return handle(bizEx);
    }
}
