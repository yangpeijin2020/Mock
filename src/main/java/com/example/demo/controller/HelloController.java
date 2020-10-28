package com.example.demo.controller;

import com.example.demo.bean.RecDto;
import com.example.demo.bean.configDto;
import com.example.demo.constant.Constant;
import com.example.demo.service.HelloService;
import com.example.demo.utils.Util;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {

    Logger logger= LoggerFactory.getLogger(HelloController.class);
    @Autowired
    configDto configDto;
    @Autowired
    HelloService helloService;

    @RequestMapping({"/","/index.html"})
    public  String  index(){
        return   "index";
    }

    /*
       以String类型接受json数据，然后判断是否在map里存在
       存在则返回json
       不存在 则返回   不存在
     */
    @RequestMapping(value ="/wms",produces = "application/json;charset=UTF-8")
    public void  returnStr(@RequestBody  String  jsonParam, HttpServletResponse  response, HttpServletRequest  request) throws IOException {
        String  reqUrl= (String) request.getAttribute("url");
        logger.info("jsonParam:"+jsonParam+",reqUrl:"+reqUrl);
        JSONObject jsonObject=Util.strTojson(jsonParam);
      /*  Map<String,String> map_containerNo=new HashMap<String, String>();
        if(jsonParam.indexOf("containerNo")>-1){
            map_containerNo.put("containerNo",jsonObject.getString("containerNo"));
        }*/
        boolean  b= Constant.urlMap.containsKey(reqUrl);
        if (b){
            logger.info("============url存在");
            response.setCharacterEncoding("UTF-8");
//            response.getWriter().write(Constant.urlMap.get(reqUrl).toString());
            response.getWriter().write(helloService.getMatcherANDreplace(Constant.urlMap.get(reqUrl).toString(),jsonObject));
        }else {
            response.getWriter().write("url不存在，请配置");
        }
//        RecDto  recDto= JSON.parseObject(jsonParam,RecDto.class);
//        logger.info("receive:"+recDto);

    }

    /*
      添加 url  到Map里；key为 url内容，value为  response内容
     */
    @ResponseBody
    @RequestMapping("/addUrl")
    public  void  addUrl(@RequestBody JSONObject  body, HttpServletRequest  request,HttpServletResponse  response) throws IOException {
        logger.info("addUrl========"+body.toString()+"name="+body.getString("name")+"value:"+body.getString("value"));
        if(body!=null ){
            helloService.add_reqUrl(body.getString("name"),body.getString("value"));
            for (Object m: Constant.urlMap.entrySet()){
                logger.info("========"+m.toString());
            }
            logger.info("添加成功,url="+body.getString("name"));
            response.getWriter().write("success");
//            response.getWriter().write("url不存在，请配置");
        }
    }


    @ResponseBody
    @RequestMapping(value="/hello")
    public void hello(@RequestBody RecDto  recDto, HttpServletResponse  response) throws IOException {
        logger.info("============map:"+recDto.toString());
        logger.info("============map:"+configDto.getUrl());

        response.setCharacterEncoding("UTF-8");
        StringBuffer  stringBuffer=new StringBuffer();
        stringBuffer.append("");

        String result="{\"code\":\"S\",\"msg\":\"获取资料！\"," +
                "\"success\":\"true\",\"data\":\"[{\\\"attribute01\\\":\\\"00EC004116\\\"," +
                "\\\"attribute02\\\":\\\"C0171114000240\\\",\\\"attribute03\\\":\\\"\\\"," +
                "\\\"attribute04\\\":\\\"\\\",\\\"attribute05\\\":\\\"\\\"," +
                "\\\"attribute06\\\":\\\"\\\",\\\"attribute07\\\":\\\"\\\"," +
                "\\\"attribute08\\\":\\\"\\\",\\\"attribute09\\\":\\\"\\\"," +
                "\\\"attribute10\\\":\\\"\\\",\\\"binCode\\\":\\\"\\\",\\\"brand\\\":\\\"MICROCHIP\\\"," +
                "\\\"cId\\\":\\\"2CC0171114000240\\\",\\\"containerNo\\\":\\\"102009\\\"," +
                "\\\"containerType\\\":\\\"C\\\",\\\"coo\\\":\\\"CN\\\"," +
                "\\\"csCapacity\\\":0,\\\"currency\\\":\\\"\\\",\\\"customerId\\\":\\\"\\\"," +
                "\\\"customerLot\\\":\\\"\\\",\\\"dataSource\\\":\\\"ERP\\\"," +
                "\\\"dateCode\\\":\\\"1445\\\",\\\"erpInv\\\":\\\"NORMAL-HMA\\\"," +
                "\\\"erpOrg\\\":\\\"ID1\\\",\\\"expiryDate\\\":\\\"1900-01-01 00:00:00\\\"," +
                "\\\"firstOwnerID\\\":\\\"\\\",\\\"firstReceiptDate\\\":\\\"2019-01-10 20:24:52\\\"," +
                "\\\"hsFlag\\\":\\\"\\\",\\\"hue\\\":\\\"\\\",\\\"iipCapacity\\\":0," +
                "\\\"ipCapacity\\\":0,\\\"lockNo\\\":\\\"\\\",\\\"ownerId\\\":\\\"5000\\\"," +
                "\\\"pledgeId\\\":\\\"\\\",\\\"product_line_id\\\":\\\"0076\\\"," +
                "\\\"qty\\\":15,\\\"receiptDate\\\":\\\"2019-01-10 20:25:00\\\",\\\"receiptId\\\":\\\"EC转仓\\\",\\\"receiptType\\\":\\\"\\\",\\\"revision\\\":\\\"\\\",\\\"skuId\\\":\\\"ATMXT336T-MAU\\\",\\\"status\\\":\\\"OK\\\",\\\"uId\\\":\\\"2UC0171114000241\\\",\\\"unitPrice\\\":\\\"0\\\",\\\"vendorDateCode\\\":\\\"1445\\\",\\\"vendorId\\\":\\\"\\\",\\\"vendorLot\\\":\\\"#\\\",\\\"vendorPack\\\":\\\"\\\",\\\"vendorSerialNo\\\":\\\"\\\",\\\"whId\\\":\\\"WH2\\\"}]\",\"traceId\":\"outbound-7f9668c526f143db852d9faa4d6039d7\",\"method\":\"getEaReceiveTask\",\"businessId\":\"\",\"userName\":\"ARSR_JD\"}";
        response.getWriter().write(result);
    }
}
