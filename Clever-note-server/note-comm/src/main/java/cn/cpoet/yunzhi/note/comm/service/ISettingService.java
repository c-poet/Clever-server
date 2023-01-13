package cn.cpoet.yunzhi.note.comm.service;

import cn.cpoet.yunzhi.note.domain.base.BaseService;
import cn.cpoet.yunzhi.note.domain.model.Setting;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Collection;
import java.util.List;

/**
 * @author CPoet
 */
public interface ISettingService extends BaseService<Setting> {

    /**
     * 根据名称查询配置
     *
     * @param name     配置名称
     * @param memberId 人员id
     * @return 配置
     */
    Setting findByName(String name, Long memberId);

    /**
     * 获取所有配置列表
     *
     * @param memberId 人员id
     * @return 配置列表
     */
    List<Setting> list(Long memberId);

    /**
     * 查询配置
     *
     * @param names    指定配置名称
     * @param memberId 人员id
     * @return 配置列表
     */
    List<Setting> listByName(Collection<String> names, Long memberId);

    /**
     * 获取配置
     *
     * @param name     配置名称
     * @param memberId 人员id
     * @param clazz    配置反序列化类型
     * @param <T>      配置类型
     * @return 配置
     */
    <T> T getBean(String name, Long memberId, Class<T> clazz);

    /**
     * 获取配置
     *
     * @param name     配置名称
     * @param memberId 人员id
     * @param typeRef  反序列化类型
     * @param <T>      配置类型
     * @return 配置
     */
    <T> T getBean(String name, Long memberId, TypeReference<T> typeRef);

    /**
     * 设置配置项
     *
     * @param name     配置名称
     * @param bean     配置内容
     * @param memberId 人员id
     * @return 配置
     */
    Setting setBean(String name, Object bean, Long memberId);

    /**
     * 设置配置项
     *
     * @param setting 待持久化配置
     * @param bean    新的配置内容
     * @return 配置
     */
    Setting setBean(Setting setting, Object bean);

    /**
     * 设置配置值
     *
     * @param name     配置名称
     * @param content  配置内容
     * @param memberId 人员id
     * @return 配置
     */
    Setting set(String name, Object content, Long memberId);

    /**
     * 设置开关型配置值为开启
     *
     * @param name     配置名称
     * @param memberId 人员id
     * @return 配置
     */
    Setting setEnable(String name, Long memberId);

    /**
     * 设置开关型配置值为关闭
     *
     * @param name     配置名称
     * @param memberId 人员id
     * @return 配置
     */
    Setting setDisable(String name, Long memberId);

    /**
     * 获取string类型配置值
     *
     * @param name     配置名称
     * @param memberId 人员id
     * @return 配置值
     */
    String get(String name, Long memberId);

    /**
     * 获取byte类型配置值
     *
     * @param name     配置名称
     * @param memberId 人员id
     * @return 配置值
     */
    Byte getByte(String name, Long memberId);

    /**
     * 获取short类型配置值
     *
     * @param name     配置名称
     * @param memberId 人员id
     * @return 配置值
     */
    Short getShort(String name, Long memberId);

    /**
     * 获取int类型配置值
     *
     * @param name     配置名称
     * @param memberId 人员id
     * @return 配置值
     */
    Integer getInt(String name, Long memberId);

    /**
     * 获取long类型配置值
     *
     * @param name     配置名称
     * @param memberId 人员id
     * @return 配置值
     */
    Long getLong(String name, Long memberId);

    /**
     * 获取float类型配置值
     *
     * @param name     配置名称
     * @param memberId 人员id
     * @return 配置值
     */
    Float getFloat(String name, Long memberId);

    /**
     * 获取double类型配置值
     *
     * @param name     配置名称
     * @param memberId 人员id
     * @return 配置值
     */
    Double getDouble(String name, Long memberId);

    /**
     * 获取bool类型配置值
     *
     * @param name     配置名称
     * @param memberId 人员id
     * @return 配置值
     */
    Boolean getBool(String name, Long memberId);

    /**
     * 开关断定
     *
     * @param name 配置名
     * @return bool
     */
    boolean isEnabled(String name);

    /**
     * 开关断定
     *
     * @param name     配置名
     * @param memberId 用户id
     * @return bool
     */
    boolean isEnabled(String name, Long memberId);

    /**
     * 创建配置对象
     *
     * @param name     配置名称
     * @param memberId 用户id
     * @return 配置实例
     */
    Setting newSetting(String name, Long memberId);
}
