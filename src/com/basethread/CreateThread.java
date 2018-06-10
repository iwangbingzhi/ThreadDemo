package com.basethread;

public class CreateThread {
    public static void main(String[] args) {
        SonThread sonThread = new SonThread("one");
        SonThread sonThread2 = new SonThread("two");

        System.out.println("main thread!");

        sonThread.start();
        sonThread2.start();
    }

}
class SonThread extends Thread{
    private String name;
    SonThread(String name){
        this.name = name;
    }
    public void run(){
        for (int i = 0; i <10; i++) {
            System.out.println(this.getName()+"sonthread run!");
        }

    }
}


