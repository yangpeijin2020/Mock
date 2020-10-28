package com.example.demo.service;

import com.example.demo.constant.Constant;
import com.example.demo.utils.Util;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class HelloService {
    Logger logger= LoggerFactory.getLogger(HelloService.class);
    //判断是否存在
    public boolean find_reqUrl(String  reqUrl){
        if (Constant.urlMap.containsKey(reqUrl)){
            return   true;
        }
        return false;
    }
    //添加url
    public void  add_reqUrl(String  reqUrl,String  urlValue){
        Constant.urlMap.put(reqUrl,urlValue);
    }

    //截取requrl请求json中的国定值，如container_no
    public   Map<String,String>   get_Req_param(String  reqUrl_json,String   container){
        Map<String ,String>  map_containerNo =new HashMap<String,String>();
        String result="";
        Pattern pattern = Pattern.compile("\\{(.*?)}");
        Matcher matcher = pattern.matcher(reqUrl_json);
        while (matcher.find()) {
            result = matcher.group(1);
            if(result.equals(container)){
                map_containerNo.put(container,result);
            }
        }
        return  map_containerNo;
    }

    /**
     * 提取｛｝里面的变量 String   str="my name  is {name} ,age  is {age}";
     * 并且从  urlMap里取出name值替换掉,返回string
     * uid  cid  是自动生成的  ，所以if 排除
     * JosnObject 是请求参数，这样做可以实现传什么参数，替换什么值，如：qty：20，containerNo  "2323233"
     */
    public String getMatcherANDreplace (String  str, JSONObject  jsonObject) {
//        String   str="my name  is {name} ,age  is {age}";
        int i=0;
        String  result="";
        String[]  newStr=new  String[10];
        //提取${}里面的值
//        Pattern pattern = Pattern.compile("\\$\\{(.*?)}");
        //提取json{} 里面的值
        Pattern pattern = Pattern.compile("\\$\\{(.*?)}");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            result = matcher.group(1);
            System.out.println(result);
            /**
             * newStr  数组用例存放替换后的str
             * 首先判断i==0,如果等于0，直接替换，不等于0，先把str[i]=str[i-1],然后再替换
             * 如果是uuid  和 cid  的话，会自动生成随机的uuid和cid
             */
            if(i==0){
                if (result.toLowerCase().equals("uid")){
                    newStr[i]=str.replaceFirst("\\$\\{[^}]*\\}", Util.getUuid());
                    System.out.println(newStr[i]);
                }else if(result.toLowerCase().equals("cid")){
                    newStr[i]=str.replaceFirst("\\$\\{[^}]*\\}",Util.getCid());
                    System.out.println(newStr[i]);
                }else{
                    newStr[i]=str.replaceFirst("\\$\\{[^}]*\\}",jsonObject.getString(result));
                }
            }else {
                if (result.toLowerCase().equals("uid")){
                    newStr[i]=newStr[i-1];
                    newStr[i]=newStr[i-1].replaceFirst("\\$\\{[^}]*\\}",Util.getUuid());
                    System.out.println(newStr[i]);
                }else if(result.toLowerCase().equals("cid")){
                    newStr[i]=newStr[i-1];
                    newStr[i]=newStr[i-1].replaceFirst("\\$\\{[^}]*\\}",Util.getCid());
                    System.out.println(newStr[i]);
                }else{
                    newStr[i]=newStr[i-1];
                    newStr[i]=newStr[i-1].replaceFirst("\\$\\{[^}]*\\}",jsonObject.getString(result));
                }
//                newStr[i]=newStr[i-1];
//                newStr[i]=newStr[i-1].replaceFirst("\\{[^}]*\\}",Constant.urlMap.get(result));
            }
//            System.out.println("str:"+newStr);
            i++;
        }
        if(i<1){
            return str;
        }
        return newStr[i-1];
    }

}
