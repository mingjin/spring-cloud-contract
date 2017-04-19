package com.ifpay.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.TreeMap;

/**
 * Created by harvey.xu on 2017/3/13.
 */
public class PostJSON {


    public static TreeMap<String, String>  post(TreeMap<String, String> map,String url1) {

        try {
            //创建连接
            URL url = new URL(url1);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type",
                    "application/json");

            connection.connect();

            //POST请求
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());

            JSONObject obj = (JSONObject) JSONObject.toJSON(map);
//            obj.element("app_name", "asdf");
            System.out.println(obj.toString());
            map.put("beforeRequest",obj.toString());
            out.write((obj.toString()).getBytes("utf-8"));
            out.flush();
            out.close();

            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            System.out.println(sb);

            reader.close();
            // 断开连接
            connection.disconnect();
            map.put("response",sb.toString());
            return  map;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

//    public static TreeMap<String, Object> postObject(TreeMap<String, Object> map, String url1) {
//
//        try {
//            //创建连接
//            URL url = new URL(url1);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoOutput(true);
//            connection.setDoInput(true);
//            connection.setRequestMethod("POST");
//            connection.setUseCaches(false);
//            connection.setInstanceFollowRedirects(true);
//            connection.setRequestProperty("Content-Type",
//                    "application/json");
//
//            connection.connect();
//
//            //POST请求
//            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//
//            JSONObject obj = (JSONObject) JSONObject.toJSON(map);
//            System.out.println("request : " + obj.toString().replace("\\", "").replace("\"{\"", "{\"").replace("\"}\"", "\"}"));
////            map.put("beforeRequest", obj.toString().replace("\\", "").replace("\"{\"", "{\"").replace("\"}\"", "\"}"));
//            out.write((obj.toString()).getBytes("utf-8"));
//            out.flush();
//            out.close();
//
//            //读取响应
//            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String lines;
//            StringBuffer sb = new StringBuffer("");
//            while ((lines = reader.readLine()) != null) {
//                lines = new String(lines.getBytes(), "utf-8");
//                sb.append(lines);
//            }
//            System.out.println("response : " + sb);
//
//            map = parseJSON2Map(sb.toString());
//
//            reader.close();
//            // 断开连接
//            connection.disconnect();
//            map.put("beforeRequest", obj.toString().replace("\\", "").replace("\"{\"", "{\"").replace("\"}\"", "\"}"));
//            map.put("response", sb.toString());
//            return map;
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            return null;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            return null;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

//    public static TreeMap<String, Object> parseJSON2Map(String jsonStr){
//        TreeMap<String, Object> map = new TreeMap<String, Object>();
//        //最外层解析
//        JSONObject json = JSONObject.fromObject(jsonStr);
//        for(Object k : json.keySet()){
//            Object v = json.get(k);
//            //如果内层还是数组的话，继续解析
//            if(v instanceof JSONArray){
//                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//                Iterator<JSONObject> it = ((JSONArray)v).iterator();
//                while(it.hasNext()){
//                    JSONObject json2 = it.next();
//                    list.add(json2);
//                }
//                map.put(k.toString(), list);
//            } else {
//                map.put(k.toString(), v);
//            }
//        }
//        return map;
//    }

    public static void main(String[] args) {
//        String jsonString="{'fullname': 'Sean Kelly','org': 'SK Consulting','emailaddrs': [{'type': 'work', 'value': 'kelly@seankelly.biz'},{'type': 'home', 'pref': 1, 'value': 'kelly@seankelly.tv'}   ]}";
//        JSONObject jsonObject = new JSONObject().fromObject(jsonString);
//        Object obje=jsonObject.get("emailaddrs");
//        System.out.println(obje.toString());
//
//        TreeMap<String, Object> map = new TreeMap();
//        map.put("emailaddrs",obje.toString());
//        System.out.println(map.get("type"));

//        String json = "{\"resultCode\":\"00\",\"resultMsg\":\"\",\"payInfo\":\"{\\\"resultCode\\\":\\\"00\\\",\\\"resultMsg\\\":\\\"\\\",\\\"redirectUrl\\\":\\\"http://10.199.48.91:9001/paypalmock/PayPalWebReturn?token=EC-hJ3I10V15UYLe9o15\\\"}\",\"signType\":\"md5\",\"sign\":\"4e56f6596a927a856d4750ebfd8b478e\"}";

//        String json = "http://10.199.48.91:9001/paypalmock/PayPalWebReturn?token=EC-hJ3I10V15UYLe9o15";

//        System.out.println("http://" + json.split("http://")[1].split("\\\"}\"")[0].replace("\\", ""));
//        System.out.println(json.split("[?]")[0]);
    }
}
