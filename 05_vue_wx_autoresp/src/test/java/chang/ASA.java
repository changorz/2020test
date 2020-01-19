package chang;

import chang.model.response.QueryResponseResult;
import chang.service.WxAutoResp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})// 指定启动类
public class ASA {

    @Autowired
    private WxAutoResp wxAutoResp;

    @Test
    public void aaa(){
        //System.out.println(wxAutoResp);
        QueryResponseResult autoRespList = wxAutoResp.findAutoRespList(1, 10);
        int code = autoRespList.getCode();
        System.out.println(code);
        System.out.println(autoRespList);


    }


}
