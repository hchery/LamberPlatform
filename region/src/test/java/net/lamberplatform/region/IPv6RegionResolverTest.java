package net.lamberplatform.region;

import net.lamberplatform.chain.HandlerUnitTestTool;
import net.lamberplatform.region.resolver.IPv6RegionResolver;
import net.lamberplatform.web.Region;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@ExtendWith(MockitoExtension.class)
public class IPv6RegionResolverTest {

    @InjectMocks
    private IPv6RegionResolver resolver;

    @Mock
    private RegionResolver next;

    @BeforeEach
    public void invokeConfigureNext() {
        HandlerUnitTestTool.invokeConfigureNextHandler(resolver, next);
    }

    @Test
    public void testResolveRegion() {
        when(next.resolveRegion(any())).thenReturn("addr");
        assertEquals(Region.IPv6, resolver.resolveRegion("::1"));
        assertEquals("addr", resolver.resolveRegion("127.0.0.1"));
    }
}
