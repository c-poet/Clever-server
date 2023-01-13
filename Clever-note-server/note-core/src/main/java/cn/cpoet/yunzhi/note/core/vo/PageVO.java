package cn.cpoet.yunzhi.note.core.vo;

import cn.cpoet.yunzhi.note.api.constant.Status;
import cn.cpoet.yunzhi.note.api.util.AppContextUtil;
import cn.cpoet.yunzhi.note.core.constant.CommReqsStatus;
import cn.cpoet.yunzhi.note.core.exception.CoreException;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperties;
import io.swagger.v3.oas.annotations.media.SchemaProperty;

import java.util.Collections;
import java.util.List;

/**
 * 分页查询返回结果
 *
 * @author CPoet
 */
@SuppressWarnings("all")
@SchemaProperties({
    @SchemaProperty(name = PageVO._PAGE_KEY, schema = @Schema(title = "当前页号", implementation = Integer.class)),
    @SchemaProperty(name = PageVO._CUR_KEY, schema = @Schema(title = "当前返回的数据量", implementation = Integer.class)),
    @SchemaProperty(name = PageVO._SIZE_KEY, schema = @Schema(title = "查询数据量", implementation = Integer.class)),
    @SchemaProperty(name = PageVO._TOTAL_KEY, schema = @Schema(title = "满足条件的数据总量", implementation = Integer.class)),
    @SchemaProperty(name = PageVO._HASNEXT_KEY, schema = @Schema(title = "是否存在更多数据", implementation = Boolean.class))
})
public class PageVO<T> extends ResultVO<List<T>> {

    private static final long serialVersionUID = 597480184918264325L;

    public final static String _PAGE_KEY = "page";
    public final static String _CUR_KEY = "cur";
    public final static String _SIZE_KEY = "size";
    public final static String _TOTAL_KEY = "total";
    public final static String _HASNEXT_KEY = "hasNext";

    /**
     * 空分页数据
     *
     * @param page  页号
     * @param size  每页大小
     * @param total 数据总量
     * @param <T>   数据类型
     * @return 空分页对象
     */
    public static <T> PageVO<T> of(int page, int size, long total) {
        return of(Collections.emptyList(), page, size, total);
    }

    /**
     * 分页对象
     *
     * @param data  数据列表
     * @param page  页号
     * @param size  每页大小
     * @param total 数据总量
     * @param <T>   数据类型
     * @return 分页对象
     */
    public static <T> PageVO<T> of(List<T> data, int page, int size, long total) {
        return of(CommReqsStatus.SUCCESS, data, page, size, total);
    }

    /**
     * 分页对象
     *
     * @param status 响应状态
     * @param data   数据列表
     * @param page   页号
     * @param size   每页大小
     * @param total  数据总量
     * @param <T>    数据对象
     * @return 分页对象
     */
    public static <T> PageVO<T> of(Status status, List<T> data, int page, int size, long total) {
        return ofImpl(status.code(), status.message(), data, page, size, total);
    }

    /**
     * 分页对象
     *
     * @param status  响应状态
     * @param message 自定义提示信息
     * @param data    数据列表
     * @param page    页号
     * @param size    每页大小
     * @param total   总数
     * @param <T>     数据类型
     * @return 分页对象
     */
    public static <T> PageVO<T> of(Status status, String message, List<T> data, int page, int size, long total) {
        return ofImpl(status.code(), message, data, page, size, total);
    }

    private static <T> PageVO<T> ofImpl(int status, String message, List<T> data, int page, int size, long total) {
        if (page <= 0 || size <= 0 || total < 0) {
            throw new CoreException("分页信息有误[page={}, size={}, total={}]", page, size, total);
        }
        PageVO<T> pageVo = new PageVO<>();
        pageVo.setCode(status);
        pageVo.setMessage(message);
        if (data != null) {
            pageVo.setData(data);
        }
        pageVo.setTimestamp(System.currentTimeMillis());
        // 分页信息
        pageVo.put(_PAGE_KEY, page);
        pageVo.put(_SIZE_KEY, size);
        pageVo.put(_TOTAL_KEY, total);
        pageVo.put(_CUR_KEY, data.size());
        pageVo.put(_HASNEXT_KEY, total % size > page);
        // 填充链路信息
        pageVo.put(_TRACE_ID, AppContextUtil.getTraceId());
        pageVo.put(_SPAN_ID, AppContextUtil.getSpanId());
        return pageVo;
    }
}
