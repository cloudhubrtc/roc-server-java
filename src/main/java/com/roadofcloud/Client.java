package com.roadofcloud;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Client {
    private String authKey;
    private String secretKey;

    public Client(String authKey, String secretKey) {
        this.authKey = authKey;
        this.secretKey = secretKey;
    }

    public String getToken(String channelName, String userId, Integer expireTime) throws Exception {
        String bodyStr = this.authKey + "authkey" + this.authKey + "channame" + channelName + "timestamp" + expireTime + "userid" + userId;

        String tokenStr = null;
        try {
            String bodyMd5 = Token.md5Encode(bodyStr);
            String secretMd5 = Token.md5Encode(this.secretKey);
            tokenStr = Token.md5Encode(bodyMd5 + secretMd5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new Exception("生成token失败");
        }
        Token token = new Token(tokenStr, expireTime);
        return token.getBase64Str() + Client.getRandomStr(16);
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
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
}
