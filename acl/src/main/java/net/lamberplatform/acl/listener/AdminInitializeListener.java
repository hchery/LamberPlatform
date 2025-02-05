package net.lamberplatform.acl.listener;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.lamberplatform.acl.service.AccountService;
import net.lamberplatform.acl.service.AclTokenService;
import net.lamberplatform.event.AccountInitializeEvent;
import net.lamberplatform.model.bo.acl.AccountBO;
import net.lamberplatform.model.bo.acl.AccountRole;
import net.lamberplatform.model.bo.acl.AccountStatus;
import net.lamberplatform.model.bo.acl.AclTokenBO;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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

    @Resource
    private AclTokenService aclTokenService;

    @Transactional
    @Override
    public void onApplicationEvent(@NonNull AccountInitializeEvent event) {
        log.info("Admin account initialize check, please wait...");
        if (accountService.existsUsingByRole(AccountRole.ADMIN)) {
            log.info("Admin account initialize check completed: already exists");
            return;
        }
        try {
            AccountBO admin = newAdminAccount();
            newAdminAclToken(admin);
            log.info("Admin account initialize check completed: success");
        } catch (Exception ex) {
            log.error("Admin account initialize check failed: {}", ex.getMessage(), ex);
            throw ex;
        }
    }

    private AccountBO newAdminAccount() {
        AccountBO admin = new AccountBO();
        admin.setAccount(AccountBO.Specials.ADMIN);
        admin.setRole(AccountRole.ADMIN);
        admin.setStatus(AccountStatus.USING);
        log.info("Admin account initialize check: created admin account");
        return accountService.save(admin);
    }

    private void newAdminAclToken(AccountBO admin) {
        AclTokenBO aclToken = new AclTokenBO();
        aclToken.setUserId(admin.getId());
        aclToken.setToken(UUID.randomUUID().toString());
        aclTokenService.save(aclToken);
        log.info("Admin account initialize check: created acl token");
    }
}
