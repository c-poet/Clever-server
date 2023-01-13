package cn.cpoet.clever.core.util;

import cn.cpoet.clever.annotation.RequestWrapper;

/**
 * request工具
 *
 * @author CPoet
 */
public abstract class ReqsUtil {
    public static final String HEADER_USER_AGENT = "User-Agent";

    private ReqsUtil() {
    }

    /**
     * 获取UA
     *
     * @param request 请求
     * @return UA
     */
    public static String getUserAgent(RequestWrapper request) {
        return request == null ? "" : request.getHeader(HEADER_USER_AGENT);
    }

    /**
     * 获取ip地址
     *
     * @param request 请求
     * @return ip地址
     */
    @SuppressWarnings("all")
    public static String findIpAddress(RequestWrapper request) {
        if (request == null) {
            return "";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
