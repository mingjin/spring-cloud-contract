package com.ifpay.common.utils;

/**
 * Created by harvey.xu on 2016/1/27.
 */
import java.util.Random;

public class RandomUtil {
    /**
     * java生成随机数字和字母组合
     * @paramlength随机值长度
     * @return String
     */
    public static String getCharAndNumr(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 生成随机数
     * @param length
     * @return
     */
    public static String getNumber(int length){
        String Num="";
        Random random = new Random();
        for(int i=0;i<length;i++)
        {
            Num += String.valueOf(random.nextInt(10));
        }
        return Num;
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
        // 2017041921001004200269337163
//         2017041921001008523890003488
        System.out.println(DateUtil.getDate("yyyyMMdd") + "2100100" + RandomUtil.getNumber(13));
//        System.out.println("https://qr.alipay.com/bax" + RandomUtil.getNumber(5) + RandomUtil.getCharAndNumr(16));

    }

}
