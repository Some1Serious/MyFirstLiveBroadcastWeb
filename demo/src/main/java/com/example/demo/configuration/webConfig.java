package com.example.demo.configuration;

import com.example.demo.interceptor.logInInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 创建拦截器, 主要是用来拦截登录状态
        registry.addInterceptor(new logInInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/logIn", "/user/register");
    }
}
