package net.lamberplatform.web;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Set;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@RequestScope
@Component
@Getter
public class HttpTraceRequestWrapper extends HttpServletRequestWrapper implements HttpTraceRequest {

    private static final Set<String> IpHeaders = Set.of(
        "X-Real-IP",
        "x-Forwarded-For",
        "Proxy-Client-IP",
        "WL-Proxy-Client-IP"
    );

    private String remoteIp;
    private String userAgent;

    public HttpTraceRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @PostConstruct
    protected void resolveExternalProperties() {
        this.remoteIp = resolveRequestRemoteIp();
        this.userAgent = getHeader("User-Agent");
    }

    private String resolveRequestRemoteIp() {
        for (String header : IpHeaders) {
            String ip = getHeader(header);
            if (verifyIp(ip)) {
                return selectFirst(ip);
            }
        }
        return resolveUndertakeRemoteAddr();
    }

    private String resolveUndertakeRemoteAddr() {
        return getRemoteAddr();
    }

    private boolean verifyIp(String ip) {
        return StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip);
    }

    private String selectFirst(String ip) {
        int commaIndex = ip.indexOf(',');
        return commaIndex != -1 ? ip.substring(0, commaIndex) : ip;
    }
}
