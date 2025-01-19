package net.lamberplatform.region.resolver;

import net.lamberplatform.region.RegionResolver;
import net.lamberplatform.web.Region;
import org.springframework.stereotype.Component;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Component
public class FinalizeRegionResolver extends RegionResolver {

    @Override
    public String resolveRegion(String ipAddress) {
        return Region.UnknownRegion;
    }
}
