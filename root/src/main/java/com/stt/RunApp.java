package com.stt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @Description
 * @Author shaotongtong
 * @Date 2022-02-11 15:27
 */
@Configuration
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class RunApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RunApp.class, args);
        Environment environment = context.getBean(Environment.class);
        System.out.println("访问链接：http://localhost:" + environment.getProperty("server.port")) ;
    }
}
