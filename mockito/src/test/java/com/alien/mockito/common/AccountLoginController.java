package com.alien.mockito.common;

import javax.servlet.http.HttpServletRequest;

/**
 * program: myStudy
 * description: 登录
 *
 * @author: alien
 * @since: 2019/11/22 17:46
 */
public class AccountLoginController {

    private final AccountDao accountDao;

    public AccountLoginController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public String login(HttpServletRequest request) {
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        try {
            Account account = accountDao.findAccount(username, password);
            if (account == null) {
                return "/login";
            } else {
                return "/index.jsp";
            }
        } catch (Exception e) {
            return "/505";
        }
    }

}
