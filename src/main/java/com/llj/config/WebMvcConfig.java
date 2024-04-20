package com.llj.config;

import com.llj.common.JacksonObjectMapper;
import com.llj.interceptor.JwtTokenUserInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
@Slf4j
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JwtTokenUserInterceptor jwtTokenUserInterceptor;

    /**
     * 设置静态资源映射
     * @param
     */
    //@Override
    //protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    //    log.info("开始静态资源映射...");
    //    registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
    //    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    //
    //    registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
    //    registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    //}

    /**
     * 注册自定义拦截器
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(jwtTokenUserInterceptor)
                .excludePathPatterns("/register","/login","/logout","/common/sendcode","/common/download");
    }

    /*
    扩展mvc框架的消息转换器
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建消息转换器对象
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //设置消息转换器，底层使用Jackson将java对象转为json
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //将消息转换器对象添加到mvc的转换器集合中
        converters.add(0,messageConverter);
    }
}

