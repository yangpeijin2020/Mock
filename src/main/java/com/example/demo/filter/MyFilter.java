package com.example.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "myFilter" ,urlPatterns={"/*"})
public class MyFilter  implements Filter {

    Logger logger= LoggerFactory.getLogger(MyFilter.class);

    public void  init(FilterConfig  filterConfig){
            logger.info("filter  init==============");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("进入  doFilter==============");
        HttpServletResponse  resp= (HttpServletResponse) servletResponse;
        HttpServletRequest   req= (HttpServletRequest) servletRequest;
        String url=req.getRequestURI();
        logger.info("url ==="+url);
        if (url.indexOf("wms")>-1){
            req.setAttribute("url",url);
            req.getRequestDispatcher("/wms").forward(req,resp);
        }else {
            filterChain.doFilter(req,resp);
        }
    }

    @Override
    public void destroy() {
        logger.info("filter  destroy==============");
    }

}
