package com.nynu.springboot.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 王纪勇
 * @Date: 2022/4/8 11:41
 * @Description: 拦截器配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所以路径的接口和资源
        registry.addInterceptor(new InterceptorConfig()).addPathPatterns("/**")
                //只释放注册，登录和一些静态资源
                .excludePathPatterns("/user/login", "/user/register", "/js/**", "/css/**", "/images/**");

    }
}
