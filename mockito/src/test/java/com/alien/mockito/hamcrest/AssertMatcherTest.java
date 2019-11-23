package com.alien.mockito.hamcrest;

import org.junit.Test;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * program: myStudy
 * description: matcher test
 *
 * @author: alien
 * @since: 2019/11/23 21:40
 */
public class AssertMatcherTest {

    @Test
    public void test() {
        int i = 10;
        assertThat(i, equalTo(10));
        assertThat(i, not(equalTo(20)));

        assertThat(i, is(10));
        assertThat(i, is(not(20)));
    }

    @Test
    public void test2() {
        double d = 23.45d;
        // 两个条件 满足任意一个
        assertThat(d, either(equalTo(23.45d)).or(equalTo(23.54d)));
        // 两个条件 都满足
        assertThat(d, both(equalTo(23.45d)).and(not(equalTo(23.54d))));
        // 任意满足一个条件
        assertThat(d, anyOf(is(23.45d), is(43.43d), is(44.33d), not(45d)));
        assertThat(d, allOf(is(23.45d), not(is(43.43d)), not(is(44.33d)), not(45d)));

        assertThat(Stream.of(1, 2, 3).allMatch(i -> i > 0), equalTo(true));
        assertThat(Stream.of(1, 2, 3).anyMatch(i -> i > 2), equalTo(true));
    }

    @Test
    public void test3() {
        // 自定义断言失败提示内容
        double d = 23.45d;
        assertThat("浮点型数值断言失败", d, either(equalTo(23.55d)).or(equalTo(23.54d)));
    }

}
