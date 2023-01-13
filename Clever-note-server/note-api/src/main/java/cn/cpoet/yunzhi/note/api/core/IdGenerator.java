package cn.cpoet.yunzhi.note.api.core;


/**
 * id生成器
 *
 * @author CPoet
 */
public interface IdGenerator<T> {
    /**
     * 获取该id生成器名称
     *
     * @return id生成器名称
     */
    String getName();

    /**
     * 生成Id
     *
     * @return 支持类型的id
     */
    T nextId();
}
