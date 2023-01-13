package cn.cpoet.yunzhi.note.web.comm.service;

import cn.cpoet.yunzhi.note.api.auth.Subject;

import java.util.List;
import java.util.Map;

/**
 * @author CPoet
 */
public interface SettingService {

    /**
     * 获取公共配置
     *
     * @param name 配置名称
     * @return 配置
     */
    Map<String, Object> getPubSetting(List<String> name);

    /**
     * 获取个人配置
     *
     * @param subject 登录主体
     * @param name    配置名称
     * @return 配置信息
     */
    Map<String, Object> getSetting(Subject subject, List<String> name);
}
