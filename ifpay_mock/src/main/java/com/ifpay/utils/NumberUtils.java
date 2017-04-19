package com.ifpay.utils;

import java.text.DecimalFormat;

/**
 * Created by harvey.xu on 2017/3/14.
 */
public class NumberUtils {

    /**
     *  自动补全15位，不足位数左补0
     * @param number
     * @return
     */
    public static String numberFormat(double number) {
        DecimalFormat df = new DecimalFormat("000000000000000");
        String reAmount = df.format(doubleTransform(number));
        return reAmount;
    }

     /* double类型转String 类型的小数点后的00
     * @param num double参数
     * @return String 类型
     */
    public static long doubleTransform(double num) {
        String strNum = num + "";
        int a = strNum.indexOf(".");
        if (a > 0) {
            //获取小数点后面的数字
            String dianAfter = strNum.substring(a + 1);
            if ("0".equals(dianAfter)) {
                return Long.valueOf(strNum.substring(0, a));
            } else {
                return Long.valueOf(strNum.replace(".", ""));
            }
        } else {
            return Long.valueOf(strNum.replace(".", ""));
        }
    }

    public static boolean isOdd(int num){
        if((num&1) != 1){   //偶数
            return true;
        }
        return false;
    }

//    public static String number(String )

    public static void main(String[] args) {
        System.out.println(NumberUtils.isOdd(19));
//        System.out.println(NumberUtils.numberFormat(999.99));
    }
}
