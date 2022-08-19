package com.example.scengine.common.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;


/**
 * RSA加密解密工具类
 */
public class RsaUtil {

    public static Map<Integer, String> genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        Map<Integer, String> keyMap = new HashMap<>(4);
        //0表示公钥
        keyMap.put(0, publicKeyString);
        //1表示私钥
        keyMap.put(1, privateKeyString);
        return keyMap;
    }


    /**
     * RSA公钥加密
     *
     * @param str       待加密字符串
     * @param publicKey 公钥
     * @return 加密后字符串
     * @throws Exception Exception
     */
    public static String encrypt(String str, String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.encodeBase64String(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * RSA私钥解密
     *
     * @param str        待解密字符串
     * @param privateKey 私钥
     * @return 解密后字符串
     * @throws Exception Exception
     */
    public static String decrypt(String str, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }

//    public static void main(String[] args) {
//        String s="123qaz!@#";
//        String publicq="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1TYXk2Ov3nIRl7Z4zZaookgTWq4Cu5W4mMeVrddWJ11L1JyIyTQtKl6Y9YRRFwZ1A4Rt/rpmagxXOVmEW9jnJAD35k5SFA27H8fapgWi8qDmAtA6AkXYl7HptIZ0hBZAMt+w7CMJPYnwasEt3ijDFS6KAQcndV0brmF2vqS+8RQIDAQAB";
//        String privateq="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALVNheTY6/echGXtnjNlqiiSBNargK7lbiYx5Wt11YnXUvUnIjJNC0qXpj1hFEXBnUDhG3"
//                + "+umZqDFc5WYRb2OckAPfmTlIUDbsfx9qmBaLyoOYC0DoCRdiXsem0hnSEFkAy37DsIwk9ifBqwS3eKMMVLooBByd1XRuuYXa+pL7xFAgMBAAECgYBpOTkAfLVW3EwrLCGpCtQa/8Q3Iwpod1faaxMPry/j9Eh9Z9z8/KIVBLbC4Nj3M0ulIADptdgISRQa0R9ASF94QpERL5mSFtECUrlGEmHyHMt3ATvJqEs1R5kdn0Eo7iZZ9FMwaTgmCryWJ4oD5aZ/7L8nQtKdPvHDgCk7TC8ZAQJBAOD2GZuL/XOsk7hCSAncXYKhvZQeFkEHV5pHzzryMHUKloxcxO3mupUIuvemSe0PjX3V6vUmTp/0qFf+/7e3xpECQQDOUVpgy261x0H1FV/2MEup9P50soChwgmqN1Hl7A/8q9n6eWFmNVEZ/69GR66t8JYkp8EIurW3I7mq8teJKDx1AkBfzo/TXghgJkR3OctCX9Z3VvjNRw8FPOJDM4f/O7wFbK/3MV8xTo1yAVjXNbvQOUpyKBO7ukfL+b9kUpaHoL3xAkB2t+tIjWbMJLc4Xq9US2AOkRImdlx8N9o13xU6N0bPvtd0QIoiNQXQ7wadazwBjNHoj7sM2dscYetBifEqVEPNAkEAqnqz80CnC/+dWd2UCJds+pXXr8dWjljrxMhAju9H2WlIZWlHaxBN7p5L20HH2LqeQR3+PQknol+dBd9x0ZojVw==";
//        try {
//            //加密
//            String encrypt = RsaUtil.encrypt(s, publicq);
//            System.out.println(encrypt);
//            //解密
//            String decrypt = RsaUtil.decrypt(encrypt, privateq);
//            System.out.println(decrypt);
//            //加密
//            String encrypt11 = RsaUtil.encrypt("yan"+s+"yan", publicq);
//            System.out.println(encrypt11);
////解密
//            String decrypt11 = RsaUtil.decrypt("naGz12yVCakbjeOmC8xaw9y1yAQJ8z+zWQUao0PnKBbJ3Lvuyfg+dZAPzTWxWtXnRMGhdrc2/U9f5WsAE8tFuxryf/XqxHGzYUcN+AcOgBFOk5x9J7iDLsWXU8IocbxGGWrvvvunGp+yOvCGCPv2Y2L1Ah8uEWHWSvRvaY321qY=", privateq);
//            System.out.println(decrypt11);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

}
