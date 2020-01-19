package chang.dao;

import chang.domain.WxAutoRespose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WxAutoResposeDao extends JpaRepository<WxAutoRespose,Integer>, JpaSpecificationExecutor<WxAutoRespose> {

    WxAutoRespose findByMsgTypeAndReq(String msgType , String req);


    WxAutoRespose findByReq(String req);
}
