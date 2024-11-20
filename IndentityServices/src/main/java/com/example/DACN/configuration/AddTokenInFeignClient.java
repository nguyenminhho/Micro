package com.example.DACN.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class AddTokenInFeignClient implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {

        // Lay cac param hien tai trong req
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();

        //Lay ra token trong req
        var authHeader = servletRequestAttributes.getRequest().getHeader("Authorization");

        //Them token vao req tiep theo thong qua feign
        if(StringUtils.hasText(authHeader)){
            requestTemplate.header("Authorization", authHeader);
        }
    }
}
