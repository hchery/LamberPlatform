package net.lamberplatform.region;

import jakarta.annotation.Resource;
import net.lamberplatform.web.Region;
import org.springframework.stereotype.Service;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Service
public class RegionService implements Region {

    @Resource
    private RegionResolverManager resolverManager;

    @Override
    public String $(String ipAddress) {
        RegionResolver resolver = resolverManager.fetchHead();
        return resolver.resolveRegion(ipAddress);
    }
}
