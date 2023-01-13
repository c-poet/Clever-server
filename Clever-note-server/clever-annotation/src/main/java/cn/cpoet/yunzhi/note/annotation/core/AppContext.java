package cn.cpoet.yunzhi.note.annotation.core;

import cn.cpoet.yunzhi.note.annotation.auth.AuthContext;
import cn.cpoet.yunzhi.note.annotation.constant.WebAppType;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;

/**
 * 应用上下文
 *
 * @author CPoet
 */
public interface AppContext {
    /**
     * 获取应用信息
     *
     * @return 应用信息
     */
    AppInfo appInfo();

    /**
     * 获取Spring中配置的ApplicationName
     *
     * @return Application Name
     */
    String getSpName();

    /**
     * 获取当前链路信息
     *
     * @return 链路信息
     */
    TraceInfo getTraceInfo();

    /**
     * 获取当前链路信息
     *
     * @param request 请求包装
     * @return 租户信息
     */
    TraceInfo getTraceInfo(RequestWrapper request);

    /**
     * 获取认证上下文
     *
     * @return 认证上下文
     */
    AuthContext authContext();

    /**
     * 获取当前应用的类型
     *
     * @return 应用类型
     */
    WebAppType getAppType();

    /**
     * 获取全局请求包装器
     *
     * @return 全局请求包装器
     */
    RequestWrapper getRequestWrapper();

    /**
     * 获取Spring应用上下文
     *
     * @return Spring应用上下文
     */
    ApplicationContext getApplicationContext();

    /**
     * 获取配置环境信息
     *
     * @return 配置环境信息
     */
    Environment getEnvironment();

    /**
     * 获取类型转换服务
     *
     * @return 类型类型转换服务
     */
    ConversionService conversionService();

    /**
     * 获取Bean
     *
     * @param clazz Bean Class
     * @param <T>   Bean类型
     * @return 存在则返回实例，不存在则返回Null
     */
    <T> T getBean(Class<T> clazz);

    /**
     * 获取Bean
     *
     * @param name  指定Bean名称
     * @param clazz Bean Class
     * @param <T>   Bean类型
     * @return 存在返回实例，否则返回null
     */
    <T> T getBean(String name, Class<T> clazz);

    /**
     * 获取应用配置
     *
     * @param key 配置名
     * @return 配置值
     */
    String getProperty(String key);

    /**
     * 获取应用配置
     *
     * @param key          配置名
     * @param defaultValue 默认值
     * @return 配置值
     */
    String getProperty(String key, String defaultValue);

    /**
     * 获取应用配置
     *
     * @param key   配置名
     * @param clazz 类型Class
     * @param <T>   类型
     * @return 配置值
     */
    <T> T getProperty(String key, Class<T> clazz);

    /**
     * 获取应用配置
     *
     * @param key          配置名
     * @param clazz        类型Class
     * @param defaultValue 默认值
     * @param <T>          类型
     * @return 配置值
     */
    <T> T getProperty(String key, Class<T> clazz, T defaultValue);
}
