package com.ifpay.utils;

import java.util.*;

/**
 * Created by admin on 2017/3/19.
 */
public class ArrayUtils {

    /**
     * 功能：除去数组中的空值和签名参数
     *
     * @param sArray
     *            签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    @SuppressWarnings("rawtypes")
    public static Map mapFilter(Map sArray) {
        List keys = new ArrayList(sArray.keySet());
        Map sArrayNew = new HashMap();
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            String value = (String) sArray.get(key);
			/*if(value.equals("") || value == null || key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type")){//新增notifyid不参加签名,只做标识用
				continue;
			}*/
            sArrayNew.put(key, value);
        }
        return sArrayNew;
    }

    /**
     * 功能：把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params
     *            需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map params) {
        List keys = new ArrayList(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            String value = (String) params.get(key);

            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    public static String getSplitStr(String inStr) {
        if (inStr.contains("-")) {
            String outStr = inStr.split("-")[1];
            return outStr;
        }
        return inStr;
    }

    public static void main(String[] args) {
        String str = "YLJZ-1234556";
        System.out.println(ArrayUtils.getSplitStr(str));
    }
}
