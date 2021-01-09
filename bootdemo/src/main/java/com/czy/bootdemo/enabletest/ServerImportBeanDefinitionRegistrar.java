package com.czy.bootdemo.enabletest;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.stream.Stream;

/**
 * program: myStudy
 * description:
 *
 * @author: alien
 * @since: 2021/01/09 13:23
 */
public class ServerImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        ServerImportSelector importSelector = new ServerImportSelector();
        String[] strings = importSelector.selectImports(importingClassMetadata);

        BeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClassName(strings[0]);
        registry.registerBeanDefinition(Server.class.getName(), genericBeanDefinition);

//        Stream.of(strings).map(GenericBeanDefinition::new)

    }
}
