package com.damu.febs.server.test.byMySelf.loadOrder;

public class Son extends Father {

    private static int s1;
    private int s2;

    static {
        System.out.println("son类，static代码块");
    }

    {
        System.out.println("son类，{}代码块");
    }

    public Son() {
//        super();
        System.out.println("son类，构造代码块");
    }

    public static void main(String[] args) {
        Son son = new Son();
        Son son1 = new Son();
    }
}
