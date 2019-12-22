package com.alien.java8.lambda;

import com.alien.java8.bean.Apple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * program: myStudy
 * description: 苹果类lambda表达式
 *
 * @author: alien
 * @since: 2019/11/30 23:08
 */
@RunWith(JUnit4.class)
public class AppleMain {

    private List<Apple> filterApple(List<Apple> apples, ApplePredicate filter) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (filter.filter(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    @Test
    public void firstLambda() {
        List<Apple> apples = Arrays.asList(new Apple("Green", 150), new Apple("red", 165),
                new Apple("yellow", 145));
        List<Apple> result = filterApple(apples, apple -> "green".equals(apple.getColor()));
        result.stream().forEach(apple -> {
            assertThat(apple.getColor(), equalTo("green"));
        });
    }

    @Test
    public void sort() {
        List<Apple> apples = Arrays.asList(new Apple("Green", 150), new Apple("red", 165),
                new Apple("yellow", 145));
        apples.stream().forEach(System.out::println);
        System.out.println("=================");
//        apples.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        apples.sort(Comparator.comparing(Apple::getWeight));
        apples.stream().forEach(System.out::println);
    }

    @Test
    public void appleLambdaTest() {
        List<Apple> apples = Arrays.asList(new Apple("Green", 150), new Apple("red", 165),
                new Apple("yellow", 145));

        Function<Apple, Boolean> functionLambda = (Apple apple) -> apple.getColor().equals("green");
        Predicate<Apple> predicateLambda = (Apple apple) -> apple.getColor().equals("green");

        Consumer<Apple> appleConsumer = (Apple apple) -> {
            System.out.println(apple.getWeight());
        };

        Function<String, Integer> stringIntegerFunction = (String s) -> s.length();

        Comparator<Apple> appleComparator = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());



    }
}
