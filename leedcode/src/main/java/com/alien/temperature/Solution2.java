package com.alien.temperature;

import java.util.Arrays;
import java.util.Stack;

/**
 * program: myStudy<br/>
 * description: <br/>
 * 最近的比当前温度高的天数，这样的需求，感觉栈应该是可以满足的，因为先进后出。<br/>
 *
 * 我们还是从后向前遍历，在栈中存储气温的天数，当前遍历到的温度，
 * 如果比栈顶的存储的天数对应的温度高，那么栈中存储的是没有意义的；如果比它低，那么就可以更新结果了。<br/>
 * 时间复杂度: o(1)
 *
 * @author: alien
 * @since: 2020/02/28 23:30
 */
public class Solution2 {

    public static int[] dailyTemperatures(int[] t) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[t.length];

        // 从后向前遍历
        for (int index = t.length - 1; index >= 0; index--) {
            while (!stack.isEmpty() && t[index] > t[stack.peek()]) {
                // 因为当前温度比栈顶存储的温度高，
                // 栈顶元素也没有存储的意义，所以出栈
                stack.pop();
            }

            // 如果栈为空，则结果为0
            // 如果栈不为空，则用当前栈顶存储的温度
            result[index] = stack.isEmpty()? 0: (stack.peek() - index);
            // 当前温度进栈
            stack.push(index);
        }

        return result;
    }

}
