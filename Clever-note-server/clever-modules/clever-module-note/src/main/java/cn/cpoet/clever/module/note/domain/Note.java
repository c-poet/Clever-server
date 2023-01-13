package cn.cpoet.clever.module.note.domain;

import cn.cpoet.yunzhi.note.domain.base.BaseModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 笔记
 *
 * @author CPoet
 */
@Data
@Entity
@Table(name = "sys_note")
public class Note extends BaseModel {

    private static final long serialVersionUID = 1444163166583603175L;

}
