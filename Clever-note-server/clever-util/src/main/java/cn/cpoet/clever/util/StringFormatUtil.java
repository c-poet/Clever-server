package cn.cpoet.clever.util;

import java.util.Map;
import java.util.Objects;

/**
 * 字符串模板渲染工具
 *
 * @author Cpoet
 */
public abstract class StringFormatUtil {

    private final static StringFormat FORMAT = new StringFormat("{", "}", null);

    private StringFormatUtil() {
    }

    /**
     * 判断string是否有效
     *
     * @param s string
     * @return 无效返回true|否则false
     */
    public static boolean isEmpty(final CharSequence s) {
        return (s == null || s.length() == 0);
    }

    /**
     * @see StringFormat#format(String, Object...)
     */
    public static String format(String format, Object... param) {
        return FORMAT.format(format, param);
    }

    /**
     * @see StringFormat#format(String, Map)
     */
    public static String format(String template, Map<String, Object> models) {
        return FORMAT.format(template, models);
    }

    public static class StringFormat {
        private final String prefix;

        private final String suffix;

        private final String blank;

        /**
         * @param prefix 变量前缀
         * @param suffix 变量后缀
         * @param blank  当不存在替换值时，默认使用的占位符
         */
        public StringFormat(String prefix, String suffix, String blank) {
            if (StringFormatUtil.isEmpty(prefix) || StringFormatUtil.isEmpty(suffix)) {
                throw new AssertionError("实例化没有意义的[" + getClass().getTypeName() + "]对象.");
            }

            this.prefix = prefix;
            this.suffix = suffix;
            this.blank = blank;
        }

        /**
         * 执行渲染
         * <p>
         * <br />
         * eg: {@code name = ourhz}              <br />
         * 1. %{name}% - %ourhz%                 <br />
         * 2. %{{{name}}}% - %{{ourhz}}%         <br />
         * 3. %{other}% - %{other}%
         *
         * @param template 模板
         * @param models   替换值
         * @return 结果
         */
        public String format(String template, Map<String, Object> models) {
            if (StringFormatUtil.isEmpty(template) || models == null || models.isEmpty()) {
                return template;
            }

            final int pLen = prefix.length();
            final int sLen = suffix.length();
            final int tLen = template.length();

            StringBuilder builder = new StringBuilder(tLen + 16);

            int sIndex, sIndexLast = 0;
            int pIndex, pIndexLast;
            while (true) {
                pIndex = template.indexOf(prefix, sIndexLast);

                if (pIndex == -1) {
                    builder.append(template, sIndexLast, tLen);
                    break;
                }

                pIndexLast = pIndex + pLen;

                // 饥饿循环，直到找到连续前缀最后一个有效前缀
                while (tLen - pIndexLast > pLen && prefix.equals(template.substring(pIndexLast, pLen + pIndexLast))) {
                    pIndex = pIndexLast;
                    pIndexLast += pLen;
                }

                if (sIndexLast != pIndex) {
                    builder.append(template, sIndexLast, pIndex);
                }

                sIndex = template.indexOf(suffix, pIndexLast);

                if (sIndex == -1) {
                    builder.append(template, pIndex, tLen);
                    break;
                }

                sIndexLast = sIndex + sLen;

                final Object model = models.get(template.substring(pIndexLast, sIndex));

                if (Objects.isNull(model)) {
                    if (Objects.isNull(blank)) {
                        builder.append(template, pIndex, sIndexLast);
                    } else {
                        builder.append(blank);
                    }
                } else {
                    builder.append(model);
                }
            }

            return builder.toString();
        }

        /**
         * 字符串拼接方法
         *
         * @param format 格式
         *               <p><b>说明:</b>会按顺序替换format中{}符号,也可以指定下标{0}。使用{}的时候需要转义\\{}</p>
         * @param param  替换参数
         * @return 拼接结果
         */
        public String format(String format, Object... param) {
            if (StringFormatUtil.isEmpty(format) || param.length == 0) {
                return format;
            }
            char[] arr = format.toCharArray();
            StringBuilder builder = new StringBuilder();
            int index = 0;
            int len = format.length();
            for (int i = 0; i < len; ++i) {
                if (arr[i] == '\\' && ++i < len) {
                    builder.append(arr[i]);
                } else if (arr[i] == '{') {
                    if (i != 0 && arr[i - 1] == '\\') {
                        builder.append(arr[i]);
                    } else {
                        StringBuilder numBuilder = new StringBuilder();
                        while (++i < len && arr[i] != '}') {
                            numBuilder.append(arr[i]);
                        }
                        String numStr = numBuilder.toString();
                        int num = isEmpty(numStr) ? index++ : Integer.parseInt(numStr);
                        if (num < param.length) {
                            builder.append(param[num]);
                        } else {
                            builder.append(arr[i - 1]);
                        }
                    }
                } else {
                    builder.append(arr[i]);
                }
            }
            return builder.toString();
        }
    }
}
