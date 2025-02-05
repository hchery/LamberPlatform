package net.lamberplatform.acl.service;

import jakarta.annotation.Resource;
import net.lamberplatform.acl.dao.AclTokenDAO;
import net.lamberplatform.acl.po.AclTokenPO;
import net.lamberplatform.acl.po.mapper.AclTokenMapper;
import net.lamberplatform.data.mongodb.RefMongoDaoService;
import net.lamberplatform.model.bo.acl.AclTokenBO;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * DATE: 2025/1/25
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@Service
public class AclTokenService extends RefMongoDaoService<AclTokenPO, AclTokenDAO> {

    public AclTokenService(AclTokenDAO dao) {
        super(dao);
    }

    @Resource
    private AclTokenMapper aclTokenMapper;

    public Optional<AclTokenBO> getByToken(String token) {
        return daoRef().findByToken(token).map(aclTokenMapper::po2bo);
    }

    public AclTokenBO save(AclTokenBO bo) {
        AclTokenPO po = aclTokenMapper.bo2po(bo);
        return aclTokenMapper.po2bo(daoRef().save(po));
    }
}
