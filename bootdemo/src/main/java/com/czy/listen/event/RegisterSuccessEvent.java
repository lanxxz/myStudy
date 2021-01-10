package com.czy.listen.event;

import org.springframework.context.ApplicationEvent;

/**
 * program: myStudy
 * description: 注册成功的事件
 *
 * @author: alien
 * @since: 2021/01/10 22:55
 */
public class RegisterSuccessEvent extends ApplicationEvent {

    public RegisterSuccessEvent(Object source) {
        super(source);
    }
}
