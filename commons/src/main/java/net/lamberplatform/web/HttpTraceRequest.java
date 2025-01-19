package net.lamberplatform.web;

import jakarta.servlet.http.HttpServletRequest;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
public interface HttpTraceRequest extends HttpServletRequest {

    String getRemoteIp();
    String getUserAgent();
}
