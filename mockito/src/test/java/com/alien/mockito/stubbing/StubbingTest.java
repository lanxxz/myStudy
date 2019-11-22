package com.alien.mockito.stubbing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * program: myStudy
 * description: stubbing test
 *
 * @author: alien
 * @since: 2019/11/22 20:30
 */
@RunWith(MockitoJUnitRunner.class)
public class StubbingTest {

    private List<String> list;

    @Before
    public void init() {
        list = mock(ArrayList.class);
    }

    /**
     * method name: howToUseStubbing <br/>
     * description: 打桩示例
     * @return: void
     * @since: 2019-11-22
     */
    @Test
    public void howToUseStubbing() {
        // get 参数 0 时返回 first
        when(list.get(0)).thenReturn("first");
        assertThat(list.get(0), equalTo("first"));

        // get 任意参数抛出运行时异常
        when(list.get(anyInt())).thenThrow(new RuntimeException());
        try {
            list.get(0);
            fail();
        } catch (Exception e) {
            assertThat(e, instanceOf(RuntimeException.class));
        }

    }

    /**
     * method name: howToUseStubbingVoidMethod <br/>
     * description: 没有返回值的处理
     * @return: void
     * @since: 2019-11-22
     */
    @Test
    public void howToUseStubbingVoidMethod() {
        doNothing().when(list).clear();
        list.clear();
        // 断言是否执行过 这里断言执行过一次
        verify(list, times(1)).clear();

        // 抛异常的处理
        doThrow(RuntimeException.class).when(list).clear();
        try {
            list.clear();
            fail();
        } catch (Exception e) {
            assertThat(e, instanceOf(RuntimeException.class));
        }
    }

    /**
     * method name: stubbingDoReturn <br/>
     * description: 带有返回值的写法
     * @return: void
     * @since: 2019-11-22
     */
    @Test
    public void stubbingDoReturn() {
        // 以下两种写法等价
        when(list.get(0)).thenReturn("first");
        doReturn("second").when(list).get(1);
        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
    }

    /**
     * method name: iterateStubbing <br/>
     * description: 迭代打桩
     * @return: void
     * @since: 2019-11-22
     */
    @Test
    public void iterateStubbing() {
        // 依次返回 1 2 3 4
        when(list.size()).thenReturn(1, 2, 3, 4);
        assertThat(list.size(), equalTo(1));
        assertThat(list.size(), equalTo(2));
        assertThat(list.size(), equalTo(3));
        assertThat(list.size(), equalTo(4));
        assertThat(list.size(), equalTo(4));

        // 依次返回 5 6
        when(list.size()).thenReturn(5).thenReturn(6);
        assertThat(list.size(), equalTo(5));
        assertThat(list.size(), equalTo(6));
    }

    /**
     * method name: stubbingWithAnswer <br/>
     * description: 返回结果逻辑处理流程

     * @return: void
     * @since: 2019-11-22
     */
    @Test
    public void stubbingWithAnswer() {
//        when(list.get(anyInt())).thenAnswer(new Answer<String>() {
//
//            @Override
//            public String answer(InvocationOnMock invocation) throws Throwable {
//                return null;
//            }
//        });

        when(list.get(anyInt())).thenAnswer(invocation -> {
            Integer index = invocation.getArgumentAt(0, Integer.class);
            return String.valueOf(index * 10);
        });
        assertThat(list.get(0), equalTo("0"));
        assertThat(list.get(999), equalTo("9990"));
    }

    @Test
    public void stubbingWithRealcall() {
        StubbingService service = mock(StubbingService.class);
        // 返回假的值
        when(service.getS()).thenReturn("alex");
        assertThat(service.getS(), equalTo("alex"));
        // 返回真的值
        when(service.getI()).thenCallRealMethod();
        assertThat(service.getI(), equalTo(10));

    }






    @After
    public void destory() {
        reset(list);
    }

}
