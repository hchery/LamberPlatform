package net.lamberplatform.i18n;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@SpringBootTest(classes = {LocaleResolverConfiguration.class, YamlMessageSource.class})
@ExtendWith(SpringExtension.class)
public class YamlMessageSourceTest {

    @Resource
    private YamlMessageSource yamlMessageSource;

    @Test
    public void testResolveCode() {
        assertEquals("测试", resolve_Zh_CN("TEST-Std_Text"));
        assertEquals("扩展", resolve_Zh_CN("TEST-Ext_Text"));
        assertEquals("Test", resolve_En_US("TEST-Std_Text"));
        assertEquals("Ext", resolve_En_US("TEST-Ext_Text"));
    }

    private String resolve_Zh_CN(String code) {
        return resolve(code, Locale.of("zh", "CN"));
    }

    private String resolve_En_US(String code) {
        return resolve(code, Locale.of("en", "US"));
    }

    private String resolve(String code, Locale locale) {
        return yamlMessageSource.resolveCode(code, locale).format(new Object[0]);
    }
}
