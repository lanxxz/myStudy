package com.alien.temperature;

import java.util.Arrays;

/**
 * program: myStudy
 * description: 测试入口
 *
 * @author: alien
 * @since: 2020/02/28 23:39
 */
public class Main {

    public static void main(String[] args) {
        int[] t = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println("当前温度：");
        System.out.println(Arrays.toString(t));

        long t1 = System.currentTimeMillis();
        int[] solution1 = Solution1.dailyTemperatures(t);
        long t2 = System.currentTimeMillis();
        System.out.println("Solution1方案：温度比当前温度高经过的天数：");
        System.out.println(Arrays.toString(solution1));
        System.out.println(t2 - t1);


        t1 = System.currentTimeMillis();
        int[] solution2 = Solution2.dailyTemperatures(t);
        t2 = System.currentTimeMillis();
        System.out.println("Solution2方案：温度比当前温度高经过的天数：");
        System.out.println(Arrays.toString(solution2));
        System.out.println(t2 - t1);
    }
}
