package com.alien.mockito.matcher;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

/**
 * program: myStudy
 * description: 参数匹配测试
 *
 * @author: alien
 * @since: 2019/11/23 17:11
 */
public class ArgumentsMatcherTest {

    @Test
    public void basicTest() {
        List<Integer> list = mock(ArrayList.class);
//        when(list.get(0)).thenReturn(1000);
        when(list.get(eq(0))).thenReturn(1000);
        assertThat(list.get(0), equalTo(1000));
        assertThat(list.get(1), nullValue());
    }

    /**
     * method name: testComplex <br/>
     * description: isA any 的应用
     * @return: void
     * @since: 2019-11-23
     */
    @Test
    public void testComplex() {
        Foo foo = mock(Foo.class);
        when(foo.function(isA(Parent.class))).thenReturn(100);
        int result = foo.function(new Child1()); // new Child2() 是一样的结果
        assertThat(result, equalTo(100));

        reset(foo);
        //////////////////////////////
        when(foo.function(isA(Child1.class))).thenReturn(100);
        result = foo.function(new Child1());
        assertThat(result, equalTo(100));
        result = foo.function(new Child2());
        assertThat(result, equalTo(0));

        reset(foo);
        //////////////////////////////
        // any(CLass<T> clazz) 只要通过语言检查，可以是 Parent或其子类都可以验证通过 旧版本可以通过，新版本不行
//        when(foo.function(any(Child1.class))).thenReturn(100);
        when(foo.function(any())).thenReturn(100);
        result = foo.function(new Child2());
        assertThat(result, equalTo(100));

    }

    ////////////////////////////////

    interface Parent {
        int work();
    }

    class Child1 implements Parent {

        @Override
        public int work() {
            throw new RuntimeException();
        }
    }

    class Child2 implements Parent {

        @Override
        public int work() {
            throw new RuntimeException();
        }
    }

    static class Foo{
        int function(Parent p) {
            return p.work();
        }
    }
}
