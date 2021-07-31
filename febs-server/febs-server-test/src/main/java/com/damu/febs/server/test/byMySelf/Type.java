package com.damu.febs.server.test.byMySelf;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Type {

    private Integer qj = new Integer(93);

    public static void main(String[] args) {

//        Type type = new Type();
//        type.add(5,6);
//        Integer i = new Integer(100);
//        Integer j = 100;
//        System.out.println(i == j); //false
//        Integer k = 100;
//        System.out.println(j == k);
//
//        Integer x1 = 100;
//        Integer x2 = 100;
//        System.out.print(x1 == x2); //true
//
//        Integer m1 = 128;
//        Integer m2 = 128;
//        System.out.print(m1 == m2); //false
//
//        Integer integer = Integer.valueOf(100);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        Date date = null;
//        StringUtils.isEmpty(date);
//        try {
//            date = simpleDateFormat.parse("2019-09-02");
//            System.out.println(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Date date = new Date();

        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int actualMaximum = instance.getActualMaximum(Calendar.DAY_OF_MONTH);
        instance.set(Calendar.DAY_OF_MONTH,actualMaximum);
        System.out.println(instance.getTime());
        String lastDayOfMonth = simpleDateFormat.format(instance.getTime());
        System.out.println(lastDayOfMonth);

    }

    private int add(int i, int j) {
        Integer meili = 9;
        int p = qj;
        System.out.println("p" + p);
        int q = meili;

        return 0;

    }


}
