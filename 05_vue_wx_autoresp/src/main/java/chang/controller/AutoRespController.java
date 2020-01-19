package chang.controller;

import chang.model.response.QueryResponseResult;
import chang.service.WxAutoResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/aoturesp")
public class AutoRespController {

    @Autowired
    private WxAutoResp wxAutoResp;

    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size")int size){
        return wxAutoResp.findAutoRespList(page,size);
    }


}
