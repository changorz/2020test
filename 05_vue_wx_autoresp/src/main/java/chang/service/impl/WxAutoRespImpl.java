package chang.service.impl;

import chang.dao.WxAutoResposeDao;
import chang.domain.WxAutoRespose;
import chang.model.response.CommonCode;
import chang.model.response.QueryResponseResult;
import chang.model.response.QueryResult;
import chang.model.response.ResponseResult;
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

    @Override
    public ResponseResult addWxAutoResp(WxAutoRespose item) {
        System.out.println(item);
        WxAutoRespose byReq = null;
        // 如果有相同的请求，覆盖以前的请求
        if(null!=item.getReq()){
            byReq = wxAutoResposeDao.findByReq(item.getReq());
        }
        if(byReq!=null){
            item.setId(byReq.getId());
        }
        //字段为空的时候回报错，提示失败（表约束）
        ResponseResult result = null;
        try {
            if("".equals(item.getReq())||"".equals(item.getResp())||!("text".equals(item.getMsgType())||"img".equals(item.getMsgType()))){
                throw new NullPointerException();
            }
            wxAutoResposeDao.save(item);
            result = ResponseResult.SUCCESS();
        }catch (Exception e){
            result = ResponseResult.FAIL();
        }
        return result;
    }

    @Override
    public ResponseResult deleteWxAutoResp(int id) {
        ResponseResult result = null;
        try {
            wxAutoResposeDao.deleteById(id);
            result = ResponseResult.SUCCESS();
        }catch (Exception e){
            result = ResponseResult.FAIL();
        }
        return result;
    }
}
