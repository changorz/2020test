package chang.service.impl;

import chang.dao.WxAutoResposeDao;
import chang.domain.WxAutoRespose;
import chang.model.response.CommonCode;
import chang.model.response.QueryResponseResult;
import chang.model.response.QueryResult;
import chang.service.WxAutoResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WxAutoRespImpl implements WxAutoResp {

    @Autowired
    private WxAutoResposeDao wxAutoResposeDao;

    @Override
    public QueryResponseResult findAutoRespList(int page, int size) {
        //分页参数
        if(page <=0){
            page = 1;
        }
        page = page -1;
        if(size<=0){
            size = 10;
        }
        Pageable pageable = PageRequest.of(page,size);
        Page<WxAutoRespose> all = wxAutoResposeDao.findAll(pageable);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(all.getContent());//数据列表
        queryResult.setTotal(all.getTotalElements());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }
}
