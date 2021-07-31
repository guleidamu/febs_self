package com.damu.febs.server.test.bySelf.jvm;

/**
 * @ Author     ：damu
 * @ Date       ：Created in 19:21 2021/7/15
 * @ Modified By：
 * @Version: 1.0.0
 *
 * 栈上分配测试
 *
 * 栈上分配测试
 *  -Xmx1G -Xms1G -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 */
public class StackAllocation {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("花费的时间" + (end - start) + "ms");
        try{
            Thread.sleep(1000000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void alloc(){
        User user = new User();
    }
    static class User{
        private String name = "kkmm";
    }

}
