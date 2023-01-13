package cn.cpoet.yunzhi.note.core.cache;

import cn.cpoet.yunzhi.note.annotation.cache.CacheKey;
import cn.cpoet.yunzhi.note.annotation.cache.ICacheKey;
import cn.cpoet.yunzhi.note.annotation.util.AppContextUtil;
import cn.cpoet.yunzhi.note.core.component.ElExpResolver;
import cn.cpoet.yunzhi.note.annotation.constant.ElementExpEnum;
import cn.cpoet.yunzhi.note.annotation.util.DigestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/**
 * 自定义缓存Key生成器
 *
 * @author CPoet
 */
@Slf4j
public class StandardKeyGenerator implements KeyGenerator {
    /**
     * null值替换符
     */
    public final static String NULL_VALUE = "*";
    /**
     * Key分隔符
     */
    public final static String KEY_DELIMITER = ":";
    /**
     * 业务类后缀
     */
    public final static String BUS_SUFFIX_REGEX = "((Service)|(ServiceImpl)|(Dao)|(DaoImpl))$";

    private EnumMap<ElementExpEnum, ElExpResolver> elExpResolvers;

    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder sb = new StringBuilder()
            .append(getTargetName(target))
            .append(KEY_DELIMITER)
            .append(getTargetNameMd5(target))
            .append(KEY_DELIMITER)
            .append(method.getName());
        if (params.length > 0) {
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int i = 0; i < params.length; ++i) {
                sb.append(KEY_DELIMITER);
                // 是否存在缓存注解
                CacheKey cacheKey = findCacheKey(parameterAnnotations[i]);
                if (cacheKey != null) {
                    // 是否忽略该参数
                    if (cacheKey.required()) {
                        sb.append(generateKey(cacheKey, target, method, i, params));
                    }
                }
                // 是否自定义了Key生成方法
                else if (params[i] instanceof ICacheKey) {
                    sb.append(((ICacheKey) params[i]).cacheKey());
                }
                // 默认处理方式
                else {
                    sb.append(Objects.toString(params[i], NULL_VALUE));
                }
            }
        }
        return sb.toString();
    }

    private String getTargetName(Object target) {
        return target.getClass().getSimpleName().replaceAll(BUS_SUFFIX_REGEX, "");
    }

    private String getTargetNameMd5(Object target) {
        return DigestUtil.md5hex16(target.getClass().getName());
    }

    /**
     * 获取声明注解，不存在返回null
     *
     * @param annotations 注解数组
     * @return 缓存Key声明注解
     */
    private CacheKey findCacheKey(Annotation[] annotations) {
        if (annotations.length > 0) {
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == CacheKey.class) {
                    return (CacheKey) annotation;
                }
            }
        }
        return null;
    }

    /**
     * 根据缓存key注解信息生成key
     *
     * @param cacheKey 注解实例
     * @param target   目标对象
     * @param method   目标方法
     * @param index    当前方法参数下标
     * @param params   所有方法参数
     * @return 符合规则的key
     */
    private String generateKey(CacheKey cacheKey, Object target, Method method, int index, Object... params) {
        ElExpResolver resolver = getElExpResolvers().get(cacheKey.elExp());
        if (resolver == null) {
            log.info("未找到表达式解析器：{}", cacheKey.elExp());
            return NULL_VALUE;
        }
        return Objects.toString(resolver.parse(params[index], cacheKey.value()), NULL_VALUE);
    }

    /**
     * 获取解析器
     *
     * @return 获取解析器
     */
    private Map<ElementExpEnum, ElExpResolver> getElExpResolvers() {
        if (elExpResolvers == null) {
            elExpResolvers = new EnumMap<>(ElementExpEnum.class);
            try {
                ApplicationContext applicationContext = AppContextUtil.appContext().getApplicationContext();
                Map<String, ElExpResolver> beansOfType = applicationContext.getBeansOfType(ElExpResolver.class);
                if (!CollectionUtils.isEmpty(beansOfType)) {
                    for (ElExpResolver resolver : beansOfType.values()) {
                        elExpResolvers.put(resolver.getType(), resolver);
                    }
                }
            } catch (Exception e) {
                log.warn("解析器获取失败：{}", e.getMessage(), e);
            }
        }
        return elExpResolvers;
    }
}
