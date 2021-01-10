package com.czy.bootdemo.enabletest;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * program: myStudy
 * description: 模块化server注解
 *
 * @author: alien
 * @since: 2021/01/09 11:53
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
//@Import(FtpServer.class)
@Import(ServerConfiguration.class)
//@Import(ServerImportSelector.class)
//@Import(ServerImportBeanDefinitionRegistrar.class)
public @interface EnableServer {

    String value();
}
