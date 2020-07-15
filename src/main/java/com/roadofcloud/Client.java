package com.roadofcloud;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

public class Client {
    private final String authKey;
    private final String secretKey;

    public Client(String authKey, String secretKey) {
        this.authKey = authKey;
        this.secretKey = secretKey;
    }

    public String getToken(String channelName, String userId, Integer expireTime) throws Exception {
        String bodyStr = this.authKey + "authkey" + this.authKey + "channame" + channelName + "timestamp" + expireTime + "userid" + userId;

        String tokenStr = null;
        try {
            String bodyMd5 = Client.md5Encode(bodyStr);
            String secretMd5 = Client.md5Encode(this.secretKey);
            tokenStr = Client.md5Encode(bodyMd5 + secretMd5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new Exception("生成token失败");
        }
        String jsonStr = "{\"token\":\"" + tokenStr + "\",\"timestamp\":" + expireTime + "}";
        return Base64.getEncoder().encodeToString(jsonStr.getBytes()) + Client.getRandomStr(16);
    }

    public static String getRandomStr(Integer count) {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
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
