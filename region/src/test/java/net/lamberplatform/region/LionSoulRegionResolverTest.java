package net.lamberplatform.region;

import jakarta.annotation.Resource;
import net.lamberplatform.chain.HandlerUnitTestTool;
import net.lamberplatform.region.resolver.LionSoulRegionResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@SpringBootTest(classes = LionSoulRegionResolver.class)
@ExtendWith(SpringExtension.class)
public class LionSoulRegionResolverTest {

    @Resource
    private LionSoulRegionResolver resolver;

    @Mock
    private RegionResolver next;

    @BeforeEach
    public void invokeConfigureNext() {
        HandlerUnitTestTool.invokeConfigureNextHandler(resolver, next);
    }

    @Test
    public void testResolveRegion() {
        when(next.resolveRegion(any())).thenReturn("other");
        assertEquals("吉林省四平市", resolver.resolveRegion("119.54.22.12"));
        assertEquals("北京市", resolver.resolveRegion("180.149.134.141"));
        assertEquals("上海市", resolver.resolveRegion("117.143.44.168"));
        assertEquals("重庆市", resolver.resolveRegion("222.178.244.1"));
        assertEquals("天津市", resolver.resolveRegion("117.14.151.2"));
        assertEquals("中国香港", resolver.resolveRegion("101.32.37.183"));
        assertEquals("中国澳门", resolver.resolveRegion("192.203.232.2"));
        assertEquals("中国台湾", resolver.resolveRegion("101.9.108.19"));
        assertEquals("other", resolver.resolveRegion("67.220.91.3"));
        assertEquals("other", resolver.resolveRegion("45.77.58.144"));
    }
}
