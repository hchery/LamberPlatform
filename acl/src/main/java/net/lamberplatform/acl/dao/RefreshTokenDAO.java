package net.lamberplatform.acl.dao;

import net.lamberplatform.data.redis.RedisDAO;
import net.lamberplatform.model.bo.acl.RefreshTokenBO;
import org.springframework.stereotype.Repository;

/**
 * DATE: 2025/2/5
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Repository
public class RefreshTokenDAO extends RedisDAO<RefreshTokenBO> {
}
