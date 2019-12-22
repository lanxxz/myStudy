package com.alien.java8.stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * program: myStudy
 * description:
 *
 * @author: alien
 * @since: 2019/12/13 23:42
 */
public class IntStreamDemo {

    public static void main(String[] args) {
        int a = 9;
        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).forEach(System.out::println);

        IntStream.rangeClosed(1, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .boxed()
                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .forEach(arr -> System.out.println("a=" + arr[0] + ", b=" + arr[1] + ", c=" + arr[2]));

        System.out.println("==============");

        IntStream.rangeClosed(1, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .forEach(arr -> System.out.println("a=" + arr[0] + ", b=" + arr[1] + ", c=" + arr[2]));

        System.out.println("==============");

        Stream.of(1, 2, 3, 4)
                .mapToInt(i -> i.intValue()).reduce((x, y) -> x + y)
                .ifPresent(System.out::println);
    }
}
