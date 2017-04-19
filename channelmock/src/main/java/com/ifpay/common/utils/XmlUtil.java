package com.ifpay.common.utils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by harvey.xu on 2016/2/15.
 */
public class XmlUtil {
    /**
     * 将xml转换为map对象
     * @param request
     * @return map
     */
    public static Map<String, String> xml2Map(HttpServletRequest request) {

        Map<String, String> map = new HashMap<String, String>();

        String orderCode;
        String amount;
        String cardNumber;
        String cardHolderName;
        String inXMLData = null;
        ServletInputStream sis = null;

        try {
            // 取HTTP请求流
            sis = request.getInputStream();
            // 取HTTP请求流长度
            int size = request.getContentLength();
            // 用于缓存每次读取的数据
            byte[] buffer = new byte[size];
            // 用于存放结果的数组
            byte[] xmldataByte = new byte[size];
            int count = 0;
            int rbyte = 0;
            // 循环读取
            while (count < size) {
                // 每次实际读取长度存于rbyte中
                rbyte = sis.read(buffer);
                for (int i = 0; i < rbyte; i++) {
                    xmldataByte[count + i] = buffer[i];
                }
                count += rbyte;
            }
            inXMLData = new String(xmldataByte, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sis != null) {
                try {
                    sis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("innerXMLData = " + inXMLData);

        if (inXMLData != null) {
            orderCode = ((String) inXMLData.subSequence(inXMLData.indexOf("<order orderCode=\"") + 18, inXMLData.indexOf("<description>"))).replace("\">" , "");
            amount = ((String) inXMLData.subSequence(inXMLData.indexOf("<amount value=") + 15, inXMLData.indexOf("\" currencyCode")));
            cardNumber = ((String) inXMLData.subSequence(inXMLData.indexOf("<cardNumber>") + 12, inXMLData.indexOf("</cardNumber>")));
            cardHolderName = ((String) inXMLData.subSequence(inXMLData.indexOf("<cardHolderName>") + 16, inXMLData.indexOf("</cardHolderName>")));

            map.put("orderCode", orderCode);
            map.put("amount", amount);
            map.put("cardNumber", cardNumber.subSequence(0,4) + "********" + cardNumber.subSequence(cardNumber.length()-4 , cardNumber.length()));
            map.put("cardHolderName", cardHolderName);
        }

        return map;
    }
}
