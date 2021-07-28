package com.damu.febs.server.test.bySelf.fzpMs;

/**
 * @ Author     ：damu
 * @ Date       ：Created in 9:31 2021/6/26
 * @ Modified By：
 * @Version: 1.0.0
 */
public class IntegerAndInt {
    public static void main(String[] args) {
        int int1 = 18;
        int int2 = new Integer(18);
        Integer integer1 = new Integer(18);
        Integer integer2 = new Integer(18);
        Integer integer3 = 18;
        Integer integer4 = 18;
        Integer integer5 = 128;
        Integer integer6 = 128;
        System.out.println(int1 == int2); //true
        System.out.println(int1 == integer1); //true
        System.out.println(int1 == integer3); //true
        System.out.println("integer1 == integer2:" + (integer1 == integer2));//false
        System.out.println("integer1 == int1:" + (integer1 == int1));//true
        System.out.println("int1 == integer1:" + (int1 == integer1));//true 因为包装类Integer和基本数据类型int比较时，java会自动拆包装为int，然后进行比较，实际上就变为两个int变量的比较
        System.out.println("integer1 == integer3:" + (integer1 == integer3));//false 非new生成的Integer变量指向的是java常量池中的对象，而new Integer()生成的变量指向堆中新建的对象，两者在内存中的地址不同
        System.out.println(integer3 == integer4);//true
        System.out.println(integer5 == integer6);//false
        printLine();

        System.out.println(integer1.equals(integer2));//true
        System.out.println(integer1.equals(integer3));//true
        System.out.println(integer3.equals(int1));//true




    }

    private static void printLine() {
        System.out.println(">>>>>>>>>>>>分割线>>>>>>>>>>");
    }
}
