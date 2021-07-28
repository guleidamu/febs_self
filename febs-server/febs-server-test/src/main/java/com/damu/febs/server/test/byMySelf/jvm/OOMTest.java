package com.damu.febs.server.test.byMySelf.jvm;


import java.util.ArrayList;
import java.util.Random;

/**
 * 默认情况新生代老年代内存占比1:2
 * -Xms600m -Xmx600m  -Xx:+PrintGCDetails
 * -XX:+PrintGCDetails(查看垃圾回收细节)
 * -XX:SurvivorRatio 调整比例 例如-XX:SurvivorRatio=8   eden空间和另外两个survivor空间缺省默认所占比例是6:1:1
 * -Xms600m 堆最小大小600m
 * -Xxs  用来设置堆空间（年轻代+老年代）最大内存大小
 */
public class OOMTest {
    public static void main(String[] args) {
        ArrayList list = new ArrayList<>();
        while (true){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new Picture(new Random().nextInt(1024*1024)));
        }
    }

 static  class Picture{
        private byte[] pixels;

        public Picture(int length){
            this.pixels = new byte[length];
        }
    }
}
