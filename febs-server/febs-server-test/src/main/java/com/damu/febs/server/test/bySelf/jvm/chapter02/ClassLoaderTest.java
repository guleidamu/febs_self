package com.damu.febs.server.test.bySelf.jvm.chapter02;

import sun.misc.Launcher;

import java.net.URL;

/**
 * @ Author     ：damu
 * @ Date       ：Created in 20:23 2021/6/18
 * @ Modified By：
 * @Version: 1.0.0
 */
public class ClassLoaderTest {
//    public static void main(String[] args) {
//        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
//        System.out.println(systemClassLoader);
//    }


    public static void main(String[] args) {
        System.out.println(">>>>>>>>>>>>>>>>启动类加载器》》》》》》");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL element : urLs){
            System.out.println(element.toExternalForm());
        }

        //从上面的路劲中
    }
}
