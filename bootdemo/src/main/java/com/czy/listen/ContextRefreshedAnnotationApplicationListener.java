package com.czy.listen;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * program: myStudy
 * description: 自定义注解式监听
 *
 * @author: alien
 * @since: 2021/01/10 22:45
 */
@Component
public class ContextRefreshedAnnotationApplicationListener {

    @EventListener
    public void onContextClosedEvent(ContextClosedEvent event) {
        System.out.println("ContextRefreshedAnnotationApplicationListener监听到ContextClosedEvent事件！");
    }
}
