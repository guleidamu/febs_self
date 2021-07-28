package com.damu.febs.server.test.byMySelf.jvm;

import java.util.ArrayList;
import java.util.List;

public class Heap {

    public static void main(String[] args) {
        List list = new ArrayList();
        int i =0;
        while (true){
//            System.out.println("i");
            list.add(i++);
        }
//        String name = "yaoyuan";
//        String aiku = name + " hello";
//        System.out.println("aiku:" + aiku);
    }

//    public static void main(String[] args) {
//        stackOutOfMemoryError(1);
//    }

//    public static void stackOutOfMemoryError(int depth){
//        depth++;
//        stackOutOfMemoryError(depth);
//    }
}
