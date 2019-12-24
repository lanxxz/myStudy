package com.alien.java8.lambda;

import com.alien.java8.bean.Apple;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * program: myStudy
 * description: {@link Collectors} 类使用测试2
 *
 * @author: alien
 * @since: 2019/12/22 16:03
 */
public class CollectorsTest2 {

    private List<Apple> apples = Arrays.asList(
            new Apple("green", 151),
            new Apple("red", 161),
            new Apple("yellow", 156),
            new Apple("green", 150),
            new Apple("red", 143),
            new Apple("yellow", 140)
    );

    @Test
    public void test1() {
        testGroupingByConcurrentWithFunction();
        testGroupingByConcurrentWithFunctionAndCollector();
        testGroupingByConcurrentWithFunctionAndSupplierAndCollector();
    }

    private void testGroupingByConcurrentWithFunction() {
        System.out.println("testGroupingByConcurrentWithFunction");
        // 返回一个 map 对象
        ConcurrentMap<String, List<Apple>> map =
                apples.stream().collect(Collectors.groupingByConcurrent(Apple::getColor));
        Optional.ofNullable(map.getClass()).ifPresent(System.out::println);
        Optional.of(map).ifPresent(System.out::println);
    }

    private void testGroupingByConcurrentWithFunctionAndCollector() {
        System.out.println("testGroupingByConcurrentWithFunctionAndCollector");
        ConcurrentMap<String, Double> map = apples.stream()
                .collect(
                    Collectors.groupingByConcurrent(
                        Apple::getColor, Collectors.averagingInt(Apple::getWeight)));

        Optional.ofNullable(map.getClass()).ifPresent(System.out::println);
        Optional.of(map).ifPresent(System.out::println);
    }

    private void testGroupingByConcurrentWithFunctionAndSupplierAndCollector() {
        System.out.println("testGroupingByConcurrentWithFunctionAndSupplierAndCollector");
        ConcurrentSkipListMap<String, Double> map = apples.stream()
            .collect(
                Collectors.groupingByConcurrent(
                    Apple::getColor, ConcurrentSkipListMap::new, Collectors.averagingInt(Apple::getWeight)));

        Optional.ofNullable(map.getClass()).ifPresent(System.out::println);
        Optional.of(map).ifPresent(System.out::println);
    }

    @Test
    public void test2() {
        testJoining();
        testJoiningWithDelimiter();
        testJoiningWithDelimiterAndPrefixAndSuffix();
        System.out.println("=======================");
        testMapping();
    }

    private void testJoining() {
        System.out.println("testJoining");
        Optional.ofNullable(
                apples.stream().map(Apple::getColor).distinct().collect(Collectors.joining()))
            .ifPresent(System.out::println);
    }

    private void testJoiningWithDelimiter() {
        System.out.println("testJoiningWithDelimiter");
        Optional.ofNullable(
                apples.stream()
                    .map(Apple::getColor).distinct()
                    .collect(Collectors.joining(",")))
            .ifPresent(System.out::println);
    }

    private void testJoiningWithDelimiterAndPrefixAndSuffix() {
        System.out.println("testJoiningWithDelimiterAndPrefixAndSuffix");
        Optional.ofNullable(
                apples.stream()
                    .map(Apple::getColor)
                    .distinct()
                    .collect(Collectors.joining(",", "Apple's color[", "]")))
            .ifPresent(System.out::println);
    }

    private void testMapping() {
        System.out.println("testMapping");
        Optional.of(
                apples.stream()
                    .collect(Collectors.mapping(Apple::getColor, Collectors.joining(","))))
            .ifPresent(System.out::println);
    }

    @Test
    public void test3() {
        testMaxBy();
        testMinBy();
    }

    private void testMaxBy() {
        System.out.println("testMaxBy");
        apples.stream().collect(
                Collectors.maxBy(Comparator.comparingInt(Apple::getWeight)))
            .ifPresent(System.out::println);
    }

