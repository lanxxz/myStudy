package com.czy.bootdemo.enabletest;

/**
 * program: myStudy
 * description: FTP 服务器
 *
 * @author: alien
 * @since: 2021/01/09 11:50
 */
public class FtpServer implements Server {
    @Override
    public void start() {
        System.out.println("ftp 服务器启动中...");
    }

    @Override
    public void run() {
        System.out.println("ftp 服务器运行中...");
    }

    @Override
    public void stop() {
        System.out.println("ftp 服务器停止中...");
    }
}
