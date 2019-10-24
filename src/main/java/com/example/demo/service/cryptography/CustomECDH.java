package com.example.demo.service.cryptography;

import javax.crypto.KeyAgreement;
import java.security.*;

import static javax.xml.bind.DatatypeConverter.printHexBinary;

/**
 * @Author: laojiaqi
 * @Date: 2019-10-24 11:22
 * @Description:
 */
public class CustomECDH {


    public static void main(String[] args) {

        /// EC参数

        KeyPair client = getECPublicKey("client");

        KeyPair server = getECPublicKey("server");


        PublicKey clientPublic = client.getPublic();

        PublicKey serverPublic = server.getPublic();


        //拿别的人的pubKey进行生成
        byte[] clientSecret = agreementKey(client.getPrivate(), serverPublic,"client");

        byte[] serverSecret= agreementKey(server.getPrivate(),clientPublic,"server");



    }

    public static byte[] agreementKey(PrivateKey privateKey,PublicKey otherPublicKey,String name){
        KeyAgreement ka = null;
        try {
            ka = KeyAgreement.getInstance("ECDH");
            ka.init(privateKey);
            ka.doPhase(otherPublicKey, true);
        } catch (NoSuchAlgorithmException e) {
        } catch (InvalidKeyException e) {
        }
        // Read shared secret
        byte[] sharedSecret = ka.generateSecret();
        System.out.println(String.format("%s ==> Shared secret: %s%n", name,printHexBinary(sharedSecret)));

        return sharedSecret;
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
        System.out.println(String.format("%s ==> generate public key %s ;%n private key %s%n",name,printHexBinary(publicKey),printHexBinary(privateKey)));
        return kp;
    }
}
