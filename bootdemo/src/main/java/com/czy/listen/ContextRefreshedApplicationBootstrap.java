package com.czy.listen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * program: myStudy
 * description: 入口
 *
 * @author: alien
 * @since: 2021/01/10 22:40
 */
@SpringBootApplication
public class ContextRefreshedApplicationBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ContextRefreshedApplicationBootstrap.class, args);

        System.out.println("IOC容器初始化完成。。。");

        RegisterService registerService = context.getBean(RegisterService.class);
        registerService.register("张大三");

        context.close();
        System.out.println("IOC容器关闭。。。");
    }
}
