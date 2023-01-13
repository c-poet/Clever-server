package cn.cpoet.yunzhi.note.comm.iquery.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 查询解析器管理
 *
 * @author CPoet
 */
@Slf4j
public class QueryResolveManager implements QueryBeanParser {

    public final static QueryResolveManager INSTANCE = new QueryResolveManager();

    private final Map<Class<?>, QueryBean> queryBeanCache = new ConcurrentHashMap<>();
    private final Map<Annotation, QueryResolver> resolvers = new ConcurrentHashMap<>();

    public void addResolver(QueryResolver queryResolver) {
        List<Annotation> supports = queryResolver.supports();
        if (!CollectionUtils.isEmpty(supports)) {
            for (Annotation support : supports) {
                resolvers.put(support, queryResolver);
            }
        }
    }

    @Override
    public QueryBean parseBean(Object bean) {
        // 解析实体信息
        Class<?> beanClass = bean.getClass();
        QueryBean queryBean = queryBeanCache.get(beanClass);
        if (queryBean == null) {
            queryBean = new QueryBean();
            queryBean.setClazz(beanClass);
            Field[] fields = beanClass.getDeclaredFields();
            if (fields.length > 0) {
                try {
                    List<QueryField> queryFields = new ArrayList<>();
                    for (Field field : fields) {
                        for (Map.Entry<Annotation, QueryResolver> entry : resolvers.entrySet()) {
                            Annotation annotation = field.getDeclaredAnnotation(entry.getKey().annotationType());
                            if (annotation != null) {
                                QueryField queryField = new QueryField();
                                PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), beanClass);
                                queryField.setName(descriptor.getName());
                                queryField.setField(field);
                                queryField.setGetter(descriptor.getReadMethod());
                                queryField.setTarAnnotation(annotation);
                                queryField.setResolver(entry.getValue());
                                queryFields.add(queryField);
                                break;
                            }
                        }
                    }
                    queryBean.setQueryFields(queryFields);
                } catch (Exception e) {
                    log.warn("解析失败：{}", e.getMessage(), e);
                }
            }
            queryBeanCache.put(beanClass, queryBean);
        }
        return queryBean;
    }
}
