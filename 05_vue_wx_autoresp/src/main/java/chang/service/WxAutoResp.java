package chang.service;


import chang.model.response.QueryResponseResult;

public interface WxAutoResp {
    //分页查询
    QueryResponseResult findAutoRespList(int page, int size);




}
