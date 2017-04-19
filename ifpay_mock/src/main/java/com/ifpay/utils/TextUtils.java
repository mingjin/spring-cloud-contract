package com.ifpay.utils;

import org.apache.commons.lang3.ArrayUtils;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Map;

/**
 * Created by harvey.xu on 2017/3/19.
 */
public class TextUtils {

    public static String jm(String content, String path)
            throws UnsupportedEncodingException, CertificateException,
            FileNotFoundException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        byte[] msg = content.getBytes("GBK"); // 待加解密的消息

        // 用证书的公钥加密
        CertificateFactory cff = CertificateFactory.getInstance("X.509");
        FileInputStream fis1 = new FileInputStream(path + "tomcat.cer"); // 证书文件
        Certificate cf = cff.generateCertificate(fis1);
        PublicKey pk1 = cf.getPublicKey(); // 得到证书文件携带的公钥
        Cipher c1 = Cipher.getInstance("RSA/ECB/PKCS1Padding"); // 定义算法：RSA
        byte[] dataReturn = null;
        c1.init(Cipher.PUBLIC_KEY, pk1);
        // StringBuilder sb = new StringBuilder();
        for (int i = 0; i < msg.length; i += 100) {
            byte[] doFinal = c1.doFinal(ArrayUtils.subarray(msg, i, i + 100));

            // sb.append(new String(doFinal,"gbk"));
            dataReturn = ArrayUtils.addAll(dataReturn, doFinal);
        }

        BASE64Encoder encoder = new BASE64Encoder();

        String afjmText = encoder.encode(dataReturn);

        return afjmText;
    }

    public static String buildMysign(Map sArray, String key) {
        String prestr = com.ifpay.utils.ArrayUtils.createLinkString(sArray); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        prestr = prestr + key; // 把拼接后的字符串再与安全校验码直接连接起来
//        System.out.println("==" + prestr + "============");
        String mysign = Md5Encrypt.md5(prestr);
        // System.out.println("+++"+mysign+"============");
        return mysign;
    }
}
