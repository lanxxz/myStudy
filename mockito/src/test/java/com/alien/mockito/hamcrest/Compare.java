package com.alien.mockito.hamcrest;

/**
 * program: myStudy
 * description: 比较接口
 *
 * @author: alien
 * @since: 2019/11/23 23:38
 */
public interface Compare<T extends Number> {
    boolean compare(T expected, T actual);
}
