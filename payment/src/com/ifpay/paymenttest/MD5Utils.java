package com.ifpay.paymenttest;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.*;

/**
 * Created by harvey.xu on 2015/11/10.
 */
public class MD5Utils {


    /**
     * @param
     * @param inputCharset
     * @return
     */
    public static TreeMap<String, String> MD5(TreeMap<String, String> map, String inputCharset, String pkey) {


        StringBuffer data1 = new StringBuffer();
        Set<String> keySet = map.keySet();
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = iter.next();

            if (StringUtils.isNotEmpty(map.get(key))) {
                data1.append(key + "=");
                data1.append(map.get(key));
                data1.append("&");
            }

        }
        String data = data1.toString().substring(0, data1.length() - 1);
        System.out.println("加签前字符串：" + data);
        map.put("beforeSignData", data);
        map.put("sign", MD5(data + pkey, inputCharset).toLowerCase());
        return map;
    }


    public static String MD5(String data, String inputCharset) {
        char hexDigits[] = {'0', '1', '2', '3', '4',
                '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = data.getBytes(inputCharset == null || inputCharset.equals("") ? "utf-8" : inputCharset);
            //获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            //使用指定的字节更新摘要
            mdInst.update(btInput);
            //获得密文
            byte[] md = mdInst.digest();
            //把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }

            return new String(str).toLowerCase();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void MD5CardSys(TreeMap<String, Object> map, String inputCharset, String pkey) {

        StringBuffer data1 = new StringBuffer();
        Set<String> keySet = map.keySet();
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = iter.next();

            if (StringUtils.isNotEmpty(map.get(key).toString())) {
                data1.append(key + "=");
                data1.append(map.get(key));
                data1.append("&");
            }
        }
        if (!data1.toString().equals("")) {
            String data = data1.toString().substring(0, data1.length() - 1);
            System.out.println("加签前字符串：" + data);
            map.put("beforeSignData", data);
            map.put("sign", MD5(data + pkey, inputCharset).toLowerCase());
        }
    }

    private static String getSortParams(Map<String, String> params) throws UnsupportedEncodingException {
        SortedMap<String, String> sortedMap = new TreeMap<String, String>(params);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
            sb.append("&").append(entry.getKey()).append("=").append(URLDecoder.decode(entry.getValue(),"utf-8"));
        }
        String result = sb.length() > 0 ? sb.substring(1) : sb.toString();
        return result;
    }

    public static String md5Sign(TreeMap<String, String> params, String pkey) throws Exception {
        String signString;
        String result;
        try {
            signString = getSortParams(params);
            System.out.println("sign string : " + signString);
            result = DigestUtils.md5Hex(signString + pkey);
        } catch (UnsupportedEncodingException e){
            System.out.println("cannot get sorted params");
            throw new Exception();
        } catch (Exception e){
            System.out.println("sign failed");
            throw new Exception();
        }

        return result;
    }

    public static void main(String[] args) {

//        TreeMap<String, String> map = new TreeMap<String, String>();
//        map.put("version", "1.0");
//        map.put("terminalType", "pc");
//        map.put("merchantId", "paymock");
//        map.put("merchantUserId", "123456789");
//        map.put("status", "1");
//        map.put("signType", "md5");
//        System.out.println("test = " + new MD5Utils().MD5(map, "", "038ioerqowerliuweryiwerqg&HDF"));

        String http = "http://10.198.195.52:8080/card/cardservice/queryCardsByUser";


        http = http.replaceAll("[a-zA-z]+://[^\\s]*[0-9]+","");
        System.out.println(http);
    }


}
