package com.basethread;

public class ComputerTest {
    public static void main(String[] args) {
        Factory factory = new Factory();
        new Thread(new ShengChaner(factory)).start();
        new Thread(new XiaoFeier(factory)).start();
    }
}
class Computer{
    private String name;
    private String price;
    private static int counte = 0;

    @Override
    public String toString() {
        return "【第"+counte+"台】"+"电脑名字："+this.name+"--电脑价格："+this.price;
    }

    public Computer(String name, String price) {
        this.name = name;
        this.price = price;
        counte++;
    }
}
class Factory{
    private Computer computer;

    public synchronized void make() throws InterruptedException {
        if (computer != null) {
            wait();
        }
        Thread.sleep(10);
        computer = new Computer("macbook pro","2w");
        System.out.println("生产者===="+this.computer);
        notifyAll();
    }

    public synchronized void get() throws InterruptedException {
        if (computer == null) {
            wait();
        }
        Thread.sleep(10);
        System.out.println("消费者===="+this.computer);
        this.computer = null;
        notifyAll();
    }
}
class XiaoFeier implements Runnable{
    private Factory factory;

    public XiaoFeier(Factory factory) {
        this.factory = factory;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                factory.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ShengChaner implements Runnable{
    private Factory factory;

    public ShengChaner(Factory factory) {
        this.factory = factory;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                factory.make();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
