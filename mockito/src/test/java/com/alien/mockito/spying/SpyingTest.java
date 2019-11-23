package com.alien.mockito.spying;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * program: myStudy
 * description: spying test 部分方法mock
 *
 * @author: alien
 * @since: 2019/11/23 16:33
 */
@RunWith(MockitoJUnitRunner.class)
public class SpyingTest {

    /**
     * method name: testSpy <br/>
     * description: 部分方法mock

     * @return: void
     * @since: 2019-11-23
     */
    @Test
    public void testSpy() {
        List<String> realList = new ArrayList<>();
        List<String> list = spy(realList);

        list.add("Mockito");
        list.add("PowerMock");
        assertThat(list.get(0), equalTo("Mockito"));
        assertThat(list.get(1), equalTo("PowerMock"));
        assertThat(list.isEmpty(), equalTo(false));

        when(list.size()).thenReturn(0);
        when(list.isEmpty()).thenReturn(true);

    }
}
