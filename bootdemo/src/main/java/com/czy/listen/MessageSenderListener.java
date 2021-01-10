package com.czy.listen;

import com.czy.listen.event.RegisterSuccessEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * program: myStudy
 * description: 站内信监听器
 *
 * @author: alien
 * @since: 2021/01/10 22:59
 */
@Component
public class MessageSenderListener {

    @EventListener
    public void onRegisterSuccess(RegisterSuccessEvent event) {
        System.out.println("监听到用户注册成功，发送站内信。。。");
    }
}
