package com.prothread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
将synchronized替换成lock操作 将wait替换成condition对象，该对象可以通过lock获取
该例中，实现了本方只唤醒对方的操作
*/
public class ProducerConsumerDemo2 {
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
    class Resource {
        private String name;
        private int count = 1;
        private boolean flag = false;
        private Lock lock = new ReentrantLock();
        private Condition condition_pro = lock.newCondition();
        private Condition condition_con = lock.newCondition();

        public void set(String name) {
            lock.lock();
            try {
                while (flag) {
                    condition_pro.await();
                }
                this.name = name + "--" + count++;
                System.out.println(Thread.currentThread().getName() + "---生产者生产了" + this.name);
                flag = true;
                condition_con.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
            public void out () {
                lock.lock();
                try {
                    while (!flag) {
                        condition_con.await();
                    }
                    System.out.println(Thread.currentThread().getName() + "---消费者消费了" + this.name);
                    flag = false;
                    condition_pro.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
    }
    class Producer implements Runnable{
        private Resource res;

        Producer(Resource res){
            this.res = res;
        }

        public void run() {
            while (true){
                res.set("+MacBook+");
            }
        }
    }

    class Consumer implements Runnable{
        private Resource res;

        Consumer(Resource res){
            this.res = res;
        }

        public void run() {
            while (true){
                res.out();
            }
        }
    }

