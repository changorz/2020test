package chang;

import chang.dao.UserDao;
import chang.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})// 指定启动类
public class Ddsfdsf {

    @Autowired
    private UserDao userDao;

    @Test
    public void save1(){
        User user = new User();
        List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }
    }

    @Test
    public void save(){
        User user = new User();
        user.setUsername("ashhd");
        user.setPassword("12fdfdffdfdhfdhdh456");
        userDao.save(user);

    }

    @Test
    @Transactional
    public void testUpdate() {
        User user = userDao.findById(6).get();
        System.out.println(user);
        user.setPassword("ccc666");
        System.out.println(user);
        userDao.save(user);
    }

    @Test
    public void testDelete() {
        userDao.deleteById(6);
    }

    /* 模糊查询 */
    @Test
    public void findone1(){
        Optional<User> username = userDao.findOne((root, query, cb) -> {
            return cb.like(root.get("username").as(String.class), "chang%");
        });
        System.out.println(username.get());
    }

    /* 分页查询 */
    @Test
    public void page1(){
        Page<User> username = userDao.findAll((root, query, cb) -> {
            return cb.like(root.get("username").as(String.class), "%a%");
        }, PageRequest.of(0, 5));
        username.forEach(System.out::print);
    }


}
