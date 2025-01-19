package net.lamberplatform.exp;

import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@RequiredArgsConstructor
public enum MsgKey implements Serializable {

    BIZ_ServerError("BIZ-Server_Error"),
    BIZ_BadRequest("BIZ-Bad_Request"),
    BIZ_NotFound("BIZ-Not_Found"),
    BIZ_MethodNotAllowed("BIZ-Method_Not_Allowed"),
    ;

    @Serial
    private static final long serialVersionUID = 8326586663715345232L;

    public final String _str_;
}
