package cn.fog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan
//开启拦截其扫描类
@Slf4j
@EnableTransactionManagement//开启事务控制类
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("原神启动@_@");
    }

}
