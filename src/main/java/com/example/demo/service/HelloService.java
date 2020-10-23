package com.example.demo.service;

import com.example.demo.constant.Constant;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service("helloService")
public class HelloService {
    public boolean find_reqUrl(String  reqUrl){
        Constant.urlMap.containsKey(reqUrl);
        return   true;
    }
    public void  add_reqUrl(String  reqUrl,String  urlValue){
        Constant.urlMap.put("reqUrl","urlValue");
    }
}
