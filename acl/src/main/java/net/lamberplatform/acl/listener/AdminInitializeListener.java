package net.lamberplatform.acl.listener;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.lamberplatform.acl.service.AccountService;
import net.lamberplatform.event.AccountInitializeEvent;
import net.lamberplatform.model.bo.acl.AccountBO;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * DATE: 2025/2/5
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Component
@Slf4j
public class AdminInitializeListener implements ApplicationListener<AccountInitializeEvent> {

    @Resource
    private AccountService accountService;

    @Override
    public void onApplicationEvent(@NonNull AccountInitializeEvent event) {
        log.info("Admin account initialize check, please wait...");
        if (accountService.getByAccount(AccountBO.Specials.ADMIN).isPresent()) {
            log.info("Admin account initialize check completed: already exists");
            return;
        }
        try {
            newAdminAccount();
        } catch (Exception ex) {
            log.error("Admin account initialize check failed: {}", ex.getMessage(), ex);
            throw ex;
        }
    }

    private void newAdminAccount() {
        AccountBO admin = new AccountBO();
        admin.setAccount(AccountBO.Specials.ADMIN);
        accountService.save(admin);
        log.info("Admin account initialize check completed: created admin account");
    }
}
