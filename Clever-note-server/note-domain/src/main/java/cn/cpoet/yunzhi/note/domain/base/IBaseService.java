package cn.cpoet.yunzhi.note.domain.base;

import java.util.Collection;
import java.util.List;

/**
 * @author CPoet
 */
public interface IBaseService<ENTITY, ID> {

    /**
     * 获取实例对应的实体类型
     *
     * @return 实体类型
     */
    Class<ENTITY> getEntityClass();

    /**
     * 获取实例对应的Repository对象
     *
     * @return repository对象
     */
    BaseRepository<ENTITY, ID> getRepository();

    /**
     * 根据id查询
     *
     * @param id 实体id
     * @return 查询结果
     */
    ENTITY findById(ID id);

    /**
     * 获取所有实体
     *
     * @return 实体列表
     */
    List<ENTITY> listAll();

    /**
     * 保存实体
     *
     * @param entity 实体
     */
    void save(ENTITY entity);

    /**
     * 批量保存
     *
     * @param entities 实体集合
     * @return 成功条数
     */
    int save(Collection<ENTITY> entities);

    /**
     * 更新
     *
     * @param entity 实体
     */
    void update(ENTITY entity);

    /**
     * 插入
     *
     * @param entity 实体
     */
    void insert(ENTITY entity);

    /**
     * 删除
     *
     * @param entity 实体
     * @return bool
     */
    boolean delete(ENTITY entity);

    /**
     * 批量删除
     *
     * @param beans 实体集合
     * @return 成功条数
     */
    int delete(Collection<ENTITY> beans);

    /**
     * 根据id删除
     *
     * @param id 实体id
     */
    void deleteById(ID id);

    /**
     * 批量删除
     *
     * @param ids id列表
     */
    void deleteByIds(Collection<ID> ids);

    /**
     * 判断是否是内置id
     *
     * @param id id值
     * @return bool
     */
    boolean isPersistId(ID id);
}
