package chang.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "user_role",
            JoinColumns = {@JoinColumn(name="rid",referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="uid",referencedColumnName="id")}
    )
    private Set<User> users = new HashSet<User>(0);

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameZH='" + nameZH + '\'' +
                '}';
    }
}
