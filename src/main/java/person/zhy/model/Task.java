package person.zhy.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.util.Date;
@Data
@Builder
public class Task implements Serializable {
    private static final long serialVersionUID = -3034857197343767912L;

    public static final Integer TASK_VALID=1;

    public static final Integer TASK_INVALID=0;

    private Long id;

    private Long userId;

    private String name;

    private String des;

    private Date createTime;

    private Date updateTime;

    private Date finishTime;

    private Integer status;

    @Tolerate
    public Task() {
    }
}