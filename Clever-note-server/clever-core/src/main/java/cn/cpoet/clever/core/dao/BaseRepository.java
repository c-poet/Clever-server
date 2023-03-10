package cn.cpoet.clever.core.dao;

import io.ebean.BeanRepository;
import io.ebean.Database;

/**
 * 基础Repository
 *
 * @author CPoet
 */
public class BaseRepository<ENTITY, ID> extends BeanRepository<ID, ENTITY> {
    protected BaseRepository(Class<ENTITY> type, Database server) {
        super(type, server);
    }
}
