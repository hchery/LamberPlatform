package net.lamberplatform.i18n;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Component
@Slf4j
public class YamlMessageSource extends AbstractMessageSource {

    private static final Pattern MessageFilesPattern = Pattern.compile("^messages\\.(\\w{2})_(\\w{2})\\.yaml$");

    private static final Map<String, Map<Locale, MessageFormat>> cachedMessageFormats = new HashMap<>();

    @Override
    @NonNull
    protected MessageFormat resolveCode(@NonNull String code, @NonNull Locale locale) {
        Map<Locale, MessageFormat> fmtMap = cachedMessageFormats.get(code);
        if (fmtMap == null) {
            throw new NoSuchMessageException(code, locale);
        }
        MessageFormat format = fmtMap.get(locale);
        if (format != null) {
            return format;
        }
        // 处理兜底情况
        if (log.isDebugEnabled()) {
            log.debug("No MessageFormat found - code: '{}', locale: '{}', use default with: '{}'",
                code,
                locale,
                LocaleResolverConfiguration.DefaultLocale
            );
        }
        return fmtMap.get(LocaleResolverConfiguration.DefaultLocale);
    }

    @Value("classpath:i18n/messages.*.yaml")
    private void scanYamlMessageFiles(Resource[] resources) throws IOException {
        for (Resource resource : resources) {
            Locale locale = resolveLocale(resource);
            try (InputStream is = resource.getInputStream()) {
                autoGenerateMessageFormats(is, locale);
                log.info("Resolved locale: '{}' message package: '{}'",
                    locale,
                    resource.getFilename()
                );
            }
        }
    }

    private void autoGenerateMessageFormats(InputStream is, Locale locale) throws IOException {
        @SuppressWarnings("unchecked")
        Map<String, String> messageMap = new Yaml().loadAs(is, LinkedHashMap.class);
        messageMap.forEach((code, message) -> {
            Map<Locale, MessageFormat> fmtMap = cachedMessageFormats.computeIfAbsent(code, k -> new HashMap<>());
            fmtMap.put(locale, new MessageFormat(message, locale));
        });
    }

    private static Locale resolveLocale(Resource resource) {
        String fileName = resource.getFilename();
        if (StringUtils.isBlank(fileName)) {
            throw new BeanInitializationException(
                "I18n message file name is Null OR Blank"
            );
        }
        Matcher matcher = MessageFilesPattern.matcher(fileName);
        if (!matcher.matches()) {
            throw new BeanInitializationException(
                MessageFormat.format("Invalid i18n message file name: {0}", fileName)
            );
        }
        return Locale.of(matcher.group(1), matcher.group(2));
    }
}
