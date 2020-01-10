package chang.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity()
@Table(name = "role")
public class Role  implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "nameZH")
    private String nameZH;

    @ManyToMany(mappedBy = "roles" )
    private Set<User> users = new HashSet<User>(0);


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameZH() {
        return nameZH;
    }

    public void setNameZH(String nameZH) {
        this.nameZH = nameZH;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