    private void testMinBy() {
        System.out.println("testMinBy");
        apples.stream().collect(
                Collectors.minBy(Comparator.comparingInt(Apple::getWeight)))
                .ifPresent(System.out::println);
    }

    @Test
    public void test4() {
        // map对象 key 为 true 或 false
        testPartitioningByPredicate();
        testPartitioningByPredicateAndCollector();
    }

    private void testPartitioningByPredicate() {
        System.out.println("testPartitioningByPredicate");
        // 判断苹果重量是否大于 150
        Map<Boolean, List<Apple>> map = apples.stream()
                .collect(Collectors.partitioningBy(apple -> apple.getWeight() > 150));
        Optional.of(map).ifPresent(System.out::println);
    }

    private void testPartitioningByPredicateAndCollector() {
        System.out.println("testPartitioningByPredicateAndCollector");
        final Map<Boolean, Double> map = apples.stream()
                .collect(
                    Collectors.partitioningBy(
                        apple -> apple.getWeight() > 150, Collectors.averagingInt(Apple::getWeight)));
        Optional.of(map).ifPresent(System.out::println);
    }

    @Test
    public void test5() {
        testReducingBinaryOperator();
        testReducingBinaryOperatorAndIdentity();
        testReducingBinaryOperatorAndIdentityAndFunction();
        System.out.println("==============");
        testSummarizingDouble();
        testSummarizingInt();
        testSummarizingLong();
    }

    private void testReducingBinaryOperator() {
        System.out.println("testReducingBinaryOperator");
        apples.stream().collect(
                Collectors.reducing(
                    BinaryOperator.maxBy(
                        Comparator.comparingInt(Apple::getWeight))))
            .ifPresent(System.out::println);
    }

    private void testReducingBinaryOperatorAndIdentity() {
        System.out.println("testReducingBinaryOperatorAndIdentity");
        Integer result = apples.stream()
            .map(Apple::getWeight).collect(Collectors.reducing(0, (d1, d2) -> d1 + d2));
        System.out.println(result);
    }

    private void testReducingBinaryOperatorAndIdentityAndFunction() {
        System.out.println("testReducingBinaryOperatorAndIdentityAndFunction");
        // 效果同前一个方法 testReducingBinaryOperatorAndIdentity 的示例
        Integer result = apples.stream()
            .collect(Collectors.reducing(0, Apple::getWeight, (d1, d2) -> d1 + d2));
        System.out.println(result);
    }

    private void testSummarizingDouble() {
        System.out.println("summarizingDouble");
        Optional.of(
                apples.stream()
                    .collect(
                        Collectors.summarizingDouble(Apple::getWeight)))
            .ifPresent(System.out::println);
    }

    private void testSummarizingLong() {
        System.out.println("summarizingLong");
        Optional.of(
                apples.stream()
                        .collect(
                                Collectors.summarizingLong(Apple::getWeight)))
            .ifPresent(System.out::println);
    }

    private void testSummarizingInt() {
        System.out.println("summarizingInt");
        Optional.of(
                apples.stream()
                        .collect(
                                Collectors.summarizingInt(Apple::getWeight)))
            .ifPresent(System.out::println);
    }

    @Test
    public void test6() {
        testSummingDouble();
        testSummingLong();
        testSummingInt();
    }

    private void testSummingDouble() {
        System.out.println("testSummingDouble");
        // 求和
        Double result = apples.stream().collect(Collectors.summingDouble(Apple::getWeight));
        Optional.of(result).ifPresent(System.out::println);

        int sum = apples.stream().map(Apple::getWeight).mapToInt(Integer::intValue).sum();
        System.out.println(sum);
    }

    private void testSummingLong() {
        System.out.println("testSummingLong");
        // 求和
        Long result = apples.stream().collect(Collectors.summingLong(Apple::getWeight));
        Optional.of(result).ifPresent(System.out::println);
    }

