package com.alien.java8.lambda;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * program: myStudy
 * description: Function相关函数接口使用测试
 *
 * @author: alien
 * @since: 2019/12/05 22:33
 */
public class FunctionTest {

    @Test
    public void testFunction_1() {
        BiFunction<String, Integer, Character> biFunction = String::charAt;
        Character character = biFunction.apply("world", 2);
        System.out.println(character);
        System.out.println("===========");

        String str = "world";
        Function<Integer, Character> function = str::charAt;
        character = function.apply(4);
        System.out.println(character);
    }
}
