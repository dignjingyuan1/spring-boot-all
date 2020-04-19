package com.flow.snow.snow.learn;

import lombok.Data;

/**
 * 测试类
 */
@Data
public class Dog{
    private String name;
    private int price;

    public Dog(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
