package cn.cpoet.clever.core.util;


import cn.cpoet.clever.constant.SystemConst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 树形封装工具
 *
 * @author CPoet
 */
public abstract class TreeUtil {
    private TreeUtil() {
    }

    /**
     * 封装
     *
     * @param entities        待封装实体列表
     * @param idFunc          id获取
     * @param pidFunc         pid获取
     * @param setChildrenFunc 子节点设置
     * @param <V>             实体类型
     * @return 封装结果
     */
    public static <V> List<V> tree(List<V> entities,
                                   Function<V, Long> idFunc,
                                   Function<V, Long> pidFunc,
                                   BiConsumer<V, List<V>> setChildrenFunc) {
        return tree(entities, idFunc, pidFunc, setChildrenFunc, SystemConst.DEFAULT_PARENT_ID);
    }

    /**
     * 封装
     *
     * @param entities        待封闭的实体列表
     * @param idFunc          id获取
     * @param pidFunc         pid获取
     * @param setChildrenFunc 子节点设置
     * @param defaultParentId 默认父级id
     * @param <V>             实体类型
     * @return 封装结果
     */
    public static <V> List<V> tree(List<V> entities,
                                   Function<V, Long> idFunc,
                                   Function<V, Long> pidFunc,
                                   BiConsumer<V, List<V>> setChildrenFunc,
                                   Long defaultParentId) {
        Map<Long, List<V>> childMap = new HashMap<>(entities.size());
        entities.forEach(item -> childMap.computeIfAbsent(pidFunc.apply(item), k -> new ArrayList<>()).add(item));
        entities.forEach(item -> setChildrenFunc.accept(item, childMap.getOrDefault(idFunc.apply(item), Collections.emptyList())));
        return childMap.getOrDefault(defaultParentId, Collections.emptyList());
    }

    /**
     * 转换且封装
     *
     * @param entities        实体列表
     * @param transformFunc   转换方法
     * @param idFunc          id获取
     * @param pidFunc         pid获取
     * @param setChildrenFunc 子节点设置
     * @param <E>             未转换前实体类型
     * @param <V>             转换后实体类型
     * @return 封装结果
     */
    public static <E, V> List<V> tree(List<E> entities,
                                      Function<E, V> transformFunc,
                                      Function<V, Long> idFunc,
                                      Function<V, Long> pidFunc,
                                      BiConsumer<V, List<V>> setChildrenFunc) {
        return tree(entities, transformFunc, idFunc, pidFunc, setChildrenFunc, SystemConst.DEFAULT_PARENT_ID);
    }

    /**
     * 转换且封装
     *
     * @param entities        实体列表
     * @param transformFunc   转换方法
     * @param idFunc          id获取
     * @param pidFunc         pid获取
     * @param setChildrenFunc 子节点设置
     * @param defaultParentId 默认父级id
     * @param <E>             未转换前实体类型
     * @param <V>             转换后实体类型
     * @return 封装结果
     */
    public static <E, V> List<V> tree(List<E> entities,
                                      Function<E, V> transformFunc,
                                      Function<V, Long> idFunc,
                                      Function<V, Long> pidFunc,
                                      BiConsumer<V, List<V>> setChildrenFunc,
                                      Long defaultParentId) {
        List<V> items = entities.stream().map(transformFunc).collect(Collectors.toList());
        return tree(items, idFunc, pidFunc, setChildrenFunc, defaultParentId);
    }
}
