package com.basethread;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * callable创建线程,并返回数据
 *
 * **/
public class TestThd {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread2 thread = new MyThread2();
        FutureTask<String> task = new FutureTask<>(thread);
        new Thread(task,"票贩子A").start();
        new Thread(task,"票贩子B").start();
        System.out.println(task.get());
    }
}
class MyThread2 implements Callable<String>{
    private int ticket = 50;
    @Override
    public String call() {
        for (int i = 0; i < 100; i++) {
            if (this.ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "卖票=" + this.ticket--);
            }
        }
        return "线程执行完毕";
    }
}

class MyThread implements Runnable{
    private int ticket = 5;
    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (this.ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "卖票=" + this.ticket--);
                } else {
                    System.out.println(Thread.currentThread().getName() +"车票已售罄--------");
                    break;
                }
            }
        }
    }
}
