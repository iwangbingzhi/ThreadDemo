package com.basethread;

public class VolatileDemo {
    public static void main(String[] args) {
        TicketDemo2 ticketDemo2 = new TicketDemo2();
        Thread thread = new Thread(ticketDemo2,"票贩子A");
        Thread thread2 = new Thread(ticketDemo2,"票贩子B");
        Thread thread3 = new Thread(ticketDemo2,"票贩子C");
        thread.start();
        thread2.start();
        thread3.start();
    }
}
class TicketDemo2 implements Runnable{
    private volatile int ticket = 5;
    @Override
    public void run() {
        synchronized (this) {
            while (this.ticket > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "售票" + ticket--);
            }
        }
    }
}
