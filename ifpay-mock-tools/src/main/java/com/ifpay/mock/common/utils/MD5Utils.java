package com.ifpay.mock.common.utils;

import com.alibaba.druid.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.binary.Base64;

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

            if (!StringUtils.isEmpty(map.get(key))) {
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

            if (!StringUtils.isEmpty(map.get(key).toString())) {
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
            if (!StringUtils.isEmpty(entry.getValue())) {
                sb.append("&").append(entry.getKey()).append("=").append(URLDecoder.decode(entry.getValue(),"utf-8"));
            }
        }
        String result = sb.length() > 0 ? sb.substring(1) : sb.toString();
        return result;
    }

    public static String md5Sign(Map<String, String> params, String key) throws Exception {
        String signString;
        String result;
        try {
            signString = getSortParams(params);
            System.out.println("sign string : " + signString + "&key=" + key);
            result = DigestUtils.md5Hex(signString + "&key=" + key);
        } catch (UnsupportedEncodingException e){
            System.out.println("cannot get sorted params");
            throw new Exception();
        } catch (Exception e){
            System.out.println("sign failed");
            throw new Exception();
        }
        return result;
    }


    public static void main(String[] args) throws Exception {

//        TreeMap<String, String> map = new TreeMap<String, String>();
//        map.put("version", "1.0");
//        map.put("terminalType", "pc");
//        map.put("merchantId", "paymock");
//        map.put("merchantUserId", "123456789");
//        map.put("status", "1");
//        map.put("signType", "md5");
//        System.out.println("test = " + MD5Utils.md5Sign(map, "038ioerqowerliuweryiwerqg&HDF"));

//        String http = "http://10.198.195.52:8080/card/cardservice/queryCardsByUser";
//        http = http.replaceAll("[a-zA-z]+://[^\\s]*[0-9]+","");
//        System.out.println(http);
        String str = "eyJ0eG5UeXBlIjoiMDEiLCJjaGFubmVsVHlwZSI6IjYwMDIiLCJtZXJJZCI6Ijk5NjYwMDAwODAwMDA2MCIsIm9yZGVyQm9keS" +
                "I6InByb2R1Y3ROYW1lIiwib3JkZXJUaW1lIjoiMjAxNzA0MTIxNTIwMjEiLCJjdXJyZW5jeVR5cGUiOiIxNTYiLCJzaWduQXR1cmUiO" +
                "iJFQjY3MkI2RkZFMzZCMkM0QTAxREVCQ0ZCM0Y1N0I4QSIsInR4blN1YlR5cGUiOiIwMTAxMzAiLCJ0eG5BbXQiOiIxIiwidGVybUlk" +
                "IjoiV0VCIiwicHJvZHVjdElkIjoiMjAxNzA0MTIxNTIwMDUzNzQiLCJzaWduTWV0aG9kIjoiMDIiLCJiYWNrRW5kVXJsIjoiaHR0cDo" +
                "vLzExNC4yMTUuMjQyLjk6MTgxNzAvUGF5UmVjMTE/aWR4PTEwMTAxIiwib3JkZXJEZXRhaWwiOiJwcm9kdWN0RGVzYyIsIm5lZWRCYW" +
                "5rVHlwZSI6IlkiLCJhdHRhY2giOiIiLCJwYXlBY2Nlc3NUeXBlIjoiMDIiLCJlbmNvZGluZyI6IlVURi04IiwidGVybUlwIjoiMTE2L" +
                "jIyOC41NC4xMTgiLCJvcmRlcklkIjoiOTExNzA0MTIwMDEyNjExMjQ4In0=";

        System.out.println(new String(Base64.decodeBase64(str)));
    }


}
