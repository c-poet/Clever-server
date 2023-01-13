package cn.cpoet.yunzhi.note.api.core;

import cn.cpoet.yunzhi.note.api.constant.AppInfoKeys;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * App信息
 *
 * @author CPoet
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class AppInfo {
    /**
     * 应用信息文件
     */
    private final static String APP_INFO_FILE = "app.info";

    /**
     * 应用信息实例
     */
    public final static AppInfo INSTANCE = loadInstance();

    /**
     * 应用名称
     */
    private final String name;

    /**
     * 作者
     */
    private final String author;

    /**
     * 公司
     */
    private final String company;

    /**
     * 邮箱
     */
    private final String email;

    /**
     * 网站
     */
    private final String site;

    /**
     * 版本信息
     */
    private final Version version;

    /**
     * 加载应用信息
     *
     * @return 应用信息
     */
    private static AppInfo loadInstance() {
        // 加载应用相关信息，默认使用Properties格式
        Properties info = new Properties();
        ClassPathResource pathResource = new ClassPathResource(APP_INFO_FILE);
        try (InputStream in = pathResource.getInputStream()) {
            info.load(in);
        } catch (IOException ignored) {
        }
        // 应用版本信息
        String major = info.getProperty(AppInfoKeys.VERSION_MAJOR);
        String minor = info.getProperty(AppInfoKeys.VERSION_MINOR);
        String revision = info.getProperty(AppInfoKeys.VERSION_REVISION);
        String build = info.getProperty(AppInfoKeys.VERSION_BUILD);
        return new AppInfo(info.getProperty(AppInfoKeys.NAME),
            info.getProperty(AppInfoKeys.AUTHOR),
            info.getProperty(AppInfoKeys.COMPANY),
            info.getProperty(AppInfoKeys.EMAIL),
            info.getProperty(AppInfoKeys.SITE),
            new Version(major == null ? 0 : Integer.parseInt(major),
                minor == null ? 0 : Integer.parseInt(minor),
                revision == null ? 0 : Integer.parseInt(revision),
                build));
    }
}
