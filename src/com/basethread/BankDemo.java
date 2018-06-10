package com.basethread;

public class BankDemo {
    public static void main(String[] args) {
        Cus c = new Cus();
        Thread t = new Thread(c);
        Thread t2 = new Thread(c);
        t.start();
        t2.start();
    }
}
class Bank{
    //银行金库总数
    private int sum;
    public synchronized void add(int n) {
            sum += n;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sum=" + sum);
    }
}
class Cus implements Runnable{
    private Bank b = new Bank();
    public void run(){
        for (int i = 0; i <3 ; i++) {
            b.add(100);
        }
    }
}
