package net.lamberplatform.web.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletResponse;
import net.lamberplatform.model.dto.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@ExtendWith(MockitoExtension.class)
public class ResponseAutoWrapperAdviceTest {

    @InjectMocks
    private ResponseAutoWrapperAdvice advice;

    @Spy
    private ObjectMapper objectMapper;

    @Mock
    private MethodParameter returnType;

    @Mock
    private ServletServerHttpRequest httpRequest;

    @Mock
    private ServletServerHttpResponse httpResponse;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpHeaders headers;

    @BeforeEach
    public void doInvokeBeforeTest() {
        objectMapper.registerModule(new JavaTimeModule());
        advice.setJsonFmt(objectMapper);
        when(httpResponse.getServletResponse()).thenReturn(response);
    }

    private Object callAdvice(Object body, MediaType mediaType, Class<? extends HttpMessageConverter<?>> converterType) {
        return advice.beforeBodyWrite(
            body,
            returnType,
            mediaType,
            MappingJackson2HttpMessageConverter.class,
            httpRequest,
            httpResponse
        );
    }

    @Test
    public void testBeforeBodyWrite_StatusWithRedirect() {
        when(response.getStatus()).thenReturn(302);
        Object body = new Object();
        Object result = callAdvice(body, MediaType.APPLICATION_JSON, MappingJackson2HttpMessageConverter.class);
        assertNotNull(result);
        assertSame(result, body);
    }

    @Test
    public void testBeforeBodyWrite_StatusWithError() {
        when(response.getStatus()).thenReturn(400);
        Map<String, Object> body = new HashMap<>();
        body.put("status", 400);
        body.put("error", "error");
        Object result = callAdvice(body, MediaType.APPLICATION_JSON, MappingJackson2HttpMessageConverter.class);
        assertNotNull(result);
        assertInstanceOf(Response.class, result);
        @SuppressWarnings("unchecked")
        Response<Void> resp = (Response<Void>) result;
        assertEquals(400, resp.getCode());
        assertEquals("error", resp.getDesc());
    }

    @Test
    public void testBeforeBodyWrite_StringBody() {
        when(response.getStatus()).thenReturn(200);
        when(httpResponse.getHeaders()).thenReturn(headers);
        String body = "body";
        Object result = callAdvice(body, MediaType.TEXT_PLAIN, StringHttpMessageConverter.class);
        assertNotNull(result);
        assertInstanceOf(String.class, result);
        verify(headers, times(1)).setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void testBeforeBodyWrite_ObjectBody() {
        when(response.getStatus()).thenReturn(200);
        Object body = new Object();
        Object result = callAdvice(body, MediaType.APPLICATION_JSON, MappingJackson2HttpMessageConverter.class);
        assertNotNull(result);
        assertInstanceOf(Response.class, result);
        @SuppressWarnings("unchecked")
        Response<Void> resp = (Response<Void>) result;
        assertEquals(0, resp.getCode());
        assertSame(body, resp.getData());
    }
}
