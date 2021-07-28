package com.damu.febs.server.test.bySelf.jvm.Heap;

/**
 * @ Author     ：damu
 * @ Date       ：Created in 19:51 2021/7/8
 * @ Modified By：
 * @Version: 1.0.0
 */
public class HeapSpaceInitial {
    public static void main(String[] args) {

        //返回Java虚拟机中的堆内存总量
        long totalMemory = Runtime.getRuntime().totalMemory() /1024/1024;

        //返回Java虚拟机试图使用的最大堆内存总量
        long maxMemory = Runtime.getRuntime().maxMemory()/1024/1024;

        System.out.println("-Xms " +totalMemory + "M");
        System.out.println("-xmx " + maxMemory + "M");

        System.out.println("系统内存大小" + totalMemory* 64.0/1024);
        System.out.println("系统内存大小" + maxMemory* 4.0/1024);
    }
}
