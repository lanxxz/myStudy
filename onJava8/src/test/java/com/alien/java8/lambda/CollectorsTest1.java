package com.alien.java8.lambda;

import com.alien.java8.bean.Apple;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * program: myStudy
 * description: {@link Collectors} 类使用测试
 *
 * @author: alien
 * @since: 2019/12/21 23:11
 */
public class CollectorsTest1 {

    /**
     * method name: test1 <br/>
     * description: 测试封装成 map
     *
     * @return: void
     * @since: 2019-12-22
     */
    @Test
    public void test1() {
        List<Apple> apples = Arrays.asList(
                new Apple("Green", 151),
                new Apple("red", 161),
                new Apple("yellow", 141),
                new Apple("Green", 150),
                new Apple("red", 160),
                new Apple("yellow", 140)
        );
        final List<Apple> reds = apples.stream()
                .filter(apple -> "red".equals(apple.getColor()))
                .collect(toList());
        Optional.ofNullable(reds).ifPresent(System.out::println);
        System.out.println("==========================");
        Optional.ofNullable(groupByNormal(apples)).ifPresent(System.out::println);
        System.out.println("==========================");
        Optional.ofNullable(groupByFunction(apples)).ifPresent(System.out::println);
        System.out.println("==========================");
        Optional.ofNullable(groupByCollectors(apples)).ifPresent(System.out::println);

    }

    private Map<String, List<Apple>> groupByNormal(List<Apple> list) {
        Map<String, List<Apple>> map = new HashMap<>();

        for (Apple apple : list) {
            List<Apple> apples = map.get(apple.getColor());
            if (apples == null) {
                apples = new ArrayList<>();
                map.put(apple.getColor(), apples);
            }
            apples.add(apple);
        }

        return map;
    }

    private Map<String, List<Apple>> groupByFunction(List<Apple> list) {
        Map<String, List<Apple>> map = new HashMap<>();

        list.stream().forEach(apple -> {
            List<Apple> apples1 = Optional.ofNullable(map.get(apple.getColor()))
                    .orElseGet(() -> {
                        List<Apple> apples = new ArrayList<>();
                        map.put(apple.getColor(), apples);
                        return apples;
                    });
            apples1.add(apple);
        });

        return map;
    }

    private Map<String, List<Apple>> groupByCollectors(List<Apple> list) {
        // 获取到 key -> Apple.getColor(), value 为 list 的 map 集合
        return list.stream().collect(Collectors.groupingBy(Apple::getColor));
    }

    /**
     * method name: averageTest <br/>
     * description: 平均值测试
     *
     * @return: void
     * @since: 2019-12-22
     */
    @Test
    public void test2() {
        List<Apple> apples = Arrays.asList(
                new Apple("Green", 151),
                new Apple("red", 161),
                new Apple("yellow", 141),
                new Apple("Green", 150),
                new Apple("red", 160),
                new Apple("yellow", 140)
        );
        testAveragingDouble(apples);
        testAveragingInt(apples);
        testAveragingLong(apples);
        System.out.println("=========1=========");
        testCollectingAndThen(apples);
        System.out.println("=========2=========");
        testCounting(apples);
        System.out.println("=========3=========");
        testGroupingBy(apples);
        testGroupingBy2(apples);
        testGroupingBy3(apples);
        System.out.println("=========4=========");
        testSummarizingInt(apples);
    }

    private void testAveragingDouble(List<Apple> apples) {
        // 求平均
        System.out.println("averagingDouble");
        Optional.ofNullable(
                apples.stream()
                        .collect(Collectors.averagingDouble(Apple::getWeight)))
                .ifPresent(System.out::println);
    }

    private void testAveragingInt(List<Apple> apples) {
        // 求平均
        System.out.println("averagingInt");
        Optional.ofNullable(
                apples.stream()
                        .collect(Collectors.averagingInt(Apple::getWeight)))
                .ifPresent(System.out::println);
    }

    private void testAveragingLong(List<Apple> apples) {
        // 求平均
        System.out.println("averagingLong");
        Optional.ofNullable(
                apples.stream()
                        .collect(Collectors.averagingLong(Apple::getWeight)))
                .ifPresent(System.out::println);
    }

    private void testCollectingAndThen(List<Apple> apples) {
        System.out.println("collectingAndThen");
        Optional.ofNullable(apples.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.averagingLong(Apple::getWeight), a -> "平均值是" + a)))
                .ifPresent(System.out::println);

        List<Apple> list = apples.stream()
                .filter(a -> "Green".equals(a.getColor()))
                .collect(
                        Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
//        此时不能对该 list 进行修改
//        list.add(new Apple("red", 190));
        System.out.println(list);
    }

    private void testCounting(List<Apple> apples) {
        System.out.println("counting");
        Optional.ofNullable(
                apples.stream().collect(Collectors.counting()))
                .ifPresent(System.out::println);

        System.out.println(apples.stream().count());

    }

    private void testGroupingBy(List<Apple> apples) {
        System.out.println("groupingBy");
        // 获取到 key -> Apple.gtWeight(), value 为 list 的 map 集合
        Optional.ofNullable(
                apples.stream().collect(Collectors.groupingBy(Apple::getWeight)))
                .ifPresent(System.out::println);
    }

    private void testGroupingBy2(List<Apple> apples) {
        System.out.println("groupingBy2");
        // 各种颜色的个数
        Optional.ofNullable(
                apples.stream().collect(Collectors.groupingBy(Apple::getColor, Collectors.counting())))
                .ifPresent(System.out::println);

        // 各种颜色的平均重量
        Optional.ofNullable(
                apples.stream().collect(Collectors.groupingBy(Apple::getColor, Collectors.averagingLong(Apple::getWeight))))
                .ifPresent(System.out::println);

    }

    private void testGroupingBy3(List<Apple> apples) {
        System.out.println("groupingBy3");

        // 使用自定义的 map， 默认是使用 HashMap
        Map<String, Double> map1 = apples.stream()
                .collect(Collectors.groupingBy(Apple::getColor, Collectors.averagingLong(Apple::getWeight)));
        Optional.ofNullable(map1.getClass()).ifPresent(System.out::println);

        final TreeMap<String, Double> map = apples.stream()
                .collect(Collectors.groupingBy(Apple::getColor, TreeMap::new, Collectors.averagingLong(Apple::getWeight)));
        Optional.ofNullable(map.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(map).ifPresent(System.out::println);

    }

    private void testSummarizingInt(List<Apple> apples) {
        System.out.println("summarizingInt");
        IntSummaryStatistics summaryStatistics = apples.stream().collect(Collectors.summarizingInt(Apple::getWeight));
        Optional.ofNullable(summaryStatistics).ifPresent(System.out::println);
    }

}
