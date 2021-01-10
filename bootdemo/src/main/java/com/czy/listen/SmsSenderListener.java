package com.czy.listen;

import com.czy.listen.event.RegisterSuccessEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * program: myStudy
 * description: 发送短信监听器
 *
 * @author: alien
 * @since: 2021/01/10 22:56
 */
@Component
public class SmsSenderListener implements ApplicationListener<RegisterSuccessEvent> {
    @Override
    public void onApplicationEvent(RegisterSuccessEvent event) {
        System.out.println("监听到用户注册成功，发送短信。。。");
    }
}
