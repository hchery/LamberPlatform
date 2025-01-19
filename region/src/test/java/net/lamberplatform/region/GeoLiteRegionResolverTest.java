package net.lamberplatform.region;

import jakarta.annotation.Resource;
import net.lamberplatform.chain.HandlerUnitTestTool;
import net.lamberplatform.region.resolver.GeoLiteRegionResolver;
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
@SpringBootTest(classes = GeoLiteRegionResolver.class)
@ExtendWith(SpringExtension.class)
public class GeoLiteRegionResolverTest {

    @Resource
    private GeoLiteRegionResolver resolver;

    @Mock
    private RegionResolver next;

    @BeforeEach
    public void invokeConfigureNext() {
        HandlerUnitTestTool.invokeConfigureNextHandler(resolver, next);
    }

    @Test
    public void testResolveRegion() {
        when(next.resolveRegion(any())).thenReturn("other");
        assertEquals("美国", resolver.resolveRegion("67.220.91.3"));
        assertEquals("英国", resolver.resolveRegion("45.77.58.144"));
        assertEquals("other", resolver.resolveRegion("119.54.22.12"));
    }
}
