package cn.cpoet.yunzhi.note.core.query;

import cn.cpoet.yunzhi.note.api.validator.group.Query;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.util.List;

/**
 * @author CPoet
 */
@Data
@Schema(title = "Id列表查询")
@NoArgsConstructor
@AllArgsConstructor
public class IdListQuery implements Serializable {

    private static final long serialVersionUID = 578279773179759623L;

    @Schema(title = "实体id")
    @NotNull(message = "实体Id不能为空"
        , groups = {Default.class, Query.class})
    @NotEmpty(message = "至少传入一个id值"
        , groups = {Default.class, Query.class})
    private List<Long> id;
}
