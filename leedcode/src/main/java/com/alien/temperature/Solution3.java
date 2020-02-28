package com.alien.temperature;

import java.util.Arrays;

/**
 * program: myStudy <br/>
 * description:<br/>
 * 从后向前遍历了，将气温对应的天数记录在数组中，这样每向前遍历一个，就遍历一次这个长度为100的数组，
 * 如果有比当前温度高的，则更新结果，否则就记为0。<br/>
 *
 * 虽然每次都要遍历一次长度为100的数组，但因为数组查询的时间复杂度为O(1)，所以速度是很快的。
 * 但是空间复杂度高于方案2 (详见 {@link Solution2})<br/>
 *
 * @author: alien
 * @since: 2020/02/28 23:53
 */
public class Solution3 {
    public static int[] dailyTemperatures(int[] T) {
        // 最终结果
        int[] result = new int[T.length];
        // 因为温度不超过100度，所以温度对应的天数存储在这个数组中
        // 如果当前温度有记录者小于最大int类型整数，并在对应位置上记录数组的序号
        int[] next = new int[101];
        Arrays.fill(next, Integer.MAX_VALUE);
        // 从后向前遍历
        for (int i = T.length - 1; i >= 0; --i) {
            // 比当前温度更高的下标
            int warmerIndex = Integer.MAX_VALUE;
            // 从next数组中查找比当前温度高的天数
            for (int t = T[i] + 1; t <= 100; ++t) {
                // 找出天数最小的一个
                if (next[t] < warmerIndex) {
                    warmerIndex = next[t];
                }
            }
            // 如果有找到，则更新result
            if (warmerIndex < Integer.MAX_VALUE) {
                result[i] = warmerIndex - i;
            }
            // 在next数组中记录当前的温度
            next[T[i]] = i;
        }
        return result;
    }
}
