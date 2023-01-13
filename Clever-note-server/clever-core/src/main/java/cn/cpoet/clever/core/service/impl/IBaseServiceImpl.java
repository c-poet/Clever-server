package cn.cpoet.clever.core.service.impl;

import cn.cpoet.clever.annotation.core.AppContext;
import cn.cpoet.yunzhi.note.annotation.util.GenericsUtil;
import io.ebean.DB;
import io.ebean.Database;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * @author CPoet
 */
public abstract class IBaseServiceImpl<ENTITY, ID> implements InitializingBean, IBaseService<ENTITY, ID> {

    /**
     * 未直接使用{@link cn.cpoet.yunzhi.note.annotation.util.AppContextUtil}已确保AppContext-Bean已经初始化
     */
    @Autowired
    protected AppContext appContext;
    protected BaseRepository<ENTITY, ID> repository;
    protected Class<ENTITY> entityClass = GenericsUtil.getActualTypeArgClass(getClass());

    @Override
    public void afterPropertiesSet() {
        this.repository = buildRepository();
    }

    protected BaseRepository<ENTITY, ID> buildRepository() {
        Database database = appContext.getBean(Database.class);
        if (database == null) {
            database = DB.getDefault();
        }
        return new BaseRepository<>(entityClass, database);
    }

    @Override
    public Class<ENTITY> getEntityClass() {
        return entityClass;
    }

    @Override
    public BaseRepository<ENTITY, ID> getRepository() {
        return repository;
    }

    @Override
    public ENTITY findById(ID id) {
        return id == null || isPersistId(id) ? null : repository.findById(id);
    }

    @Override
    public List<ENTITY> listAll() {
        return repository.findAll();
    }


    @Override
    public int save(Collection<ENTITY> entities) {
        return CollectionUtils.isEmpty(entities) ? 0 : repository.saveAll(entities);
    }

    @Override
    public void update(ENTITY entity) {
        if (entity != null) {
            repository.update(entity);
        }
    }

    @Override
    public boolean delete(ENTITY entity) {
        return entity == null || repository.delete(entity);
    }

    @Override
    public int delete(Collection<ENTITY> beans) {
        return CollectionUtils.isEmpty(beans) ? 0 : repository.deleteAll(beans);
    }

    @Override
    public void deleteById(ID id) {
        if (id != null && !isPersistId(id)) {
            repository.deleteById(id);
        }
    }

    @Override
    public void deleteByIds(Collection<ID> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            ids.forEach(this::deleteById);
        }
    }

    @Override
    public void insert(ENTITY entity) {
        if (entity != null) {
            repository.insert(entity);
        }
    }

    @Override
    public void save(ENTITY entity) {
        if (entity != null) {
            repository.save(entity);
        }
    }

    @Override
    public boolean isPersistId(ID id) {
        return false;
    }
}
