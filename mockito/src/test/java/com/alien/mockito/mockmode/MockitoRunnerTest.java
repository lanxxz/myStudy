package com.alien.mockito.mockmode;

import com.alien.mockito.common.Account;
import com.alien.mockito.common.AccountDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

/**
 * program: myStudy
 * description: RunWith方式
 *
 * @author: alien
 * @since: 2019/11/22 19:07
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoRunnerTest {

    @Test
    public void testMock() {
        // 返回一个 null类型 的 account
//        AccountDao accountDao = mock(AccountDao.class);
        // 返回一个字符串的 account结果
        AccountDao accountDao = mock(AccountDao.class, Mockito.RETURNS_SMART_NULLS);
        Account account = accountDao.findAccount("x", "y");
        System.out.println(account);
    }
}
