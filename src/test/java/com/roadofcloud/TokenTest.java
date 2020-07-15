package com.roadofcloud;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TokenTest {

    @Test
    public void testGetToken() {
        String authKey = "testAuthKey";
        String secretKey = "testSecretKey";
        String channelName = "testChannelName";
        String userId = "testUserId";
        Integer timestamp = 1594704891;

        Client client = new Client(authKey, secretKey);
        String token = null;
        try {
            token = client.getToken(channelName, userId, timestamp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 删除掩码
        token = token.substring(0, token.length() - 16);
        assertEquals("eyJ0b2tlbiI6IjBiNDkzZTVjN2FjMjM2OThkOWNkYTJhZmMzNGUyMTQyIiwidGltZXN0YW1wIjoxNTk0NzA0ODkxfQ==", token);
    }
}
