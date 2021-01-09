package com.czy.bootdemo;

import com.czy.bootdemo.enabletest.EnableServer;
import com.czy.bootdemo.enabletest.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableServer("mail")
//@EnableServer("${com.czy.server}")
public class BootdemoApplication {

    @Value("${com.czy.server}")
    private String value2;

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(BootdemoApplication.class, args);

        Server server = applicationContext.getBean(Server.class);

        BootdemoApplication bean = applicationContext.getBean(BootdemoApplication.class);
        System.out.println(bean.value2);

        server.start();
        server.run();
        server.stop();

        applicationContext.close();
    }

}
