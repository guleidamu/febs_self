package com.damu.febs.server.test.byMySelf.jvm.stack;

import java.net.URL;

public class OOMError {

//    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
//        Field field = sun.misc.Unsafe.class.getDeclaredFields()[0];
//        field.setAccessible(true);
//        sun.misc.Unsafe unsafe = (Unsafe) field.get(null);
//        while (true) {
//            unsafe.allocateMemory(64 * 1024 * 1024);
//        }
//    }

    public static void main(String[] args) {

        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }

    }
}
