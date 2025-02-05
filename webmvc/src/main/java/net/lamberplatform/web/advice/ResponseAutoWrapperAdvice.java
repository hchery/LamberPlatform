package net.lamberplatform.web.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import net.lamberplatform.model.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@RestControllerAdvice
public class ResponseAutoWrapperAdvice implements ResponseBodyAdvice<Object> {

    private static final Set<Class<? extends HttpMessageConverter<?>>> SupportConverters = Set.of(
        StringHttpMessageConverter.class,
        MappingJackson2HttpMessageConverter.class
    );

    private static final Set<Integer> TransmissionStatus = Set.of(
        HttpStatus.NOT_FOUND.value(),
        HttpStatus.METHOD_NOT_ALLOWED.value()
    );

    private Function<Object, String> jsonFmt;

    @Autowired
    protected void setJsonFmt(ObjectMapper objectMapper) {
        // 这里由于处理异常因此不使用lambda
        this.jsonFmt = new Function<>() {
            @SneakyThrows
            @Override
            public String apply(Object o) {
                return objectMapper.writeValueAsString(o);
            }
        };
    }

    @Override
    public boolean supports(@NonNull MethodParameter returnType,
                            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {

        return SupportConverters.contains(converterType);
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body,
                                  @NonNull MethodParameter returnType,
                                  @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NonNull ServerHttpRequest request,
                                  @NonNull ServerHttpResponse response) {

        ServletServerHttpResponse httpResponse = (ServletServerHttpResponse) response;
        int status = httpResponse.getServletResponse().getStatus();

        return switch (status / 100) {
            case 3 -> body;
            case 4,5 -> resolveErrorResponse(status, httpResponse, body);
            default -> resolveBodyResponse(httpResponse, body);
        };
    }

    private Object resolveBodyResponse(ServletServerHttpResponse response, Object body) {
        // 特殊body处理
        if (body instanceof Response) {
            return body;
        }
        // StringBody由于处理器原因导致header不同
        // 因此需要重新设置为json格式
        if (body instanceof String) {
            Response<String> resp = new Response<>((String) body);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return jsonFmt.apply(resp);
        }
        return new Response<>(body);
    }

    private Object resolveErrorResponse(int status, ServletServerHttpResponse response, Object body) {
        // 处理响应header
        if (!TransmissionStatus.contains(status)) {
            response.setStatusCode(HttpStatus.OK);
        }
        // 特殊body处理
        if (body == null || body instanceof Response) {
            return body;
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) body;

        return new Response<>((int) map.get("status"), (String) map.get("error"));
    }
}
