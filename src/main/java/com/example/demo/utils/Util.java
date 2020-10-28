package com.example.demo.utils;


import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    static Logger logger= LoggerFactory.getLogger(Util.class);
    Date dt = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
    String data = sdf.format(dt);
    static String  result = null;
    //提取｛｝里面的变量 String   str="my name  is {name} ,age  is {age}";


    public static   String  getUuid(){
        return "uuid"+new Util().data;
    }
    public  static String  getCid(){
        return "cid"+new Util().data;
    }

    public static   JSONObject strTojson(String str){
        logger.debug("str:"+str);
        JSONObject  jsonObject= JSONObject.fromObject(str);
        logger.info("str 转换 json"+jsonObject.toString());
        return jsonObject;
    }
}
