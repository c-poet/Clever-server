package cn.cpoet.yunzhi.note.api.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json工具
 *
 * @author CPoet
 */
public abstract class JsonUtil {

    public final static char JSON_ARRAY_PREFIX = '[';
    public final static char JSON_ARRAY_SUFFIX = ']';
    public final static char JSON_OBJECT_PREFIX = '{';
    public final static char JSON_OBJECT_SUFFIX = '}';

    private static ObjectMapper objectMapper;

    private JsonUtil() {
    }

    /**
     * 判断是否是json数组
     *
     * @param str 字符串
     * @return bool
     */
    public static boolean isJsonArray(CharSequence str) {
        if (StringFormatUtil.isEmpty(str)) {
            return false;
        }
        return str.charAt(0) == JSON_ARRAY_PREFIX && str.charAt(str.length() - 1) == JSON_ARRAY_SUFFIX;
    }

    /**
     * 判断是否是json对象
     *
     * @param str 字符串
     * @return bool
     */
    public static boolean isJsonObject(CharSequence str) {
        if (StringFormatUtil.isEmpty(str)) {
            return false;
        }
        return str.charAt(0) == JSON_OBJECT_PREFIX && str.charAt(str.length() - 1) == JSON_OBJECT_SUFFIX;
    }

    /**
     * 读取json
     *
     * @see ObjectMapper#readValue(byte[], Class)
     */
    public static <T> T readValue(byte[] src, Class<T> valueType) {
        try {
            return getObjectMapper().readValue(src, valueType);
        } catch (Exception e) {
            throw new JsonException(e);
        }
    }

    /**
     * 读取json
     *
     * @see ObjectMapper#readValue(byte[], TypeReference)
     */
    public static <T> T readValue(byte[] src, TypeReference<T> valueTypeRef) {
        try {
            return getObjectMapper().readValue(src, valueTypeRef);
        } catch (Exception e) {
            throw new JsonException(e);
        }
    }

    /**
     * 读取json
     *
     * @see ObjectMapper#readValue(String, Class)
     */
    public static <T> T readValue(String content, Class<T> valueType) {
        try {
            return getObjectMapper().readValue(content, valueType);
        } catch (Exception e) {
            throw new JsonException(e);
        }
    }

    /**
     * 读取json
     *
     * @see ObjectMapper#readValue(String, TypeReference)
     */
    public static <T> T readValue(String content, TypeReference<T> valueTypeRef) {
        try {
            return getObjectMapper().readValue(content, valueTypeRef);
        } catch (Exception e) {
            throw new JsonException(e);
        }
    }

    /**
     * 生成json
     *
     * @see ObjectMapper#writeValueAsString(Object)
     */
    public static String writeValueAsString(Object value) {
        try {
            return getObjectMapper().writeValueAsString(value);
        } catch (Exception e) {
            throw new JsonException(e);
        }
    }

    /**
     * 转换对象，深度
     *
     * @see ObjectMapper#convertValue(Object, Class)
     */
    public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
        try {
            return getObjectMapper().convertValue(fromValue, toValueType);
        } catch (Exception e) {
            throw new JsonException(e);
        }
    }

    /**
     * 转换对象，深度
     *
     * @see ObjectMapper#convertValue(Object, TypeReference)
     */
    public <T> T convertValue(Object fromValue, TypeReference<T> toValueTypeRef) {
        try {
            return getObjectMapper().convertValue(fromValue, toValueTypeRef);
        } catch (Exception e) {
            throw new JsonException(e);
        }
    }

    /**
     * 获取ObjectMapper
     *
     * @return ObjectMapper
     */
    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = AppContextUtil.getBean(ObjectMapper.class);
        }
        return objectMapper == null ? new ObjectMapper() : objectMapper;
    }
}
