package person.zhy.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class ErrorLog implements Serializable {
    private static final long serialVersionUID = -5107503085713890103L;
    private Long id;

    private String ip;

    private String uri;

    private String browser;

    private String serverIp;

    private Date createTime;

    private String des;


}