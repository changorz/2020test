package chang;

import chang.dao.RoleDao;
import chang.dao.UserDao;
import chang.domain.Role;
import chang.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Set;

/*

@OneToMany:
   	作用：建立一对多的关系映射
    属性：
    	targetEntityClass：指定多的多方的类的字节码
    	mappedBy：指定从表实体类中引用主表对象的名称。
    	cascade：指定要使用的级联操作
    	fetch：指定是否采用延迟加载
    	orphanRemoval：是否使用孤儿删除

@ManyToOne
    作用：建立多对一的关系
    属性：
    	targetEntityClass：指定一的一方实体类字节码
    	cascade：指定要使用的级联操作
    	fetch：指定是否采用延迟加载
    	optional：关联是否可选。如果设置为false，则必须始终存在非空关系。

@JoinColumn
     作用：用于定义主键字段和外键字段的对应关系。
     属性：
    	name：指定外键字段的名称
    	referencedColumnName：指定引用主表的主键字段名称
    	unique：是否唯一。默认值不唯一
    	nullable：是否允许为空。默认值允许。
    	insertable：是否允许插入。默认值允许。
    	updatable：是否允许更新。默认值允许。
    	columnDefinition：列的定义信息。



    	通过保存的案例，我们可以发现在设置了双向关系之后，会发送两条insert语句，
    	一条多余的update语句，那我们的解决是思路很简单，就是一的一方放弃维护权
	   放弃外键维护权的配置将如下配置改为

@OneToMany(targetEntity=LinkMan.class)
@JoinColumn(name="lkm_cust_id",referencedColumnName="cust_id")
设置为
@OneToMany(mappedBy="customer")


删除操作的说明如下：
删除从表数据：可以随时任意删除。
删除主表数据：
	有从表数据
          1、在默认情况下，它会把外键字段置为null，然后删除主表数据。如果在数据库的表
             结构上，外键字段有非空约束，默认情况就会报错了。
          2、如果配置了放弃维护关联关系的权利，则不能删除（与外键字段是否允许为null
          ，没有关系）因为在删除时，它根本不会去更新从表的外键字段了。
          3、如果还想删除，使用级联删除引用
        	没有从表数据引用：随便删

        级联操作：指操作一个对象同时操作它的关联对象
        使用方法：只需要在操作主体的注解上配置cascade

	 cascade:配置级联操作
	 		CascadeType.MERGE	级联更新
	  		CascadeType.PERSIST	级联保存：
	  		CascadeType.REFRESH 级联刷新：
	  		CascadeType.REMOVE	级联删除：
	  		CascadeType.ALL		包含所有
@OneToMany(mappedBy="customer",cascade=CascadeType.ALL)

 */



@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})// 指定启动类
public class MangTable {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    /*
    @ManyToMany
	作用：用于映射多对多关系
	属性：
		cascade：配置级联操作。
		fetch：配置是否采用延迟加载。
    	targetEntity：配置目标的实体类。映射多对多的时候不用写。

    @JoinTable
        作用：针对中间表的配置
        属性：
            nam：配置中间表的名称
            joinColumns：中间表的外键字段关联当前实体类所对应表的主键字段
            inverseJoinColumn：中间表的外键字段关联对方表的主键字段

    @JoinColumn
        作用：用于定义主键字段和外键字段的对应关系。
        属性：
            name：指定外键字段的名称
            referencedColumnName：指定引用主表的主键字段名称
            unique：是否唯一。默认值不唯一
            nullable：是否允许为空。默认值允许。
            insertable：是否允许插入。默认值允许。
            updatable：是否允许更新。默认值允许。
            columnDefinition：列的定义信息。
     */

    @Test
    @Transactional
    @Rollback(false)//设置为不回滚
    public void save(){
        User user = new User();
        Role role1 = roleDao.findById(1).get();
        Role role2 = roleDao.findById(2).get();

        System.out.println(role1);
        System.out.println(role2);

        user.getRoles().add(role1);
        user.getRoles().add(role2);


        roleDao.save(role1);
        roleDao.save(role2);
        userDao.save(user);

        /*
        User u1 = new User();
        u1.setUsername("用户1");
        Role r1 = new Role();
        r1.setNameZH("角色1");
        //建立关联关系
        u1.getRoles().add(r1);
        r1.getUsers().add(u1);
        //保存
        roleDao.save(r1);
        userDao.save(u1);


         */
    }


    @Test
    @Transactional
    @Rollback(false)//设置为不回滚
    public void testDelete() {
        //userDao.deleteById(7);
        userDao.deleteById(17);
        //userDao.deleteById(9);
    }

    @Test
    @Transactional
    public void testFind() {
        User customer = userDao.findById(18).get();
        Set<Role> linkMans = customer.getRoles();//对象导航查询
        for (Role linkMan : linkMans) {
            System.out.println(linkMan);
        }

    }


    /*

	在客户对象的@OneToMany注解中添加fetch属性
	 		FetchType.EAGER	：立即加载
	  		FetchType.LAZY	：延迟加载

    @OneToMany(mappedBy="customer",fetch=FetchType.EAGER)
    private Set<LinkMan> linkMans = new HashSet<>(0);

     */








}
