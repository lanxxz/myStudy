package com.czy.xml.test;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * program: myStudy
 * description: 启动类
 *
 * @author: alien
 * @since: 2021/01/10 20:13
 */
public class ListableBeanFactoryApplication {

    private static void test1() {
        ClassPathResource resource = new ClassPathResource("container/listable-container.xml");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions(resource);
        // 直接打印容器中的所有Bean
        System.out.println("加载xml文件后容器中的Bean：");
        Stream.of(beanFactory.getBeanDefinitionNames()).forEach(System.out::println);
    }

    private static void test2() {
        ClassPathResource resource = new ClassPathResource("container/listable-container.xml");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions(resource);
        // 直接打印容器中的所有Bean
        System.out.println("加载xml文件后容器中的Bean：");
        Stream.of(beanFactory.getBeanDefinitionNames()).forEach(System.out::println);
        System.out.println();

        beanFactory.registerSingleton("doggg", new Dog());
        // 再打印容器中的所有Bean
        System.out.println("手动注册单实例Bean后容器中的所有Bean：");
        Stream.of(beanFactory.getBeanDefinitionNames()).forEach(System.out::println);

        System.out.println("容器中真的有注册的Dog：" + beanFactory.getBean("doggg"));
        // 通过getBeanNamesOfType查找Dog
        System.out.println("容器中的所有Dog：" + Arrays.toString(beanFactory.getBeanNamesForType(Dog.class)));
    }

    public static void main(String[] args) {
//        test1();
        test2();
    }
}
