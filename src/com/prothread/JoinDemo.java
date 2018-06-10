package com.prothread;

/*join用来临时加入线程执行*/
public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        Demo d = new Demo();
        Thread t1 = new Thread(d);
        Thread t2 = new Thread(d);
        t1.start();
        //t1.join(); //t1得到cpu执行权
        t2.start();

      /*  for (int i = 0; i <77 ; i++) {
            System.out.println("mian...."+i);
        }*/
        System.out.println("main over");
    }
}
class Demo implements Runnable{
    public void run(){
        for (int i = 0; i <66 ; i++) {
            System.out.println(Thread.currentThread().getName()+"...."+i);
            Thread.yield(); //强制让线程释放执行权
        }
    }
}
