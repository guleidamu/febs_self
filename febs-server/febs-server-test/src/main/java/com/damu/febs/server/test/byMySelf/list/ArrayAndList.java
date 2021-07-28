package com.damu.febs.server.test.byMySelf.list;

import java.util.Arrays;
import java.util.List;

public class ArrayAndList {

    public static void main(String[] args) {
        List<Integer> statusList = Arrays.asList(1, 2);
        System.out.println(statusList);
        System.out.println(statusList.contains(1));
        System.out.println(statusList.contains(3));

        //会报错
        statusList.add(3);
        System.out.println(statusList.contains(3));
    }
}
