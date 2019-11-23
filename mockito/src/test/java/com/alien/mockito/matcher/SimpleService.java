package com.alien.mockito.matcher;

import java.io.Serializable;
import java.util.Collection;

/**
 * program: myStudy
 * description: 测试对象
 *
 * @author: alien
 * @since: 2019/11/23 20:35
 */
public class SimpleService {

    public int method1(int i, String s, Collection<?> c, Serializable serializable) {
        throw new RuntimeException();
    }

    public void method2(int i, String s, Collection<?> c, Serializable serializable) {
        throw new RuntimeException();
    }
}
