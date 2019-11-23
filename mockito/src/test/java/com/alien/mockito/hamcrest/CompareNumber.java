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

    private final boolean great;

    public CompareNumber(T value, boolean great) {
        this.value = value;
        this.great = great;
    }

    @Override
    public boolean matches(Object actual) {
        Class<?> clazz = actual.getClass();
        if (clazz == Integer.class) {
            return sameOr(great, (Integer) actual > (Integer) value);
        } else if (clazz == Short.class) {
            return sameOr(great, (Short) actual > (Short) value);
        }else if (clazz == Byte.class) {
            return sameOr(great, (Byte) actual > (Byte) value);
        }else if (clazz == Long.class) {
            return sameOr(great, (Long) actual > (Long) value);
        }else if (clazz == Double.class) {
            return sameOr(great, (Double) actual > (Double) value);
        }else if (clazz == Float.class) {
            return sameOr(great, (Float) actual > (Float) value);
        }else {
            throw new AssertionError("The number type " + clazz + " is not supported.");
        }
    }

    private boolean sameOr(boolean great, boolean compare) {
        // 同或
        return xor(great, compare) ^ true;
    }

    private boolean xor(boolean great, boolean compare) {
        // 异或
        return great ^ compare;
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
