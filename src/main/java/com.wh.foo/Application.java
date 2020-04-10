package com.wh.foo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Auther: WangHong
 * @Date: 2020/3/26 14:40
 * @Description: 项目启动项
 */
@SpringBootApplication
@ComponentScan("com.wh.foo")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
