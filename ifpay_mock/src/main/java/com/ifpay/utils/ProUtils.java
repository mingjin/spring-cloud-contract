package com.ifpay.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by harvey.xu on 2017/3/19.
 */
public class ProUtils {

    public String getKey() {
        String key = "";
            String filePath="/apps/conf/javaconf/paymock.properties";
//        String filePath = "D:\\code\\javaconf\\paymock.properties";
        Properties properties = new Properties();
        try {
            FileInputStream in = new FileInputStream(new File(filePath));

            properties.load(in);
            key = properties.get("key").toString();
//            Iterator<String> it = properties.stringPropertyNames().iterator();
//            while (it.hasNext()) {
//                String key = it.next();
//                System.out.println(key + ":" + properties.getProperty(key));

//            }

            in.close();

        } catch (
                Exception e
                )

        {
            System.out.println(e.getMessage());
        }
        return key;
    }

    /**
     * 从配置文件读取参数
     * @return Map
     */
    public Map getProperties() {
        Properties prop = new Properties();

        Map propMap = new HashMap();

        String fileName = System.getProperty("spring.profiles.active");
        if(fileName == null){
            fileName="/apps/conf/javaconf/paymock.properties";
        }

            try{
                //读取属性文件
                InputStream in = new BufferedInputStream(new FileInputStream(fileName));
                //加载属性列表
                prop.load(in);
                Iterator<String> it = prop.stringPropertyNames().iterator();
                while(it.hasNext()) {
                    String key=it.next();
//                    System.out.println(key + ":" + prop.getProperty(key));
                    propMap.put(key , prop.getProperty(key));
                }
                in.close();
            } catch(Exception e){
                System.out.println(e);
            }

        return propMap;
    }

    /**
     * 获取配置文件中的key值
     * @return String
     */
    public String getKeyFromProp(String keyFlag) {
        String key = "";
        Map map = new ProUtils().getProperties();

        Iterator entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
//            System.out.println("key = " + entry.getKey() + " | " + "value = " + entry.getValue());

            if (keyFlag != null && keyFlag.equals("agentpay")) {
                if (entry.getKey() != null && entry.getKey().equals("key.internal")) {
                    key = entry.getValue().toString();
                }
            } else {
                if (entry.getKey() != null && entry.getKey().equals("key")) {
                    key = entry.getValue().toString();
                }
            }
        }
        return key;
    }

//    public static void main(String[] args) {
//        String key = "123456";
//        String para = "";
//        String requestUrl = "www.test.com";
//
//        if (StringUtils.isNotEmpty(para)) {
//            key = para;
//        } else {
//            if (StringUtils.isNotEmpty(requestUrl) && requestUrl.contains("vipme.com")) {
//                key = new  ProUtils().getKeyFromProp();
//            }
//        }
//
//        System.out.println("key = " + key);
//    }

}
