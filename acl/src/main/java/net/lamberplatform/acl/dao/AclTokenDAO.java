package net.lamberplatform.acl.dao;

import net.lamberplatform.acl.po.AclTokenPO;
import net.lamberplatform.data.mongodb.MongoDAO;

import java.util.Optional;

/**
 * DATE: 2025/1/25
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
public interface AclTokenDAO extends MongoDAO<AclTokenPO> {

    Optional<AclTokenPO> findByToken(String token);
}
