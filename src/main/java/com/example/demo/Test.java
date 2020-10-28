package com.example.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public String  getMatcher(String  body){
        String  result=null;
        Pattern pattern = Pattern.compile("\\$\\{(.*?)}");
        Matcher matcher=pattern.matcher(body);
        while (matcher.find()){
            result=matcher.group(1);

        }
        return result;
    }
    public static void main(String[] args) {
        String   str="my name  is ${name} ,age  is ${age}";
        new Test().getMatcher(str);
    }
}
