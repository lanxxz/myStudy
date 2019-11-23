package com.alien.mockito.hamcrest;

import org.junit.Test;

import static com.alien.mockito.hamcrest.CompareNumber.gt;
import static com.alien.mockito.hamcrest.CompareNumber.lt;
import static org.hamcrest.CoreMatchers.both;
import static org.junit.Assert.assertThat;

/**
 * program: myStudy
 * description: matcher 扩展测试
 *
 * @author: alien
 * @since: 2019/11/23 22:23
 */
public class SimpleTest {

    @Test
    public void test() {
        assertThat(1, lt(3));
        assertThat(10, gt(5));
        assertThat(12, both(gt(10)).and(lt(13)));
    }
}
