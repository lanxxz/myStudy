package com.czy.listen;

import com.czy.listen.event.RegisterSuccessEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * program: myStudy
 * description: 注册逻辑业务层
 *
 * @author: alien
 * @since: 2021/01/10 23:00
 */
@Service
public class RegisterService implements ApplicationEventPublisherAware {
    // 发布者，事件源
    ApplicationEventPublisher publisher;

    public void register(String username) {
        // 用户注册的动作。。。
        System.out.println(username + "注册成功。。。");
        // 发布事件
        publisher.publishEvent(new RegisterSuccessEvent(username));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }
}
