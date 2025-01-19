package net.lamberplatform.region.resolver;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.AbstractNamedRecord;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import net.lamberplatform.region.NotAllowedRegionException;
import net.lamberplatform.region.RegionResolver;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Component
@Slf4j
public class GeoLiteRegionResolver extends RegionResolver {

    private static final String GeoLiteI18n = "zh-CN";

    private DatabaseReader databaseReader;

    @Override
    public String resolveRegion(String ipAddress) {

        if (log.isDebugEnabled()) {
            log.debug("Resolve ip region by GeoLite-City: '{}'", ipAddress);
        }

        try {
            CityResponse resp = databaseReader.city(InetAddress.getByName(ipAddress));
            return formatRegion(
                resolveName(resp.getCountry()),
                resolveName(resp.getMostSpecificSubdivision())
            );
        } catch (Exception ex) {
            return handleError(ipAddress, ex);
        }
    }

    private String resolveName(AbstractNamedRecord record) {
        return record.getNames().get(GeoLiteI18n);
    }

    private String formatRegion(String country, String province) throws NotAllowedRegionException {

        // 没有国家信息，忽略该数据
        if (StringUtils.isBlank(country)) {
            throw new NotAllowedRegionException(
                "GeoLite service not applied when country not exists"
            );
        }

        // 中国地区不使用此定位方式
        if (StringUtils.equals("中国", country)) {
            throw new NotAllowedRegionException(
                "GeoLite service not applied at current region"
            );
        }

        // 没有省份信息，只保留国家数据
        if (StringUtils.isBlank(province)) {
            return country;
        }

        // 国家信息和省份信息拼接作为位置数据
        return "%s%s".formatted(country, province);
    }

    private String handleError(String ipAddress, Exception ex) {
        if (log.isDebugEnabled()) {
            log.debug("GeoLite service confirm ip: '{}' region with error, message: '{}'",
                ipAddress,
                ex.getMessage(),
                ex
            );
        }
        return maybeNext().resolveRegion(ipAddress);
    }

    @Value("classpath:geolite2-city/GeoLite2-City.mmdb")
    protected void openDatabaseReader(Resource resource) throws IOException {
        log.info("GeoLite database reader opening...");
        databaseReader = new DatabaseReader.Builder(resource.getInputStream()).build();
        log.info("GeoLite database reader opened.");
    }

    @PreDestroy
    protected void closeDatabaseReader() throws IOException {
        log.info("GeoLite database reader closing...");
        databaseReader.close();
        log.info("GeoLite database reader closed.");
    }
}
