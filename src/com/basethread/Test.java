package com.basethread;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Thread  userThread = new Thread(()->{
            long num = 0;
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+num++);
            }

        },"用户线程");

        Thread  daemonThread = new Thread(()->{
            long num = 0;
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+num++);
            }

        },"守护线程");
        daemonThread.setDaemon(true);  //设置为守护线程
        userThread.start();
        daemonThread.start();
    }
}
