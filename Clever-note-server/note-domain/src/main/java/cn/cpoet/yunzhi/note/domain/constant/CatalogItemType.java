package cn.cpoet.yunzhi.note.domain.constant;

import cn.cpoet.yunzhi.note.annotation.exception.EnumUndefinedException;
import cn.cpoet.yunzhi.note.annotation.util.EnumUtil;
import cn.cpoet.yunzhi.note.domain.base.BaseModel;
import cn.cpoet.yunzhi.note.domain.model.File;
import cn.cpoet.yunzhi.note.domain.model.Note;
import cn.cpoet.yunzhi.note.domain.model.Todo;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.ebean.annotation.DbEnumValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 目录项类型
 *
 * @author CPoet
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum CatalogItemType {
    /**
     * 笔记
     */
    NOTE("note", "笔记", Note.class),

    /**
     * 文件
     */
    FILE("file", "文件", File.class),

    /**
     * 待办清单
     */
    TODO("todo", "待办", Todo.class),

    /**
     * 预留
     */
    UNKNOWN("unknown", "未知", null);

    @Getter(onMethod_ = {@JsonValue, @DbEnumValue})
    private final String code;
    private final String desc;
    private final Class<? extends BaseModel> entity;

    @JsonCreator
    public static CatalogItemType ofCode(String code) {
        return EnumUtil.valueSafeOf(values(), CatalogItemType::code, code)
            .orElseThrow(EnumUndefinedException::getInstance);
    }
}
