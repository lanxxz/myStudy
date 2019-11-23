package com.alien.mockito.hamcrest;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;

/**
 * program: myStudy
 * description: 自定义实现matcher
 *
 * @author: alien
 * @since: 2019/11/23 22:26
 */
public class CompareNumber<T extends Number> extends BaseMatcher<T> {

    private final T value;

    private final Compare<T> COMPARE;

    public CompareNumber(T value, boolean great) {
        this.value = value;
        this.COMPARE = new DefaultNumberCompare<>(great);
    }

    @Override
    public boolean matches(Object actual) {
        return this.COMPARE.compare(value, (T) actual);
    }

    @Factory
    public static <T extends Number> CompareNumber<T> gt(T value) {
        return new CompareNumber<>(value, true);
    }

    @Factory
    public static <T extends Number> CompareNumber<T> lt(T value) {
        return new CompareNumber<>(value, false);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("compare two number failed.");
    }
}
