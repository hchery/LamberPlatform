package net.lamberplatform.acl.po.mapper;

import net.lamberplatform.acl.po.AclTokenPO;
import net.lamberplatform.model.GenericMapper;
import net.lamberplatform.model.bo.acl.AclTokenBO;
import org.mapstruct.Mapper;

/**
 * DATE: 2025/1/25
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Mapper(componentModel = "spring")
public interface AclTokenMapper extends GenericMapper<AclTokenPO, AclTokenBO> {
}
