package com.alien.mockito.hamcrest;

/**
 * program: myStudy
 * description: 默认 {@link Compare} 的实现类
 *
 * @author: alien
 * @since: 2019/11/23 23:40
 */
public class DefaultNumberCompare<T extends Number> implements Compare<T> {

    private final boolean great;

    public DefaultNumberCompare(boolean great) {
        this.great = great;
    }

    @Override
    public boolean compare(T expected, T actual) {
        Class<?> clazz = actual.getClass();
        if (clazz == Integer.class) {
            return sameOr(great, (Integer) actual > (Integer) expected);
        } else if (clazz == Short.class) {
            return sameOr(great, (Short) actual > (Short) expected);
        }else if (clazz == Byte.class) {
            return sameOr(great, (Byte) actual > (Byte) expected);
        }else if (clazz == Long.class) {
            return sameOr(great, (Long) actual > (Long) expected);
        }else if (clazz == Double.class) {
            return sameOr(great, (Double) actual > (Double) expected);
        }else if (clazz == Float.class) {
            return sameOr(great, (Float) actual > (Float) expected);
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
}
