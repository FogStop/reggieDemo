//package cn.fog.conf;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableSwagger2
//@Slf4j
//public class SwaggerConfig {
//
//    @Bean
//    public Docket createRestApi(){
//        log.info("用到了");
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("cn"))
//                .paths(PathSelectors.any()).build();
//    }
//    private ApiInfo apiInfo(){
//        return new ApiInfoBuilder()
//                .title("瑞吉外卖项目")//标题
//                .description("后端接口文档详细显示")//描述
//                .version("1.0")//版本
//                .build();
//    }
//
////  启动  http://127.0.0.1:8081/swagger-ui.html
//}

