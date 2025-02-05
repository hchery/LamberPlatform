package net.lamberplatform.acl.po.mapper;

import net.lamberplatform.acl.po.AccountPO;
import net.lamberplatform.model.GenericMapper;
import net.lamberplatform.model.bo.acl.AccountBO;
import org.mapstruct.Mapper;

/**
 * DATE: 2025/2/5
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Mapper(componentModel = "spring")
public interface AccountMapper extends GenericMapper<AccountPO, AccountBO> {
}
