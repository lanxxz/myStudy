package com.alien.mockito.matcher;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.Serializable;
import java.util.Collections;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * program: myStudy
 * description: 参数匹配测试2
 *
 * @author: alien
 * @since: 2019/11/23 20:43
 */
@RunWith(MockitoJUnitRunner.class)
public class WildcardArgumentMatcherTest {

    @Mock
    private SimpleService simpleService;

    /**
     * method name: WildcardMethod1 <br/>
     * description: 参数匹配任意值
     * @return: void
     * @since: 2019-11-23
     */
    @Test
    public void WildcardMethod1() {
        when(simpleService.method1(anyInt(), anyString(), anyCollection(), isA(Serializable.class))).thenReturn(100);

        int result = simpleService.method1(1, "Alex", Collections.emptyList(), "Mockito");
        assertThat(result, equalTo(100));

        result = simpleService.method1(1, "Alien", Collections.emptySet(), "MockitoForJava");
        assertThat(result, equalTo(100));

    }

    /**
     * method name: WildcardMethod1WithSpec <br/>
     * description: 第二个参数匹配特殊指定的值，其他参数匹配任意值
     * @return: void
     * @since: 2019-11-23
     */
    @Test
    public void WildcardMethod1WithSpec() {
        // 以下第一条代码必须在第二条和第三条前面，因为第一条的范围比后两条大
        // 所以第一条如果写在第二条和第三条之后就会把第二条和第三条的thenReturn结果覆盖了
        when(simpleService.method1(anyInt(), anyString(), anyCollection(), isA(Serializable.class))).thenReturn(-1);
        when(simpleService.method1(anyInt(), eq("Alex"), anyCollection(), isA(Serializable.class))).thenReturn(100);
        when(simpleService.method1(anyInt(), eq("Alien"), anyCollection(), isA(Serializable.class))).thenReturn(200);

        int result = simpleService.method1(1, "Alex", Collections.emptyList(), "Mockito");
        assertThat(result, equalTo(100));

        result = simpleService.method1(1, "Alien", Collections.emptySet(), "MockitoForJava");
        assertThat(result, equalTo(200));

        result = simpleService.method1(1, "dfadf", Collections.emptySet(), "MockitoForJava");
        assertThat(result, equalTo(-1));
    }

    /**
     * method name: WildcardMethod2 <br/>
     * description: 无返回值验证
     * @return: void
     * @since: 2019-11-23
     */
    @Test
    public void WildcardMethod2() {
        doNothing().when(simpleService).method2(anyInt(), anyString(), anyCollection(), isA(Serializable.class));

        simpleService.method2(1, "Alex", Collections.emptyList(), "Mockito");
        verify(simpleService, times(1)).method2(1, "Alex", Collections.emptyList(), "Mockito");
        verify(simpleService, times(1)).method2(anyInt(), eq("Alex"), anyCollection(), isA(Serializable.class));
    }



    @After
    public void destory() {
        reset(simpleService);
    }
}
