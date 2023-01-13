package cn.cpoet.yunzhi.note.core.configuration;

import cn.cpoet.yunzhi.note.core.cache.StandardKeyGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;

/**
 * 缓存配置
 *
 * @author CPoet
 */
@RequiredArgsConstructor
@ConditionalOnClass(name = {"org.springframework.data.redis.cache.RedisCacheConfiguration"})
public class CacheConfig extends CachingConfigurerSupport {

    @Override
    public KeyGenerator keyGenerator() {
        return new StandardKeyGenerator();
    }
}
