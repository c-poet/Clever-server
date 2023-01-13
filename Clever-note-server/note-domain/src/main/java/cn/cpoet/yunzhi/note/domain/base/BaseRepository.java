package cn.cpoet.yunzhi.note.domain.base;

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
