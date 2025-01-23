package net.lamberplatform.data.mongodb;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * DATE: 2025/1/23
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@NoRepositoryBean
public interface MongoDAO<T> extends CrudRepository<T, String> {
}
