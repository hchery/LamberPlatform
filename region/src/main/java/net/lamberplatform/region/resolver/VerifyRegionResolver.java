package net.lamberplatform.region.resolver;

import lombok.extern.slf4j.Slf4j;
import net.lamberplatform.region.RegionResolver;
import net.lamberplatform.web.Region;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Component
@Slf4j
public class VerifyRegionResolver extends RegionResolver {

    @Override
    public String resolveRegion(String ipAddress) {

        // 过滤空数据
        if (StringUtils.isBlank(ipAddress)) {
            return Region.UnknownRegion;
        }

        // 校验ip合法性
        try {
            if (InetAddress.getByName(ipAddress) == null) {
                return Region.UnknownRegion;
            }
            return maybeNext().resolveRegion(ipAddress);
        } catch (Exception ex) {
            if (log.isDebugEnabled()) {
                log.debug("Unknown host: '{}', message: '{}'",
                    ipAddress,
                    ex.getMessage(),
                    ex
                );
            }
            return Region.UnknownRegion;
        }
    }
}
