package com.example.lmageeach.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080") // 设置允许的源
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 设置允许的 HTTP 方法
                .allowedHeaders("*") // 设置允许的请求头
                .allowCredentials(true) // 允许携带跨域请求的凭证信息
                .maxAge(3600); // maxAge 设置跨域预检请求的有效期，单位为秒
    }
}
