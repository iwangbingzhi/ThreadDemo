package com.basethread;

public class AddSubTest {
    public static void main(String[] args) {
        Nums nums = new Nums();
        Adder adder = new Adder(nums);
        Suber suber = new Suber(nums);
        Thread thread1 = new Thread(adder);
        Thread thread2 = new Thread(suber);
        thread1.start();
        thread2.start();


    }
}
class Nums{
    private int num = 0;
    private Boolean flag = false;

    public synchronized void add() throws InterruptedException{
        while(flag){
            wait();
        }
        Thread.sleep(100);

        System.out.println("【加法运算】--------num="+num++);
        flag = true;
        notifyAll();
    }

    public synchronized void sub() throws InterruptedException{
        while(!flag){
            wait();
        }
        Thread.sleep(200);

        System.out.println("【减法运算】--------num="+num--);
        flag = false;
        notifyAll();
    }
}
class Adder implements Runnable{
    private Nums nums;

    public Adder(Nums nums) {
        this.nums = nums;
    }

    @Override
    public void run() {
        for (int i = 0; i <10 ; i++) {
            try {
                nums.add();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Suber implements Runnable{
    private Nums nums;

    public Suber(Nums nums) {
        this.nums = nums;
    }

    @Override
    public void run() {
        for (int i = 0; i <10 ; i++) {
            try {
                nums.sub();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
