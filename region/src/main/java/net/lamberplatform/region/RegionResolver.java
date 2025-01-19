package net.lamberplatform.region;

import net.lamberplatform.chain.Handler;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
public abstract class RegionResolver extends Handler<RegionResolver> {
    public abstract String resolveRegion(String ipAddress);
}
