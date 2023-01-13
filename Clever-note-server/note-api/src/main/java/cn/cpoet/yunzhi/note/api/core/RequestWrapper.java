package cn.cpoet.yunzhi.note.api.core;

/**
 * 请求包装器
 *
 * <p>
 * 兼容{@link org.springframework.boot.WebApplicationType#REACTIVE}
 * 和{@link org.springframework.boot.WebApplicationType#SERVLET}
 * </p>
 *
 * @author CPoet
 */
public interface RequestWrapper {
    /**
     * 是否存在请求
     *
     * @return 是否存在请求
     */
    boolean requesting();

    /**
     * 获取远程主机地址
     *
     * @return 主机地址
     */
    String getRemoteAddr();

    /**
     * 获取请求头
     *
     * @param name 请求头名称
     * @return 请求头值
     */
    String getHeader(String name);

    /**
     * 获取请求参数
     *
     * @param name 参数名称
     * @return 参数值
     */
    String getParameter(String name);

    /**
     * 获取请求上下文中的属性
     *
     * @param name 属性名
     * @return 属性
     */
    Object getAttribute(String name);

    /**
     * 设置属性值
     *
     * @param name 属性名
     * @param o    属性值
     */
    void setAttribute(String name, Object o);
}
