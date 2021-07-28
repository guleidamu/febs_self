package com.damu.febs.server.test.bySelf.jvm.chapter04;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author     ：damu
 * @ Date       ：Created in 19:37 2021/7/26
 * @ Modified By：
 * @Version: 1.0.0
 */
public class JConsoleTest {
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];

        public static void fillHeap(int num) throws InterruptedException {
            List<OOMObject> list = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                //稍作延时，监视曲线变化更明显
                Thread.sleep(50);
                list.add(new OOMObject());
            }
            System.gc();
        }

        public static void main(String[] args) throws Exception{
            fillHeap(1000);
        }
    }


}
