package cn.cpoet.clever.annotation;

/**
 * 缓存Key生成
 *
 * @author CPoet
 */
public interface ICacheKey {
    /**
     * 获取缓存Key
     *
     * @return 缓存Key
     */
    String cacheKey();
}
