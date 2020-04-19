package com.flow.snow.snow.learn;

/**
 * 单例模式案例 饿汗式
 */
public class SingleDemo {
    // 私有构造器
    private SingleDemo(){

    }


    private final static SingleDemo singleDemo = new SingleDemo();

    public static SingleDemo getInstance(){
        return singleDemo;
    }

    public static void main(String[] args) {
        SingleDemo singleDemo = SingleDemo.getInstance();
        SingleDemo singleDemo1 = SingleDemo.getInstance();
        System.out.println(singleDemo==singleDemo1);;
    }
}
