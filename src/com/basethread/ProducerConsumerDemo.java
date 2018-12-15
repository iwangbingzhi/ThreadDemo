package com.basethread;

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Resource r = new Resource();

        Producer p = new Producer(r);
        Consumer c = new Consumer(r);

        Thread t1 = new Thread(p);
        Thread t2 = new Thread(c);
        Thread t3 = new Thread(p);
        Thread t4 = new Thread(c);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class Resource{
    private String name;
    private int count = 1;
    private boolean flag = false;

    public synchronized void set(String name){
        while (flag) {
            System.out.println("生产者的flag="+flag);
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            this.name = name + "--" + count++;
            System.out.println(Thread.currentThread().getName() + "---生产者" + this.name);
            flag = true;
            this.notifyAll();
    }
    public synchronized void out(){
        while (!flag){
            System.out.println("消费者的!flag="+!flag);
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"---消费者"+this.name);
        flag = false;
        this.notifyAll();
    }
}


class Producer implements Runnable{
    private Resource res;

    Producer(Resource res){
        this.res = res;
    }

    public void run() {
        while (true) {
            res.set("+iphone X+");
        }
    }
}

class Consumer implements Runnable{
    private Resource res;

    Consumer(Resource res){
        this.res = res;
    }

    public void run() {
        while (true) {
            res.out();
        }
    }
}

