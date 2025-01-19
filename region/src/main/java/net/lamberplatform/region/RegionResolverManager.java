package net.lamberplatform.region;

import net.lamberplatform.chain.HandlerApply;
import net.lamberplatform.chain.HandlerManager;
import net.lamberplatform.region.resolver.*;
import org.springframework.stereotype.Component;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Component
public class RegionResolverManager extends HandlerManager<RegionResolver> {

    @Override
    protected void applyHandlers(HandlerApply<RegionResolver> apply) {
        apply.$(VerifyRegionResolver.class);
        apply.$(IPv6RegionResolver.class);
        apply.$(LanIpRegionResolver.class);
        apply.$(LionSoulRegionResolver.class);
        apply.$(GeoLiteRegionResolver.class);
        apply.$(FinalizeRegionResolver.class);
    }
}
