package cn.cpoet.yunzhi.note.domain.base;

import cn.cpoet.yunzhi.note.annotation.constant.SystemConst;
import io.ebean.annotation.SoftDelete;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhoCreated;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 记录型模型
 *
 * @author CPoet
 */
@Data
@MappedSuperclass
@Schema(title = "记录型实体基类")
public abstract class BaseRecordModel implements Serializable {

    private static final long serialVersionUID = -3468290157721249462L;

    @Id
    @Column(name = "id")
    @Schema(title = "Id")
    @GeneratedValue(generator = SystemConst.GLOBAL_ID_GENERATOR)
    private Long id;

    @Hidden
    @Version
    @Column(name = "version")
    @Schema(title = "数据版本")
    private Integer version;

    /**
     * 软删除
     */
    @Hidden
    @SoftDelete
    @Schema(title = "删除标记")
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    /**
     * 创建人
     */
    @WhoCreated
    @Column(name = "created_member")
    @Schema(title = "创建人")
    private Long createdMember;

    /**
     * 创建时间
     */
    @WhenCreated
    @Column(name = "created_time")
    @Schema(title = "创建时间")
    private LocalDateTime createdTime;

    /**
     * 数据前置操作
     * <p>禁止手动调用</p>
     */
    @PrePersist
    public void prePersist() {
        // 未设置删除标识的情况下默认为false
        if (deleted == null) {
            deleted = Boolean.FALSE;
        }
    }
}
