package com.pareeksha.blackcat.avenger.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PareekshaUtil {


    /**
     * It generates hash sort string out of string
     * @param examName @mandatory
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String generateHashFromString(String examName) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] hashBytes = digest.digest(examName.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hashBytes); // Convert byte array to hexadecimal string
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }

//    public static void main(String[] args) throws NoSuchAlgorithmException {
//        String str = "SSC CHSL 2023 fjkhgh gfghjkjhg ghjjhghjk ghjkjhg";
//        System.out.println(generateHashFromString(str));
//    }
}
