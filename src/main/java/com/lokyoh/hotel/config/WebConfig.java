package com.lokyoh.hotel.config;

import com.lokyoh.hotel.interceptors.AdminInterceptor;
import com.lokyoh.hotel.interceptors.LoginInterceptor;
import com.lokyoh.hotel.interceptors.WorkerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private WorkerInterceptor workerInterceptor;
    @Autowired
    private AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/worker/login", "/user/login", "/user/register");
        registry.addInterceptor(workerInterceptor).addPathPatterns("/worker/**").excludePathPatterns("/worker/login");
        registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT");
    }
}
