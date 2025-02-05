package net.lamberplatform.acl.dao;

import net.lamberplatform.acl.po.AccountPO;
import net.lamberplatform.data.mongodb.MongoDAO;
import net.lamberplatform.model.bo.acl.AccountRole;
import net.lamberplatform.model.bo.acl.AccountStatus;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

/**
 * DATE: 2025/2/5
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
public interface AccountDAO extends MongoDAO<AccountPO> {

    Optional<AccountPO> findByAccount(String account);

    boolean existsByRoleAndStatus(AccountRole role, AccountStatus status);
}
