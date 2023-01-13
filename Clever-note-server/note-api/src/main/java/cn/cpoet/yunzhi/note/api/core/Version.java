package cn.cpoet.yunzhi.note.api.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 版本
 *
 * @author CPoet
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class Version {
    /**
     * 主版本号
     */
    private final int major;

    /**
     * 次版本号
     */
    private final int minor;

    /**
     * 修正版本号
     */
    private final int revision;

    /**
     * 编译版本号
     */
    private final String build;

    /**
     * 获取可展示的版本号
     *
     * @return 版本号
     */
    public String visible() {
        return major + "." + minor + "." + revision + (build == null ? "" : " " + build);
    }

    @Override
    public String toString() {
        return visible();
    }
}
