package net.lamberplatform.acl.service;

import jakarta.annotation.Resource;
import net.lamberplatform.acl.dao.AccountDAO;
import net.lamberplatform.acl.po.AccountPO;
import net.lamberplatform.acl.po.mapper.AccountMapper;
import net.lamberplatform.data.mongodb.RefMongoDaoService;
import net.lamberplatform.model.bo.acl.AccountBO;
import net.lamberplatform.model.bo.acl.AccountRole;
import net.lamberplatform.model.bo.acl.AccountStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * DATE: 2025/2/5
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Service
public class AccountService extends RefMongoDaoService<AccountPO, AccountDAO> {

    public AccountService(AccountDAO dao) {
        super(dao);
    }

    @Resource
    private AccountMapper accountMapper;

    public Optional<AccountBO> getByAccount(String account) {
        return daoRef().findByAccount(account).map(accountMapper::po2bo);
    }

    public AccountBO save(AccountBO bo) {
        AccountPO po = accountMapper.bo2po(bo);
        return accountMapper.po2bo(daoRef().save(po));
    }

    public boolean existsUsingByRole(AccountRole role) {
        return daoRef().existsByRoleAndStatus(role, AccountStatus.USING);
    }
}
