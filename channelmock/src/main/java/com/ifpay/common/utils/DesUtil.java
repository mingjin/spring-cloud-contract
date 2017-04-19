package com.ifpay.common.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * Created by harvey.xu on 2016/3/11.
 */
public class DesUtil {

    public static class DesTest {
        // 与收银台约定密钥
//	private static String cashierKey = "QBCOf22Bf9tcGl2csvMeLA5b3fP5AWan5MpXt6CdrzOIAOwuXCPOpsgHF4f0wdW6";
        private static String key = "SUNz1CDr5SigXxCM6MDvTGecJKgrYJJioNuQbhz0vLjQ7Dfcmyq57ud5OWEyY9kL";
        private static char[] HEXCHAR = {'0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        public static void main(String[] args) {


            DesTest desTest = new DesTest();

            // 加密处理
            String oriStr1 = "13817774924";
            String encStr = desTest.encrypt(oriStr1, key);
            System.out.println("加密后的数据:[" + encStr + "]");

            // 解密处理
            String oriStr2 = "NNmdrwcsA0R/a0ZIeJcY7Q==";

            String decStr = desTest.decrypt(oriStr2, key);
            System.out.println("解密后的数据:[" + decStr + "]");
        }

        /**
         * @param sdata 要加密的数据
         * @return 加密后的数据
         */
        public String encrypt(String sdata, String desKey) {
            try {
                byte[] key = new BASE64Decoder().decodeBuffer(desKey);
                byte[] data = sdata.getBytes("UTF-8");
                Key deskey = null;
                DESedeKeySpec spec = new DESedeKeySpec(key);
                SecretKeyFactory keyfactory = SecretKeyFactory
                        .getInstance("desede");
                deskey = keyfactory.generateSecret(spec);
                Cipher cipher = Cipher.getInstance("desede/ECB/PKCS5Padding");
                cipher.init(1, deskey);
                byte[] bOut = cipher.doFinal(data);
                return new BASE64Encoder().encode(bOut);
            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            } catch (NoSuchPaddingException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            return "";
        }

        public String decrypt(String sdata, String desKey) {
            try {
                byte[] key = new BASE64Decoder().decodeBuffer(desKey);
                byte[] data = new BASE64Decoder().decodeBuffer(sdata);
                Key deskey;
                DESedeKeySpec spec = new DESedeKeySpec(key);
                SecretKeyFactory keyfactory = SecretKeyFactory
                        .getInstance("desede");
                deskey = keyfactory.generateSecret(spec);
                Cipher cipher = Cipher.getInstance("desede/ECB/PKCS5Padding");
                cipher.init(2, deskey);
                byte[] bOut = cipher.doFinal(data);
                String sdata_encode = new String(bOut, "UTF-8");
                return sdata_encode;
            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            } catch (NoSuchPaddingException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            return "";
        }

        private static String toHexString(byte[] b) {
            StringBuilder sb = new StringBuilder(b.length * 2);
            for (int i = 0; i < b.length; i++) {
                sb.append(HEXCHAR[((b[i] & 0xF0) >>> 4)]);
                sb.append(HEXCHAR[(b[i] & 0xF)]);
            }
            return sb.toString();
        }

        private static byte[] toBytes(String s) {
            byte[] bytes = new byte[s.length() / 2];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = ((byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2),
                        16));
            }
            return bytes;
        }

    }
}
