package com.google.myselfcode;

public class Test {
    public static void main(String[] args) {
        JoinTest jt = new JoinTest();

        Thread t = new Thread(jt);
        Thread t2 = new Thread(jt);

        t.start();
        t2.start();
    }
}
