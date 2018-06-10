package com.basethread;



/*单例模式与多线程*/
public class SingleDemo {
    public static void main(String[] args) {

    }
}
class Single{
/*
    饿汉式
    private static final Single s = new Single();
    private Single(){

    }
    public static Single getInstance(){
        return s;
    }*/


    //懒汉式
    private static Single s = null;
    private Single(){

    }
    public static Single getInstance(){
        if(s==null) {
            synchronized (Single.class) {
                if (s == null) {
                    s = new Single();
                }
            }
        }
        return s;
    }
}
