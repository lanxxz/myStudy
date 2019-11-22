package com.alien.mockito.stubbing;

/**
 * program: myStudy
 * description: 假设是一个很复杂的外部资源
 *
 * @author: alien
 * @since: 2019/11/22 21:22
 */
public class StubbingService {

    public int getI() {
        System.out.println("=====getI()=====");
        return 10;
    }

    public String getS() {
        System.out.println("====getS()=====");
        throw new RuntimeException();
    }
}
