package cn.cpoet.yunzhi.note.core.query;

import cn.cpoet.yunzhi.note.api.validator.group.Query;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.io.Serializable;

/**
 * Id查询
 *
 * @author CPoet
 */
@Data
@Schema(title = "Id查询")
@NoArgsConstructor
@AllArgsConstructor
public class IdQuery implements Serializable {

    private static final long serialVersionUID = -1807202830950585607L;

    @Schema(title = "实体id")
    @NotNull(message = "实体Id不能为空"
        , groups = {Default.class, Query.class})
    private Long id;
}
