package chang.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "wx_auto_respose")
public class WxAutoRespose implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //回复类型
    private String msgType;

    //接收消息
    private String req;

    //回复消息
    private String resp;

}
