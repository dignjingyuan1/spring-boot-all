package com.flow.snow.snow.learn;

import lombok.SneakyThrows;

public class TreadTest extends Thread{
    private String name;

    public TreadTest(String name) {
        this.name = name;
    }

    @SneakyThrows
    @Override
    public void run() {
        sleep(1000l);
        System.out.println(this.name + "执行了");
    }

    public static void main(String[] args) throws InterruptedException {
        TreadTest treadTest1 = new TreadTest("线程1");
        treadTest1.start();
        System.out.println("线程1启动");
        treadTest1.join(); // 等待此线程执行完才能继续执行其他等待的线程
        TreadTest treadTest2 = new TreadTest("线程2");
        treadTest2.start();
        System.out.println("线程2启动");
        treadTest2.join(); // 等待此线程执行完才能继续执行其他等待的线程
        TreadTest treadTest3 = new TreadTest("线程3");
        treadTest3.start();
        System.out.println("线程3启动");
        TreadTest treadTest4 = new TreadTest("线程4");
        treadTest4.start();
        System.out.println("线程4启动");
        TreadTest treadTest5 = new TreadTest("线程5");
        treadTest5.start();
        System.out.println("线程5启动");
    }
}
