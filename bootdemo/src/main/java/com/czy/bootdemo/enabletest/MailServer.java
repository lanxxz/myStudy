package com.czy.bootdemo.enabletest;

/**
 * program: myStudy
 * description: Mail服务器
 *
 * @author: alien
 * @since: 2021/01/09 11:51
 */
public class MailServer implements Server {
    @Override
    public void start() {
        System.out.println("Mail 服务器启动中...");
    }

    @Override
    public void run() {
        System.out.println("Mail 服务器运行中...");
    }

    @Override
    public void stop() {
        System.out.println("Mail 服务器停止中...");
    }
}
