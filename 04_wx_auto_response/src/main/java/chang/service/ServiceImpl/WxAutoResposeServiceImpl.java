package chang.service.ServiceImpl;

import chang.dao.WxAutoResposeDao;
import chang.service.WxAutoResposeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxAutoResposeServiceImpl implements WxAutoResposeService {

    @Autowired
    WxAutoResposeDao wxAutoResposeDao;

    @Override
    public String findResposeByReq(String msgType ,String req) {
        return wxAutoResposeDao.findByMsgTypeAndReq(msgType,req).getResp();
    }
}
