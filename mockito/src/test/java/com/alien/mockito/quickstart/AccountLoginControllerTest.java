package com.alien.mockito.quickstart;

import com.alien.mockito.common.Account;
import com.alien.mockito.common.AccountDao;
import com.alien.mockito.common.AccountLoginController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * program: myStudy
 * description: 登录测试
 *
 * @author: alien
 * @since: 2019/11/22 17:58
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountLoginControllerTest {

    private AccountDao accountDao;

    private HttpServletRequest request;

    private AccountLoginController controller;

    /**
     * method name: setUp <br/>
     * description: mock 打桩

     * @return: void
     * @since: 2019-11-22
     */
    @Before
    public void setUp() {
        accountDao = Mockito.mock(AccountDao.class);
        request = Mockito.mock(HttpServletRequest.class);
        controller = new AccountLoginController(accountDao);
    }

    /**
     * method name: testLoginSuccess <br/>
     * description: 登录成功测试
     * @return: void
     * @since: 2019-11-22
     */
    @Test
    public void testLoginSuccess() {
        Account account = new Account();
        when(request.getParameter("username")).thenReturn("alex");
        when(request.getParameter("password")).thenReturn("123456");
        when(accountDao.findAccount(anyString(), anyString())).thenReturn(account);

        String login = controller.login(request);
        assertThat(login, equalTo("/index.jsp"));
    }

    /**
     * method name: testLoginFailure <br/>
     * description: 登录失败测试
     * @return: void
     * @since: 2019-11-22
     */
    @Test
    public void testLoginFailure() {
        when(request.getParameter("username")).thenReturn("alex");
        when(request.getParameter("password")).thenReturn("123456");
        when(accountDao.findAccount(anyString(), anyString())).thenReturn(null);

        String login = controller.login(request);
        assertThat(login, equalTo("/login"));
    }

    /**
     * method name: test505 <br/>
     * description: 测试异常处理
     * @return: void
     * @since: 2019-11-22
     */
    @Test
    public void test505() {
        when(request.getParameter("username")).thenReturn("alex");
        when(request.getParameter("password")).thenReturn("123456");
        when(accountDao.findAccount(anyString(), anyString())).thenThrow(UnsupportedOperationException.class);

        String login = controller.login(request);
        assertThat(login, equalTo("/505"));
    }
}
