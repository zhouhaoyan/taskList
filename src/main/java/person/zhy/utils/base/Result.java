package person.zhy.utils.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int SUCCESS_CODE = 1;

    public static final int FAIL_CODE = 0;

    /**
     * 状态码：0-失败 1-成功
     */
    private int code = SUCCESS_CODE;

    /**
     * 返回信息
     */
    private String message = "success";

    /**
     * 返回数据
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    /**
     * token信息
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;

    /**
     * api接口版本
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String version;

    public Result() {
        super();
    }

    public Result(T data) {
        super();
        this.data = data;
    }

    public Result(Throwable e) {
        super();
        this.message = e.toString();
        this.code = FAIL_CODE;
    }




}