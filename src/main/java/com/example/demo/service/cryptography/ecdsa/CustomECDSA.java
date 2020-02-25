package com.example.demo.service.cryptography.ecdsa;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Base64;

import static javax.xml.bind.DatatypeConverter.printHexBinary;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-25 22:58
 * @Description:
 */
public class CustomECDSA {


    public static void main(String[] args) {
        //签名
        String text="abcd";

        try {
            //sever sign
            KeyPair server = getECPublicKey("server");
            Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");
            ecdsaSign.initSign(server.getPrivate());
            ecdsaSign.update(text.getBytes("UTF-8"));
            byte[] serverSignature = ecdsaSign.sign();
            String pub = Base64.getEncoder().encodeToString(server.getPublic().getEncoded());
            String serverSign = Base64.getEncoder().encodeToString(serverSignature);
            System.out.println(String.format(" server sign ==> %s",serverSign));
            System.out.println(String.format(" server public key ==> %s",pub));


            //client verify
            Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA");
            ecdsaVerify.initVerify(server.getPublic());
            ecdsaVerify.update(text.getBytes("UTF-8"));
            boolean verify = ecdsaVerify.verify(serverSignature);
            System.out.println(String.format(" client verify ===> %s",verify));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static KeyPair getECPublicKey(String name){
        KeyPairGenerator kpg = null;
        try {
            kpg = KeyPairGenerator.getInstance("EC");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        kpg.initialize(256);
        KeyPair kp = kpg.generateKeyPair();
        byte[] publicKey = kp.getPublic().getEncoded();
        byte[] privateKey = kp.getPrivate().getEncoded();
//        System.out.println(String.format("%s ==> generate public key %s ;%n private key %s%n",name,printHexBinary(publicKey),printHexBinary(privateKey)));
        return kp;
    }
}
