package net.lamberplatform.region.resolver;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import net.lamberplatform.region.NotAllowedRegionException;
import net.lamberplatform.region.RegionResolver;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Component
@Slf4j
public class LionSoulRegionResolver extends RegionResolver {

    // 特别行政区域
    // 目前台湾地区IP不做精确定位
    // 只定位到省级
    // 台湾省由于库中数据格式问题先按照特别行政区逻辑处理
    // 台湾省数据问题代码中有解释
    private static final Set<String> SpecialRegion = Set.of(
        "香港",
        "澳门",
        "台湾省"
    );

    // 直辖市城市
    private static final Set<String> CG_City = Set.of(
        "北京",
        "上海",
        "天津",
        "重庆"
    );

    private Searcher searcher;

    @Override
    public String resolveRegion(String ipAddress) {

        if (log.isDebugEnabled()) {
            log.debug("Resolve ip region by ip2region: '{}'", ipAddress);
        }

        try {
            String[] r = searcher.search(ipAddress).split("\\|");
            return formatRegion(r[0], r[2], r[3]);
        } catch (Exception ex) {
            return handleError(ipAddress, ex);
        }
    }

    private String formatRegion(String country, String province, String city) throws NotAllowedRegionException {

        // 非国内地区不使用此定位方式
        if (!StringUtils.equals("中国", country)) {
            throw new NotAllowedRegionException(
                "Ip2Region service not applied at current region"
            );
        }

        // 没有省份信息，只保留国家信息
        if (StringUtils.isBlank(province)) {
            return country;
        }

        // 特殊行政区域使用国家级字样开头
        if (SpecialRegion.contains(province)) {
            return "%s%s".formatted(country, optimizeSpecialRegion(province));
        }

        // 直辖市省略省份
        if (CG_City.contains(province)) {
            return city;
        }

        // 其他中国大陆地区信息，拼接省份和城市信息
        return "%s%s".formatted(province, city);
    }

    // todo 由于目前数据库里台湾省数据格式不统一，因此先将台湾省数据按照特殊行政区域逻辑处理，后续变更为省市处理逻辑
    // 处理特殊行政区域
    // 由于库中台湾尾部带有省份字样
    // 为了保持数据库数据格式统一，将省字去掉
    // 后续拼接结果应为中国台湾
    private String optimizeSpecialRegion(String province) {
        if (StringUtils.equals("台湾省", province)) {
            return "台湾";
        }
        return province;
    }

    private String handleError(String ipAddress, Exception ex) {
        if (log.isDebugEnabled()) {
            log.debug("Ip2Region service confirm ip: '{}' region with error, message: '{}'",
                ipAddress,
                ex.getMessage(),
                ex
            );
        }
        return maybeNext().resolveRegion(ipAddress);
    }

    @Value("classpath:ip2region/ip2region.xdb")
    protected void openRegionSearcher(Resource resource) throws IOException {
        log.info("LionSoul ip2region searcher opening...");
        searcher = Searcher.newWithBuffer(resource.getContentAsByteArray());
        log.info("LionSoul ip2region searcher opened.");
    }

    @PreDestroy
    protected void closeRegionSearcher() throws IOException {
        log.info("LionSoul ip2region searcher closing...");
        searcher.close();
        log.info("LionSoul ip2region searcher closed.");
    }
}
