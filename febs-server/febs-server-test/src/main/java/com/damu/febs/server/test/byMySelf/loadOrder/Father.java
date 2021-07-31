package com.damu.febs.server.test.byMySelf.loadOrder;

public class Father {

    private int fa;

    private static int fastatic;

    static {
        System.out.println("Father类， static代码块");
    }

    {
        System.out.println("Father类，{}代码块");
    }

    public Father() {
        System.out.println("Father类，构造方法");
    }
}
