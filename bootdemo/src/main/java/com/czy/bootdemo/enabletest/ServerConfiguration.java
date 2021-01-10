package com.czy.bootdemo.enabletest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * program: myStudy
 * description: 配置类
 *
 * @author: alien
 * @since: 2021/01/09 11:55
 */
@Configuration
public class ServerConfiguration {

    @Bean
    public Server server() {
        return new FtpServer();
    }

}
