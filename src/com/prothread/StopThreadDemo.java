package com.prothread;


public class StopThreadDemo {
    public static void main(String[] args) {
        StopThread stopThread = new StopThread();
        Thread t1 = new Thread(stopThread);
        Thread t2 = new Thread(stopThread);


        t1.setDaemon(true);  //进入守护线程，前台所有的线程都停止后，后台程序也会跟着全部停止
        /*t2.setDaemon(true);*/
        t1.start();
        t2.start();

        int num = 0;
        while (true){
            if (num++ == 20) {
//                t1.interrupt();   //中断  将线程从sleep或者wait等状态中恢复成运行状态
//                t2.interrupt();
                break;
            }
            System.out.println(Thread.currentThread().getName()+"main......"+num);
        }
        System.out.println("main over");
    }

}
class StopThread implements Runnable{
    private boolean flag = true;
    public synchronized void run(){
        while (flag){
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+"execption....");
                flag = false;
            }
            System.out.println(Thread.currentThread().getName()+"run....");
        }
    }

}
