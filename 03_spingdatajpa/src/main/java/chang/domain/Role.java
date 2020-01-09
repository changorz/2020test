package chang.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "role")
public class Role {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "nameZH")
    private String nameZH;

}
