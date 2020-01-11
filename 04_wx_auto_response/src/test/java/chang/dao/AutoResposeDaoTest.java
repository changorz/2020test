package chang.dao;

import chang.WxMpDemoApplication;
import chang.domain.WxAutoRespose;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={WxMpDemoApplication.class})// 指定启动类
public class AutoResposeDaoTest {

    @Autowired
    private WxAutoResposeDao autoResposeDao;

    @Test
    @Transactional
    @Rollback(false)//设置为不回滚
    public void saveTest(){
        WxAutoRespose wxAutoRespose = new WxAutoRespose();
        wxAutoRespose.setMsgType("text");
        wxAutoRespose.setReq("2");
        wxAutoRespose.setResp("java 666");

        autoResposeDao.save(wxAutoRespose);

    }



}
