package com.alien.java8.lambda;

import com.alien.java8.bean.Apple;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
            new Apple("yellow", 141),
            new Apple("green", 150),
            new Apple("red", 160),
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


}
