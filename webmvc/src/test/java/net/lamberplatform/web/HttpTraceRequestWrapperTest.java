package net.lamberplatform.web;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@ExtendWith(MockitoExtension.class)
public class HttpTraceRequestWrapperTest {

    @InjectMocks
    private HttpTraceRequestWrapper wrapper;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    public void beforeInvokeHeaders() {
        when(request.getHeader("User-Agent")).thenReturn(null);
        when(request.getHeader("X-Real-IP")).thenReturn(null);
        when(request.getHeader("x-Forwarded-For")).thenReturn(null);
        when(request.getHeader("Proxy-Client-IP")).thenReturn(null);
        when(request.getHeader("WL-Proxy-Client-IP")).thenReturn(null);
    }

    @Test
    public void testResolveUserAgent() {
        when(request.getHeader("User-Agent")).thenReturn("ua");
        wrapper.resolveExternalProperties();
        assertEquals("ua", wrapper.getUserAgent());
    }

    @Test
    public void testResolveRemoteAddress() {
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");
        wrapper.resolveExternalProperties();
        assertEquals("127.0.0.1", wrapper.getRemoteIp());
        when(request.getHeader("x-Forwarded-For")).thenReturn("127.0.0.2,127.0.0.3");
        wrapper.resolveExternalProperties();
        assertEquals("127.0.0.2", wrapper.getRemoteIp());
    }
}
