package com.ifpay.common.utils;

import java.io.*;
import java.security.*;

import javax.crypto.*;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import sun.misc.*;

/**
 * <p>Title: RSA非对称型加密的公钥和私钥</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 *
 * Created by harvey.xu on 2016/3/8.
 */
public class RSAUtil {

    private KeyPairGenerator kpg = null;
    private KeyPair kp = null;
    private PublicKey public_key = null;
    private PrivateKey private_key = null;

    /**
     * 构造函数
     * @param in 指定密匙长度（取值范围：512～2048）
     * @throws NoSuchAlgorithmException 异常
     */
    public RSAUtil(int in, String address) throws NoSuchAlgorithmException,
            FileNotFoundException, IOException
    {
        kpg = KeyPairGenerator.getInstance("RSA"); //创建‘密匙对’生成器
        kpg.initialize(in); //指定密匙长度（取值范围：512～2048）
        kp = kpg.genKeyPair(); //生成‘密匙对’，其中包含着一个公匙和一个私匙的信息
        public_key = kp.getPublic(); //获得公匙
        private_key = kp.getPrivate(); //获得私匙

        sun.misc.BASE64Encoder  b64 =  new sun.misc.BASE64Encoder();
        String pkStr = b64.encode(public_key.getEncoded());
        String prStr = b64.encode(private_key.getEncoded());
        System.out.print("pkStr length:" +pkStr.length() +pkStr);

        FileWriter fw=new FileWriter(address + "/private_key.dat");
        fw.write(prStr);
        fw.close();

        FileWriter  fw2 = new FileWriter(address + "/public_key.dat");
        fw2.write(pkStr);
        fw2.close();
    }

    /**
     *加密的方法
     */
    private  static String encrypt(String source, String key) throws Exception{

        /** 将文件中的公钥对象读出 */
        System.out.println( "myBuilderStr :  length: " +  key.length() +"  "+key );
        BASE64Decoder b64d = new  BASE64Decoder();
        byte [] keyByte =  b64d.decodeBuffer(key);
        X509EncodedKeySpec x509ek = new X509EncodedKeySpec(keyByte);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509ek);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        byte[] sbt = source.getBytes();
        byte [] epByte = cipher.doFinal(sbt);
        BASE64Encoder encoder = new BASE64Encoder();
        String epStr =  encoder.encode(epByte);
        return epStr;

    }

    /**
     *解密的方法
     */
    public static String decrypt(String cryptograph) throws Exception{

        FileReader  fr = new FileReader("c://private_key.dat");
        BufferedReader br=new BufferedReader(fr);//建立BufferedReader对象，并实例化为

        String getPvKey = "";
        while(true){
            String aLine = br.readLine();
            if(aLine==null)break;
            getPvKey += aLine;
        }
        BASE64Decoder   b64d = new  BASE64Decoder();
        byte [] keyByte =  b64d.decodeBuffer(getPvKey);
        PKCS8EncodedKeySpec  s8ek  = new PKCS8EncodedKeySpec(keyByte);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey  privateKey = keyFactory.generatePrivate(s8ek);


        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b1 = decoder.decodeBuffer(cryptograph);
        /** 执行解密操作 */
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }

    public static void main(String[] args) {
        try {
//            new KeyRSA(1024, "D:/");      //私匙和公匙保存到D盘下的文件中.
            System.out.println("");
            String getEptStr =  encrypt("13818884924", "SDw4654313qewrdfsasdfert845SUN95ddr1ld");
            System.out.println("getEptStr:"+getEptStr);
            String drpStr = decrypt(getEptStr);
            System.out.println("drpStr:"+drpStr);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
