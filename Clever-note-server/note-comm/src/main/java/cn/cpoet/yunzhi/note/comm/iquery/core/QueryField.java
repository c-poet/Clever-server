package cn.cpoet.yunzhi.note.comm.iquery.core;

import lombok.Data;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author CPoet
 */
@Data
public class QueryField {
    private String name;

    private Field field;

    private Method getter;

    private Annotation tarAnnotation;

    private QueryResolver resolver;
}
