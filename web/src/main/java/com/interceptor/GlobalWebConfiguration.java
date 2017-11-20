package com.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 为后台操作链接增加权限控制，只有指定用户可以访问后台操作页面
 */
@Configuration
public class GlobalWebConfiguration extends WebMvcConfigurerAdapter {
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AccessLinkInterceptor());
    }
}