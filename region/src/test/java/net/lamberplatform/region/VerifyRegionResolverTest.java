package net.lamberplatform.region;

import net.lamberplatform.chain.HandlerUnitTestTool;
import net.lamberplatform.region.resolver.VerifyRegionResolver;
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
public class VerifyRegionResolverTest {

    @InjectMocks
    private VerifyRegionResolver resolver;

    @Mock
    private RegionResolver next;

    @BeforeEach
    public void invokeConfigureNext() {
        HandlerUnitTestTool.invokeConfigureNextHandler(resolver, next);
    }

    @Test
    public void testResolveRegion() {
        when(next.resolveRegion(any())).thenReturn("addr");
        assertEquals("addr", resolver.resolveRegion("192.168.1.1"));
        assertEquals(Region.UnknownRegion, resolver.resolveRegion("192.168.1.c"));
        assertEquals(Region.UnknownRegion, resolver.resolveRegion(""));
        assertEquals(Region.UnknownRegion, resolver.resolveRegion(null));
    }
}
