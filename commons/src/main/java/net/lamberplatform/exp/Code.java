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
public enum Code implements Serializable {

    STD_BadRequest(400),
    STD_NotFound(404),
    SRD_MethodNotAllowed(405),
    STD_ServerError(500),
    ;

    @Serial
    private static final long serialVersionUID = 5079278652448182512L;

    public final int _int_;
}
