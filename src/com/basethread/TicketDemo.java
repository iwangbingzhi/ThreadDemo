package com.basethread;

/*
普通同步函数的锁是this;
静态同步函数的锁是class对象
同步方法块锁是括号里面的对象
*/
public class TicketDemo {
    public static void main(String[] args) {

        TicketThread t = new TicketThread();

        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);

        t1.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.flag = false;
        t2.start();
    }
}

class TicketThread implements Runnable {
    private static int TicketNum = 150;
    Boolean flag = true;
    public void run() {
        if (flag){
        while (true) {
            synchronized (TicketThread.class) {
                if (TicketNum > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "...runcode" + TicketNum-- + "号票");
                }
            }
        }
    }else{
        while(true) {
            showTickNums();
        }
        }
    }

    public static synchronized void showTickNums() {
        if (TicketNum > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "...showcode" + TicketNum-- + "号票");

        }
    }
}