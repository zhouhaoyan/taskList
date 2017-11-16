package person.zhy.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
@Data
@Builder
public class User implements Serializable {

    private static final long serialVersionUID = 522167871795744377L;

    public static final Integer USER_VALID=1;

    public static final Integer USER_INVALID=0;

    private Long id;

    private String account;

    private String password;

    private String channel;

    private String nickName;

    private String des;

    private Integer status;

    private Date createTime;

    @Tolerate
    public User() {
    }
}