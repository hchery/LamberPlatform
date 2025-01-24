package net.lamberplatform.data.mongodb;

import lombok.RequiredArgsConstructor;

/**
 * DATE: 2025/1/25
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@RequiredArgsConstructor
public abstract class RefMongoDaoService<PO, DAO extends MongoDAO<PO>> {

    private final DAO dao;

    protected DAO daoRef() {
        return dao;
    }
}
