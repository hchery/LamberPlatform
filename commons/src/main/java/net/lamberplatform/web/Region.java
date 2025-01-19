package net.lamberplatform.web;

import org.apache.commons.lang3.StringUtils;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@FunctionalInterface
public interface Region {

    String UnknownRegion    = StringUtils.EMPTY;
    String IPv6             = "IPv6";
    String LanIP            = "LanIP";

    String $(String ipAddress);
}
