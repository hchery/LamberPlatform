package net.lamberplatform.region.resolver;

import lombok.SneakyThrows;
import net.lamberplatform.region.RegionResolver;
import net.lamberplatform.web.Region;
import org.springframework.stereotype.Component;

import java.net.Inet6Address;
import java.net.InetAddress;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Component
public class IPv6RegionResolver extends RegionResolver {

    @SneakyThrows
    @Override
    public String resolveRegion(String ipAddress) {
        InetAddress addr = InetAddress.getByName(ipAddress);
        if (addr instanceof Inet6Address) {
            return Region.IPv6;
        }
        return maybeNext().resolveRegion(ipAddress);
    }
}
