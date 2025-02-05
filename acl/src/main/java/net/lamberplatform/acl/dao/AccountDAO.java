package net.lamberplatform.acl.dao;

import net.lamberplatform.acl.po.AccountPO;
import net.lamberplatform.data.mongodb.MongoDAO;

import java.util.Optional;

/**
 * DATE: 2025/2/5
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
public interface AccountDAO extends MongoDAO<AccountPO> {

    Optional<AccountPO> findByAccount(String account);
}
