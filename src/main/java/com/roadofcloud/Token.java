package com.roadofcloud;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Token {
    private final String token;
    private final Integer timestamp;

    public Token(String token, Integer timestamp) {
        this.token = token;
        this.timestamp = timestamp;
    }

    public String getBase64Str() {
        String jsonStr = "{\"token\":\"" + this.token + "\",\"timestamp\":" + this.timestamp + "}";
        return Base64.getEncoder().encodeToString(jsonStr.getBytes());
    }


    public static String md5Encode(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(str.getBytes(StandardCharsets.UTF_8));

        byte[] byteArray = messageDigest.digest();

        StringBuilder md5StrBuff = new StringBuilder();
        for (byte b : byteArray) {
            if (Integer.toHexString(0xFF & b).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & b));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & b));
        }
        return md5StrBuff.toString();
    }
}
