package com.damu.febs.server.test.byMySelf.tryCatch;

public class ver {
    public static void main(String[] args) {
        int i = test1();
        System.out.println(i);
    }

    /**
     * 说明return语句已经执行了再去执行finally语句，不过并没有直接返回，而是等finally语句执行完了再返回结果。
     *
     * @return
     */
    public static int test1() {
        int b = 20;
        try {
            System.out.println("try block");
            return b += 90;
        } catch (Exception e) {
            System.out.println("catch");
            e.printStackTrace();
        } finally {
            System.out.println("finally block");
            if (b > 20) {
                System.out.println("b>25, b=" + b);
            }
            b = 150;
            System.out.println("b:" + b);
        }
        return 666;
    }
}
