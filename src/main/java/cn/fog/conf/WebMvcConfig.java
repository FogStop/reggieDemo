package cn.fog.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.xml.ws.soap.Addressing;

@Configuration
@Slf4j
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /*
    主要就是进行静态资源映射的方法，实际就是浏览器浏览的请求
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("静态映射开始...");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath/front/");
    }
}
