package com.basethread;
/**
 * 线程的中断  interrupt
 *
 * */
public class EatAndCall {
    public static void main(String[] args) throws InterruptedException {
        EatAndCallThread ect = new EatAndCallThread();
        Thread thread = new Thread(ect);
        thread.start();
        Thread.sleep(10);
        if (!thread.isInterrupted()){
            System.out.println(Thread.currentThread().getName()+"有电话呼叫~~嘟嘟嘟嘟");
            thread.interrupt();
        }
    }
}
class EatAndCallThread implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"正在吃饭中哦~~");
        try {
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName()+"吃完一顿美美的大餐~~");
        }catch (InterruptedException e){
            System.out.println(Thread.currentThread().getName()+"我在吃饭别打电话打扰我吃饭~~");
        }
    }
}


