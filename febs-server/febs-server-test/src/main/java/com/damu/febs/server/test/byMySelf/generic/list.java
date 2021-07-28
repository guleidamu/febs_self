package com.damu.febs.server.test.byMySelf.generic;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class list {

    public static void main(String[] args) {
//        List arrayList = new ArrayList();
//        arrayList.add(8);
//        arrayList.add("yy");
//        for (int i = 0; i < arrayList.size(); i++) {
//            String item = (String)arrayList.get(i);
//            System.out.println("item:" + item);
//        }

//        String str1 = new String("Hello");
//        String str2 = new String("Hello");
//        String str3 = "Hello";
//        System.out.println(str1 == str3);
//        System.out.println(str1.equals(str3));
        String get = httpRequest("http://192.168.90.246:8095/DellCalendar.ashx?Action=GetDellYearWeek&Date=2021-06-01", "GET", null);
        Map maps = (Map) JSON.parse(get);
        System.out.println(get);
    }

    //处理http请求  requestUrl为请求地址  requestMethod请求方式，值为"GET"或"POST"
    public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
        StringBuffer buffer = null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod(requestMethod);
            conn.connect();
            //往服务器端写内容 也就是发起http请求需要带的参数
            if (null != outputStr) {
                OutputStream os = conn.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }
            //读取服务器端返回的内容
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            buffer = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

}
