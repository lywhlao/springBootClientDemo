//package com.example.demo.service.cryptography;
//
///**
// * @Author: laojiaqi
// * @Date: 2019-10-24 10:49
// * @Description:
// */
//
//import javax.crypto.KeyAgreement;
//import java.nio.ByteBuffer;
//import java.security.*;
//import java.security.spec.X509EncodedKeySpec;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Scanner;
//
//import static javax.xml.bind.DatatypeConverter.parseHexBinary;
//import static javax.xml.bind.DatatypeConverter.printHexBinary;
//
//public class ECDH {
//
//    public static void main(String[] args) throws Exception {
//
//        Scanner scanner = new Scanner(System.in);
//        // Generate ephemeral ECDH keypair
////        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
////        kpg.initialize(256);
////        KeyPair kp = kpg.generateKeyPair();
////        byte[] ourPk = kp.getPublic().getEncoded();
//
//        byte[] ourPk = getECPublicKey();
//
//        // Display our public key
//        System.out.println(String.format("Public Key: %s%n", printHexBinary(ourPk)));
//
//        // Read other's public key:
//        byte[] otherPk = parseHexBinary(scanner.nextLine());
//
//        KeyFactory kf = KeyFactory.getInstance("EC");
//        X509EncodedKeySpec pkSpec = new X509EncodedKeySpec(otherPk);
//        PublicKey otherPublicKey = kf.generatePublic(pkSpec);
//
//        // Perform key agreement
//        KeyAgreement ka = KeyAgreement.getInstance("ECDH");
//        ka.init(kp.getPrivate());
//        ka.doPhase(otherPublicKey, true);
//
//        // Read shared secret
//        byte[] sharedSecret = ka.generateSecret();
//        System.out.println(String.format("Shared secret: %s%n", printHexBinary(sharedSecret)));
//
//        // Derive a key from the shared secret and both public keys
//        MessageDigest hash = MessageDigest.getInstance("SHA-256");
//        hash.update(sharedSecret);
//        // Simple deterministic ordering
//        List<ByteBuffer> keys = Arrays.asList(ByteBuffer.wrap(ourPk), ByteBuffer.wrap(otherPk));
//        Collections.sort(keys);
//        hash.update(keys.get(0));
//        hash.update(keys.get(1));
//
//        byte[] derivedKey = hash.digest();
//        System.out.println(String.format("Final key: %s%n", printHexBinary(derivedKey)));
//    }
//
//
//    public static KeyPair getECPublicKey(){
//        KeyPairGenerator kpg = null;
//        try {
//            kpg = KeyPairGenerator.getInstance("EC");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        kpg.initialize(256);
//        KeyPair kp = kpg.generateKeyPair();
//        byte[] publicKey = kp.getPublic().getEncoded();
//        byte[] privateKey = kp.getPrivate().getEncoded();
//        System.out.println(String.format("genterate public key %s ; private key %s",printHexBinary(publicKey),printHexBinary(privateKey)));
//        return kp;
//    }
//}
