package com.alien.mockito.mockmode;

import com.alien.mockito.common.AccountDao;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * program: myStudy
 * description:  role test
 * role 就是 junit里面的runwith
 * @author: alien
 * @since: 2019/11/22 19:33
 */
public class MockByRoleTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private AccountDao accountDao;

    @Test
    public void testMock() {
        accountDao.findAccount("x", "y");
    }
}
