package com.alien.temperature;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * program: myStudy <br/>
 * description:<br/>
 * 优先队列<br/>
 * 如果正向思考的话，就是从前向后遍历，将温度存储在一个优先级队列中（小顶堆），队列中的结构需要记录温度和天数。<br/>
 *
 * 遍历时需要找到队列中最小的值是否大于当前温度，如果大于，则更新结果；如果小于，则将当前记录放入队列中。<br/>
 * 时间复杂度: o(n)
 *
 * @author: alien
 * @since: 2020/02/28 22:06
 */
public class Solution1 {

    public static int[] dailyTemperatures(int[] t) {
        int[] result = new int[t.length];
        // 以温度为排序依据的小顶堆，温度越低越靠前
        PriorityQueue<Node> queue =
                new PriorityQueue<>(Comparator.comparingInt(Node::getTemperature));
        for (int index = 0; index < t.length; index++) {
            Node node = new Node();
            node.setIndex(index);
            node.setTemperature(t[index]);
            queue.add(node);

            // 取队列中最小的元素
            Node newNode = queue.peek();
            // 如果队列中最低温度就是当前温度
            if (newNode.getTemperature() == node.getTemperature()) {
                // 说明queue中没有比当前温度低的天
                continue;
            }

            // 最低温度比当前低满足要求
            while (newNode.getTemperature() < node.getTemperature()) {
                // 更新 t，并移除
                result[newNode.getIndex()] = node.getIndex() - newNode.getIndex();
                queue.remove();
                newNode = queue.peek();
            }
            
        }

        // 队列中剩余的节点，都对应0
        for (Node node: queue) {
            result[node.getIndex()] = 0;
        }

        return result;
    }

    static class Node {
        private int temperature;
        private int index;

        public int getTemperature() {
            return temperature;
        }

        public void setTemperature(int temperature) {
            this.temperature = temperature;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

}
