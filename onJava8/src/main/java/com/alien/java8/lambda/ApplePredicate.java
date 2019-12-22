package com.alien.java8.lambda;

import com.alien.java8.bean.Apple;

/**
 * program: myStudy
 * description: {@link Apple} 类过滤接口
 *
 * @author: alien
 * @since: 2019/11/30 23:05
 */
@FunctionalInterface
public interface ApplePredicate {

    /**
     * method name: filter <br/>
     * description: 过滤规则
     * @param apple:
     * @return: boolean
     * @since: 2019-11-30
     */
    boolean filter(Apple apple);
}
