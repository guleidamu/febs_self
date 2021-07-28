package com.damu.febs.server.test.bySelf.fzpMs;

import lombok.val;

/**
 * @ Author     ：damu
 * @ Date       ：Created in 11:25 2021/6/26
 * @ Modified By：
 * @Version: 1.0.0
 */
public class equalH {

    public static void main(String[] args) {

        String string = new String("abc");

        String string1 = "abc";
        String string2 = "abc";
        String string3 = "a"+"bc";
        Object o1 = new Object();
        Object o2 = new Object();
        System.out.println(string.equals(string1));
        System.out.println(string == string1);

        System.out.println(string1.equals(string2));//true
        System.out.println(string1==string2);//true

        System.out.println(string1 == string3);//true
        System.out.println(string1.equals(string3));//true

        System.out.println(o1.equals(o2));//false
        System.out.println(o1 == o2);//false

    }
}
