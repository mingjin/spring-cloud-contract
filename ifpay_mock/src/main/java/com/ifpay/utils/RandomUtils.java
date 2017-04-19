package com.ifpay.utils;

/**
 * Created by harvey.xu on 2017/3/25.
 */
public class RandomUtils {

    /**
     * 生成N位随机数
     * @param n
     * @return num
     */
    public static int random(Integer n) {
        int num = 0;

        for(int i = 0; i < n; i++){
            num += (int)(Math.random()*10);
        }
        return num;
    }

    /**
     * 生成时间戳随机数
     * @return num
     */
    public static String random() {
        String num;
        num = System.currentTimeMillis() + String.valueOf((long) (Math.random() * 1000000));
        return num;
    }

    public static void main(String[] args) {
//        System.out.println(random(Integer.parseInt("AUTO_SN_10".split("_")[2])));
        System.out.println(random(1));
    }
}
