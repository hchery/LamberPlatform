package net.lamberplatform.acl.redis;

import net.lamberplatform.data.redis.RedisValueClient;
import net.lamberplatform.model.bo.acl.RefreshTokenBO;
import org.springframework.stereotype.Component;

/**
 * DATE: 2025/1/25
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Component
public class RefreshTokenClient extends RedisValueClient<RefreshTokenBO> {
}
