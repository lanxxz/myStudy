package com.alien.mockito.mockmode;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

/**
 * program: myStudy
 * description: 深度Mock<br/>
 * 在mock的时候顺便把里面的也mock了
 *
 * @author: alien
 * @since: 2019/11/22 19:41
 */
public class DeepMockTest {
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Lesson03Service service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeepMock() {
        service.get().foo();
    }
}
