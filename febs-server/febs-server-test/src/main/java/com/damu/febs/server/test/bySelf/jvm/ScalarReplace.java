package com.damu.febs.server.test.bySelf.jvm;

/**
 * @ Author     ：damu
 * @ Date       ：Created in 20:11 2021/7/15
 * @ Modified By：
 * @Version: 1.0.0
 * 标量替换测试
 * -Xmx100m -Xms100m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-EliminateAllocations
 */
public class ScalarReplace {

    public static class User{
        public int id;
        public String name;
    }

    public static void alloc(){
        User user = new User();
        user.id = 3;
        user.name = "meiliaichangge";
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000200; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("花费的时间" + (end - start) + "ms");
        try{
            Thread.sleep(1020000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
