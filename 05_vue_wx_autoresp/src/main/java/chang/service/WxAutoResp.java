package chang.service;


import chang.domain.WxAutoRespose;
import chang.model.response.QueryResponseResult;
import chang.model.response.ResponseResult;

public interface WxAutoResp {
    //分页查询
    QueryResponseResult findAutoRespList(int page, int size);

    ResponseResult addWxAutoResp(WxAutoRespose ietm);

    ResponseResult deleteWxAutoResp(int id);

}
