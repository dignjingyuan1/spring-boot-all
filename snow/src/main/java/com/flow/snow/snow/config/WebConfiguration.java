package com.flow.snow.snow.config;

import com.flow.snow.snow.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Configuration
public class WebConfiguration {

    @Autowired
    private JwtUtil jwtUtil;

    private static final String[] excepts = new String[]{
            "/test.html",
            "/index.html",
            "/login",
            "/register",
            "/static/",
            "/favicon.ico"
    };

    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;

    }



    public class MyFilter implements Filter {
        @Override
        public void destroy() {
            // TODO Auto-generated method stub
        }

        @Override
        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
                throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) srequest;
            // 消除例外
            String uri = request.getRequestURI();
            boolean isExcept = false;
            for (String str: excepts){
                if (uri.contains(str)){
                    isExcept = true;
                    break;
                }
            }
            if (!isExcept) {
                String tokenStr = request.getHeader("accessToken");
                if (!StringUtils.isEmpty(tokenStr)) {
                    try{
                        Claims claims = jwtUtil.parseJWT(tokenStr);
                    } catch (Exception e){
                        throw new RuntimeException("无效令牌哦！");
                    }
                    filterChain.doFilter(srequest, sresponse);
                }else{
                    throw new RuntimeException("没有令牌哦！");
                }
            }else{
                filterChain.doFilter(srequest, sresponse);
            }
        }

        @Override
        public void init(FilterConfig arg0) throws ServletException {
            // TODO Auto-generated method stub
        }
    }

}
