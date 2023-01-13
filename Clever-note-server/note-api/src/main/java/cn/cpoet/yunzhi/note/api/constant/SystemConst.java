package cn.cpoet.yunzhi.note.api.constant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 系统级常量
 *
 * @author CPoet
 */
public interface SystemConst {
    /**
     * 系统ID
     */
    long SYS_ID = 0L;

    /**
     * 游客ID
     */
    long GUEST_ID = -1L;

    /**
     * 默认排序值
     */
    int DEFAULT_SORED = 99;

    /**
     * 默认实体ID
     */
    long DEFAULT_ENTITY_ID = SYS_ID;

    /**
     * 默认父级ID
     */
    long DEFAULT_PARENT_ID = DEFAULT_ENTITY_ID;

    /**
     * 系统前缀
     */
    String SYSTEM_PREFIX = "yunzhi";

    /**
     * 系统前缀带"-"
     */
    String SYSTEM_PREFIX_ = SYSTEM_PREFIX + "-";

    /**
     * profile: dev
     */
    String APP_PROFILE_DEV = "dev";

    /**
     * profile: test
     */
    String APP_PROFILE_TEST = "test";

    /**
     * profile: prod
     */
    String APP_PROFILE_PROD = "prod";

    /**
     * 全局ID生成器名称
     */
    String GLOBAL_ID_GENERATOR = "yunzhi@global-id-generator";

    /**
     * 全局UUID生成器名称
     */
    String GLOBAL_UUID_GENERATOR = "yunzhi@global-uuid-generator";

    /**
     * 链路跟踪Id
     */
    String TRACE_ID = "traceId";

    /**
     * 链路跨度Id
     */
    String SPAN_ID = "spanId";

    /**
     * 默认链路跨度id
     */
    int DEFAULT_SPAN_ID = 0;

    /**
     * 系统最大日期
     */
    LocalDate LOCAL_DATE_MAX = LocalDate.parse("2250-01-01", DateTimeFormatter.ISO_LOCAL_DATE);

    /**
     * 系统最小日期
     */
    LocalDate LOCAL_DATE_MIN = LocalDate.parse("2000-01-01", DateTimeFormatter.ISO_LOCAL_DATE);

    /**
     * 系统最大时间
     */
    LocalDateTime LOCAL_DATE_TIME_MAX = LocalDateTime.of(LOCAL_DATE_MAX, LocalTime.MAX);

    /**
     * 系统最小时间
     */
    LocalDateTime LOCAL_DATE_TIME_MIN = LocalDateTime.of(LOCAL_DATE_MIN, LocalTime.MIN);

    /**
     * 缓存名称：sys
     */
    String CACHE_NAMES_SYS = "sys";

    /**
     * 缓存名称：user
     */
    String CACHE_NAMES_MEMBER = "user";

    /**
     * 系统配置前缀
     */
    String SETTING_SYS_PREFIX = "_";

    /**
     * 个人配置前缀
     */
    String SETTING_PERSON_PREFIX = "@";
}
