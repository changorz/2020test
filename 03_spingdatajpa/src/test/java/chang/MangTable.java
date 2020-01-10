package chang;

import chang.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void save(){





    }


}
