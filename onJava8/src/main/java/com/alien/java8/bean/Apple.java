package com.alien.java8.bean;

/**
 * program: myStudy
 * description: 苹果类
 *
 * @author: alien
 * @since: 2019/11/30 23:01
 */
public class Apple {
    // 颜色
    private String color;
    // 重量
    private Integer weight;

    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
