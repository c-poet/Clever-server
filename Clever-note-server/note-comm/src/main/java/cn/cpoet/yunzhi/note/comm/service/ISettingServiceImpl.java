package cn.cpoet.yunzhi.note.comm.service;

import cn.cpoet.yunzhi.note.api.constant.SystemConst;
import cn.cpoet.yunzhi.note.api.util.JsonUtil;
import cn.cpoet.yunzhi.note.domain.base.ServiceImpl;
import cn.cpoet.yunzhi.note.domain.constant.CommStatus;
import cn.cpoet.yunzhi.note.domain.model.Setting;
import cn.cpoet.yunzhi.note.domain.model.query.QSetting;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author CPoet
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ISettingServiceImpl extends ServiceImpl<Setting> implements ISettingService {

    @Override
    public Setting findByName(String name, Long memberId) {
        return new QSetting()
            .name.eq(name)
            .memberId.eq(memberId)
            .status.eq(CommStatus.ENABLED)
            .findOne();
    }


    @Override
    public List<Setting> list(Long memberId) {
        return new QSetting()
            .memberId.eq(memberId)
            .status.eq(CommStatus.ENABLED)
            .findList();
    }


    @Override
    public List<Setting> listByName(Collection<String> names, Long memberId) {
        return new QSetting()
            .name.in(names)
            .memberId.eq(memberId)
            .status.eq(CommStatus.ENABLED)
            .findList();
    }


    @Override
    public <T> T getBean(String name, Long memberId, Class<T> clazz) {
        String content = get(name, memberId);
        if (StringUtils.hasText(content)) {
            try {
                return JsonUtil.readValue(content, clazz);
            } catch (Exception e) {
                log.warn("读取配置失败: {}", e.getMessage());
            }
        }
        return null;
    }


    @Override
    public <T> T getBean(String name, Long memberId, TypeReference<T> typeRef) {
        String content = get(name, memberId);
        if (StringUtils.hasText(content)) {
            try {
                return JsonUtil.readValue(content, typeRef);
            } catch (Exception e) {
                log.warn("读取配置失败: {}", e.getMessage());
            }
        }
        return null;
    }


    @Override
    public Setting setBean(String name, Object bean, Long memberId) {
        Setting setting = findByName(name, memberId);
        if (setting == null) {
            setting = newSetting(name, memberId);
        }
        return setBean(setting, bean);
    }


    @Override
    public Setting setBean(Setting setting, Object bean) {
        String content = null;
        if (bean != null) {
            try {
                content = JsonUtil.writeValueAsString(bean);
            } catch (Exception e) {
                log.warn("写入配置失败：{}", e.getMessage());
            }
        }
        setting.setContent(content);
        save(setting);
        return setting;
    }


    @Override
    public Setting set(String name, Object content, Long memberId) {
        Setting setting = findByName(name, memberId);
        if (setting == null) {
            setting = newSetting(name, memberId);
        }
        setting.setContent(Objects.toString(content, null));
        save(setting);
        return setting;
    }


    @Override
    public Setting setEnable(String name, Long memberId) {
        return set(name, Boolean.TRUE, memberId);
    }


    @Override
    public Setting setDisable(String name, Long memberId) {
        return set(name, null, memberId);
    }


    @Override
    public String get(String name, Long memberId) {
        Setting setting = findByName(name, memberId);
        return setting == null ? null : setting.getContent();
    }


    @Override
    public Byte getByte(String name, Long memberId) {
        String content = get(name, memberId);
        return StringUtils.hasText(content) ? Byte.valueOf(content) : null;
    }


    @Override
    public Short getShort(String name, Long memberId) {
        String content = get(name, memberId);
        return StringUtils.hasText(content) ? Short.valueOf(content) : null;
    }


    @Override
    public Integer getInt(String name, Long memberId) {
        String content = get(name, memberId);
        return StringUtils.hasText(content) ? Integer.valueOf(content) : null;
    }


    @Override
    public Long getLong(String name, Long memberId) {
        String content = get(name, memberId);
        return StringUtils.hasText(content) ? Long.valueOf(content) : null;
    }


    @Override
    public Float getFloat(String name, Long memberId) {
        String content = get(name, memberId);
        return StringUtils.hasText(content) ? Float.valueOf(content) : null;
    }


    @Override
    public Double getDouble(String name, Long memberId) {
        String content = get(name, memberId);
        return StringUtils.hasText(content) ? Double.valueOf(content) : null;
    }


    @Override
    public Boolean getBool(String name, Long memberId) {
        return isEnabled(name, memberId);
    }


    @Override
    public boolean isEnabled(String name) {
        return isEnabled(name, SystemConst.SYS_ID);
    }

    @Override
    public boolean isEnabled(String name, Long memberId) {
        String content = get(name, memberId);
        return !(content == null
            || "false".equalsIgnoreCase(content)
            || "0".equals(content)
            || "disable".equalsIgnoreCase(content)
            || "undefine".equalsIgnoreCase(content)
            || "off".equalsIgnoreCase(content)
            || "n".equalsIgnoreCase(content)
        );
    }


    @Override
    public Setting newSetting(String name, Long memberId) {
        Setting setting = new Setting();
        setting.setName(name);
        setting.setMemberId(memberId);
        setting.setStatus(CommStatus.ENABLED);
        setting.setLocked(Boolean.FALSE);
        setting.setIsBuiltIn(Boolean.FALSE);
        return setting;
    }
}
