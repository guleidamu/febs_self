package com.damu.febs.server.test.byMySelf.jvm.stack;

public class xssTest {

    static int count = 1;

    public static void main(String[] args) {
        byte i = 5;
        System.out.println(count++);
        main(null);
    }
}