    private void testSummingInt() {
        System.out.println("testSummingInt");
        // 求和
        Integer result = apples.stream().collect(Collectors.summingInt(Apple::getWeight));
        Optional.of(result).ifPresent(System.out::println);
    }

    @Test
    public void test7() {
        testToCollection();
        testToMap();
        testToMapWithBinaryOperator();
        testToMapWithBinaryOperatorWithSupplier();
        System.out.println("===================");
        testToConcurrentMap();
        testToConcurrentMapWithBinaryOperator();
        testToConcurrentMapWithBinaryOperatorWithSupplier();
        System.out.println("===================");
        testToList();
        testToSet();
    }

    private void testToList() {
        System.out.println("testToList");
        List<Integer> result = apples.stream().map(Apple::getWeight).collect(Collectors.toList());
        Optional.of(result).ifPresent(m -> {
            System.out.println(m.getClass());
            System.out.println(m);
        });
    }

    private void testToSet() {
        System.out.println("testToSet");
        Set<Integer> result = apples.stream().map(Apple::getWeight).collect(Collectors.toSet());
        Optional.of(result).ifPresent(m -> {
            System.out.println(m.getClass());
            System.out.println(m);
        });
    }

    private void testToCollection() {
        System.out.println("testToCollection");
        HashSet<Apple> result = apples.stream().filter(a -> a.getWeight() > 150)
                .collect(Collectors.toCollection(HashSet::new));
        Optional.of(result).ifPresent(System.out::println);
    }

    private void testToMap() {
        System.out.println("testToMap");
        Map<String, Integer> result = apples.stream().filter(a -> a.getWeight() > 150)
                .collect(Collectors.toMap(Apple::getColor, Apple::getWeight));
        Optional.of(result).ifPresent(m -> {
            System.out.println(m.getClass());
            System.out.println(m);
        });
    }

    private void testToMapWithBinaryOperator() {
        System.out.println("testToMapWithBinaryOperator");
        // key -> color, value -> total weight
        Map<String, Integer> result = apples.stream()
                .collect(Collectors.toMap(Apple::getColor, Apple::getWeight, (u, v) -> u + v));
        Optional.of(result).ifPresent(m -> {
            System.out.println(m.getClass());
            System.out.println(m);
        });
    }

    private void testToMapWithBinaryOperatorWithSupplier() {
        System.out.println("testToMapWithBinaryOperatorWithSupplier");
        TreeMap<String, Integer> result = apples.stream()
                .collect(Collectors.toMap(Apple::getColor, Apple::getWeight,
                        (u, v) -> u + v, TreeMap::new));
        Optional.of(result).ifPresent(System.out::println);
    }

    private void testToConcurrentMap() {
        System.out.println("testToConcurrentMap");
        ConcurrentMap<String, Integer> result = apples.stream().filter(a -> a.getWeight() > 150)
                .collect(Collectors.toConcurrentMap(Apple::getColor, Apple::getWeight));
        Optional.of(result).ifPresent(System.out::println);
    }

    private void testToConcurrentMapWithBinaryOperator() {
        System.out.println("testToConcurrentMapWithBinaryOperator");
        // key -> color, value -> total weight
        ConcurrentMap<String, Integer> result = apples.stream()
                .collect(Collectors.toConcurrentMap(Apple::getColor, Apple::getWeight, (u, v) -> u + v));
        Optional.of(result).ifPresent(System.out::println);
    }

    private void testToConcurrentMapWithBinaryOperatorWithSupplier() {
        System.out.println("testToConcurrentMapWithBinaryOperatorWithSupplier");
        ConcurrentSkipListMap<String, Integer> result = apples.stream()
                .collect(Collectors.toConcurrentMap(Apple::getColor, Apple::getWeight,
                        (u, v) -> u + v, ConcurrentSkipListMap::new));
        Optional.of(result).ifPresent(System.out::println);
    }

}
