package com.alien.mockito.mockmode;

import com.alien.mockito.common.Account;
import com.alien.mockito.common.AccountDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * program: myStudy
 * description: mock 注解测试
 *
 * @author: alien
 * @since: 2019/11/22 19:26
 */
public class MockByAnnotationTest {

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    // 返回 account 为 null
//    @Mock
//    public AccountDao accountDao;

    // 返回字符串的 account
    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    public AccountDao accountDao;


    @Test
    public void testMock() {
        Account account = accountDao.findAccount("x", "y");
        System.out.println(account);
    }
}
