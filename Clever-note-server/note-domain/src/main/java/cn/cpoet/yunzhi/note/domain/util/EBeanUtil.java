package cn.cpoet.yunzhi.note.domain.util;

import io.ebean.typequery.TQProperty;
import io.ebean.typequery.TQPropertyBase;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * EBean操作相关工具
 *
 * @author CPoet
 */
public abstract class EBeanUtil {
    private EBeanUtil() {
    }

    /**
     * 获取属性名称
     *
     * @param tqProperty 属性查询信息
     * @return 属性名称
     */
    public static String getFieldName(TQProperty<?> tqProperty) {
        return tqProperty.toString();
    }

    /**
     * 按指定顺序条件做升序
     *
     * @param fields     字段列表
     * @param properties 查询属性
     */
    public static void ascFields(List<String> fields, TQPropertyBase<?>... properties) {
        if (properties.length != 0) {
            ascFields(fields, Arrays.asList(properties));
        }
    }

    /**
     * 按指定顺序条件做升序
     *
     * @param fields     字段列表
     * @param properties 查询属性
     */
    public static void ascFields(List<String> fields, Collection<TQPropertyBase<?>> properties) {
        orderFields(fields, Collections.emptyList(), properties);
    }

    /**
     * 按指定顺序条件做降序
     *
     * @param fields     字段列表
     * @param properties 查询属性
     */
    public static void descFields(List<String> fields, TQPropertyBase<?>... properties) {
        if (properties.length != 0) {
            descFields(fields, Arrays.asList(properties));
        }
    }

    /**
     * 按指定顺序条件做降序
     *
     * @param fields     字段列表
     * @param properties 查询属性
     */
    public static void descFields(List<String> fields, Collection<TQPropertyBase<?>> properties) {
        orderFields(Collections.emptyList(), fields, properties);
    }

    /**
     * 按指定顺序条件排序
     *
     * @param ascFields  升序字段列表
     * @param descFields 降序字段列表
     * @param properties 查询属性
     */
    public static void orderFields(List<String> ascFields, List<String> descFields, TQPropertyBase<?>... properties) {
        if (properties.length != 0) {
            orderFields(ascFields, descFields, Arrays.asList(properties));
        }
    }

    /**
     * 按指定顺序条件排序
     *
     * @param ascFields  升序字段列表
     * @param descFields 降序字段列表
     * @param properties 查询属性
     */
    public static void orderFields(List<String> ascFields, List<String> descFields, Collection<TQPropertyBase<?>> properties) {
        if (!CollectionUtils.isEmpty(properties)) {
            Map<String, TQPropertyBase<?>> map = properties
                .stream()
                .collect(Collectors.toMap(EBeanUtil::getFieldName, p -> p));
            if (!CollectionUtils.isEmpty(ascFields)) {
                for (String ascField : ascFields) {
                    TQPropertyBase<?> property = map.get(ascField);
                    if (property != null) {
                        property.asc();
                    }
                }
            }
            if (!CollectionUtils.isEmpty(descFields)) {
                for (String field : descFields) {
                    TQPropertyBase<?> property = map.get(field);
                    if (property != null) {
                        property.desc();
                    }
                }
            }
        }
    }
}
