package cn.cpoet.yunzhi.note.domain.constant;

import io.ebean.config.dbplatform.DbType;

/**
 * 兼容数据库类型
 *
 * @author CPoet
 */
public interface CompatibleDbTypes {
    /**
     * Oracle-Clob类型
     *
     * @see DbType#CLOB
     */
    String CLOB = "CLOB";

    /**
     * Mysql-文本类型
     */
    String TEXT = CLOB;

    /**
     * Mysql-长文本类型
     */
    String LONG_TEXT = TEXT;
}
