package com.basethread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ResponderTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Responder responder = new Responder();
        FutureTask<String> futureTaskA = new FutureTask<String>(responder);
        FutureTask<String> futureTaskB = new FutureTask<String>(responder);
        FutureTask<String> futureTaskC = new FutureTask<String>(responder);

        Thread thread1 = new Thread(futureTaskA,"竞赛者A");
        thread1.start();

        Thread thread2 = new Thread(futureTaskB,"竞赛者B");
        thread2.start();
        thread2.setPriority(10);

        Thread thread3 = new Thread(futureTaskC,"竞赛者C");
        thread3.start();
        thread3.setPriority(10);

        System.out.println(futureTaskA.get());
        System.out.println(futureTaskB.get());
        System.out.println(futureTaskC.get());
    }
}
class Responder implements Callable<String>{
    private Boolean flag = true;
    @Override
    public String call() throws Exception {
        synchronized(this){
            if (this.flag == true){
                this.flag = false;
                return Thread.currentThread().getName()+"抢答成功！！";
            }else {
                return Thread.currentThread().getName()+"抢答失败！！";
            }
        }
    }
}
