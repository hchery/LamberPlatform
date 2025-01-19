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
public class LanIpRegionResolver extends RegionResolver {

    @Override
    public String resolveRegion(String ipAddress) {
        int[] d = new int[] { 0, 0 };
        transferFirstTwoGroups(ipAddress, d);
        if (isLanIp(d)) {
            return Region.LanIP;
        }
        return maybeNext().resolveRegion(ipAddress);
    }

    private boolean isLanIp(int[] d) {
        return switch (d[0]) {
            case 0x7F /*127*/, 0x0A /*10*/ ->
                true;
            case 0xAC /*172*/ ->
                d[1] >= 0x10 /*16*/ && d[1] <= 0x1F /*31*/;
            case 0xC0 /*192*/ ->
                d[1] == 0xA8 /*168*/;
            default -> false;
        };
    }

    private void transferFirstTwoGroups(String ipAddress, int[] d) {
        String[] parts = ipAddress.split("\\.");
        d[0] = Integer.parseInt(parts[0]);
        d[1] = Integer.parseInt(parts[1]);
    }
}
