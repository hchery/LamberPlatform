package net.lamberplatform.web.exp;

import net.lamberplatform.exp.BizException;
import net.lamberplatform.exp.Code;
import net.lamberplatform.exp.MsgKey;
import net.lamberplatform.model.dto.Response;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@RestControllerAdvice
public class SpringExceptionHandler extends AbstractExceptionHandler {

    @ExceptionHandler(MethodNotAllowedException.class)
    public Response<Void> methodNotAllowed(MethodNotAllowedException ex) {
        BizException bizEx = new BizException(Code.SRD_MethodNotAllowed, MsgKey.BIZ_MethodNotAllowed, ex);
        return handle(bizEx);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response<Void> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        BizException bizEx = new BizException(Code.SRD_MethodNotAllowed, MsgKey.BIZ_MethodNotAllowed, ex);
        return handle(bizEx);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response<Void> httpMessageNotReadable(HttpMessageNotReadableException ex) {
        BizException bizEx = new BizException(Code.STD_BadRequest, MsgKey.BIZ_BadRequest, ex);
        return handle(bizEx);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public Response<Void> noResourceFound(NoResourceFoundException ex) {
        BizException bizEx = new BizException(Code.STD_NotFound, MsgKey.BIZ_NotFound, ex);
        return handle(bizEx);
    }
}
