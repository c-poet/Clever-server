package cn.cpoet.yunzhi.note.core.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @author CPoet
 */
@Data
@Schema(title = "排序详情")
public class OrderQuery implements Serializable {

    private static final long serialVersionUID = -6419729667477483417L;

    @Schema(title = "升序排序的字段")
    private List<String> ascFields;

    @Schema(title = "降序排序的字段")
    private List<String> descFields;

    /**
     * 是否存在排序条件
     *
     * @return bool
     */
    public boolean hasOrder() {
        return !CollectionUtils.isEmpty(ascFields) || !CollectionUtils.isEmpty(descFields);
    }
}
