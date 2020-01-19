package chang.controller;

import chang.domain.WxAutoRespose;
import chang.model.response.QueryResponseResult;
import chang.model.response.ResponseResult;
import chang.service.ExcelUtils;
import chang.service.WxAutoResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/wx/aoturesp")
public class AutoRespController {

    @Autowired
    private ExcelUtils excelUtils;

    @Autowired
    private WxAutoResp wxAutoResp;

    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size")int size){
        return wxAutoResp.findAutoRespList(page,size);
    }

    @GetMapping("/delete/{id}")
    public ResponseResult deleteWxAutoResp(@PathVariable("id") int id){
        System.out.println(id);
        return wxAutoResp.deleteWxAutoResp(id);
    }

    @PostMapping("/add")
    public ResponseResult addWxAutoResp(@RequestBody WxAutoRespose wxAutoRespose){
        System.out.println(wxAutoRespose);
        return wxAutoResp.addWxAutoResp(wxAutoRespose);
    }

    @PostMapping("/addexcel")
    public ResponseResult addExcel(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {
            return ResponseResult.FAIL();
        }
        InputStream inputStream = null;
        try {
            inputStream= file.getInputStream();
        } catch (IOException e) {
            return ResponseResult.FAIL();
        }
        List<List<String>> result;
        result = excelUtils.writeWithoutHead(inputStream);
        //System.out.println("读取结果：" + result);
        result.forEach(list -> {
            //System.out.println(list);
            WxAutoRespose wx = new WxAutoRespose();
            wx.setMsgType("text");
            wx.setReq(list.get(0));
            wx.setResp(list.get(1));
            wxAutoResp.addWxAutoResp(wx);
        });
        return ResponseResult.SUCCESS();
    }

}
