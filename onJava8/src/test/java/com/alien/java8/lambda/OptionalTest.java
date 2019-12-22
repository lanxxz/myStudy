package com.alien.java8.lambda;

import com.alien.java8.bean.Apple;
import org.junit.Test;

import java.util.Optional;

/**
 * program: myStudy
 * description:
 *
 * @author: alien
 * @since: 2019/12/21 22:24
 */
public class OptionalTest {

    @Test
    public void testMapFlatmap() {
        Apple apple = new Apple("bule", 120);
        final Integer colorLength1 = Optional.ofNullable(apple)
                .map(Apple::getColor)
                .map(String::length)
                .get();
        Optional.ofNullable(apple)
                .flatMap(a -> Optional.ofNullable(a.getColor()))
                .flatMap(str -> Optional.ofNullable(str.length()))
                .get();
    }
}
