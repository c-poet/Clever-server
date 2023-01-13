package cn.cpoet.yunzhi.note.domain.base;

import io.ebean.annotation.WhenModified;
import io.ebean.annotation.WhoModified;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 基础模型
 *
 * @author CPoet
 */
@Data
@MappedSuperclass
@Schema(title = "实体基类")
public abstract class BaseModel extends BaseRecordModel {

    private static final long serialVersionUID = -7399379738524122401L;

    /**
     * 更新人员
     */
    @WhoModified
    @Column(name = "updated_member")
    @Schema(title = "更新人员")
    private Long updatedMember;

    /**
     * 更新时间
     */
    @WhenModified
    @Column(name = "updated_time")
    @Schema(title = "更新时间")
    private LocalDateTime updatedTime;
}
