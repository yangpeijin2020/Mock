package com.example.demo.conifg;

import com.example.demo.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean  registFilter(){
        FilterRegistrationBean   registrationBean=new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("myFilter");
        return   registrationBean;
    }
}
