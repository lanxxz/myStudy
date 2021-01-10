package com.czy.bootdemo.enabletest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * program: myStudy
 * description: {@link Configuration} import
 *
 * @author: alien
 * @since: 2021/01/09 13:10
 */
@Configuration
@EnableServer("")
public class EnableConfigurationImportTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(EnableConfigurationImportTest.class);
        applicationContext.refresh();

        Server server = applicationContext.getBean(Server.class);
        server.start();
        server.run();
        server.stop();

        applicationContext.close();
    }
}
