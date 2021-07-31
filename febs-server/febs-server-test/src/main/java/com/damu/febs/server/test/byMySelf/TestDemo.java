package com.damu.febs.server.test.byMySelf;


import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TestDemo {

    transient Object[] elementData;

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    public static void main(String[] args) {


//
//        Iterator iterator = set.iterator();
//        while (iterator.hasNext()){
//            String next = (String) iterator.next();
//            System.out.println("next  " + next);
//        }
        TestDemo testDemo = new TestDemo();
        testDemo.MapUtilsTest();
    }

    private void testTransient() {
        User user = new User();

        user.setUserName("Alexia");
        user.setPassword("123456");

        System.out.println("read before Serializable1: ");
        System.out.println("username1: " + user.getUserName());
        System.err.println("password1: " + user.getPassword());


        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:/transientTest.txt"));
            objectOutputStream.writeObject(user);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:/transientTest.txt"));
            User userOut = (User) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println("\nread after Serializable: ");
            System.out.println("username2: " + userOut.getUserName());
            System.err.println("password2: " + userOut.getPassword());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void MapUtilsTest(){
        Map map = new HashMap();
        map.put("jiu","yang");
        map.put("jiu","yangShenGong");
        map.get("jiu");

        Map map1 = new Hashtable();
        map.put("jiu","yang");
        map.put("jiu","yangShenGong");
        map.get("jiu");

        Map map2 = new ConcurrentHashMap();
        map2.put("jiu","yang");
        map2.put("jiu","yangShenGong");
        map2.get("jiu");
    }


    private void CollectionUtilsTest(){
        int a = 666;
        List arrayList = new ArrayList();
        arrayList.add("aL1");

        arrayList.add("aL2");
        List list2 = new LinkedList();
        list2.add("yaoming1");
        int ak = 666;
//        System.out.println("输出");
//        List<Object> objects = new List<>();
//        list.add("element1");
//        list.add("element2");
//        list.remove(2);
//        StringUtils.isBlank("");
//        StringUtils.isEmpty("");
//
//        List lkList = new LinkedList();
//        lkList.add("linkList");
//        lkList.add("linkList2");
//
        Set<String> set = new HashSet();
        set.add("uyou");

        set.add("beautiful");
        set.add("uyou");
        set.add("uyou");
        set.add("uyou");

        Set<String> linkHashSet = new LinkedHashSet<>();
        linkHashSet.add("you");
        linkHashSet.add("谢晓梅");
        linkHashSet.add("ppp");

//        List list = new List() {
//        }
//        }

//        Queue q = new Qu

    }


}


