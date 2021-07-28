package com.damu.febs.server.test.byMySelf.lambda;

public class J8Lambda {


    public static void main(String[] args) {

         int num = 1;
//        Converter<Integer, Integer> g = a -> System.out.println(num + a);
//        g.convert(9);

//        Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));
//        s.convert(2);
        Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));
        s.convert(2);
//        num = 5;
    }

    interface Converter<T1, T2> {
        void convert(int i);
    }
}
