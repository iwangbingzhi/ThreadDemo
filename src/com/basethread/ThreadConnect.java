package com.basethread;

//线程间通信：多个线程操作同一个资源
//等待唤醒机制
public class ThreadConnect {
    public static void main(String[] args) {
        Res r = new Res();

        Input in = new Input(r);
        Output ou = new Output(r);

        Thread t1 = new Thread(in);
        Thread t2 = new Thread(ou);

        t1.start();
        t2.start();
    }
}
class Res{
    private String name;
    private String sex;
    private boolean flag = false;
    public synchronized void set(String name,String sex){
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            this.name = name;
            this.sex = sex;
            flag = true;
            this.notify();

    }
    public synchronized void out(){
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            System.out.println(name+"......"+sex);
            flag = false;
            this.notify();
    }
}

class Input implements Runnable{
    private Res r;
    boolean b = true;

    Input(Res r){
        this.r = r;
    }
    public void run(){
        while (true) {
                if (b) {
                    r.set("jay","男");
                    b = false;
                } else {
                    r.set("leehom","nvnvnvnv");
                    b = true;
        }
        }
    }
}

class Output implements Runnable{
    private Res r;
    Output(Res r){
        this.r = r;
    }
    public void run(){
        while (true) {
           r.out();
        }
    }
}
