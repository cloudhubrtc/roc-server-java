package com.cloudhub;

/**
 * Demo Only
 * 请使用自己的 AuthKey 和 SecretKey
 * AuthKey: WbykCN****8pwd3
 * SecretKey: 23423****dfsfs
 * ChannelName: 1234567****shao
 * UserId: 159****91766-10**26
 * ExpireTime: 1594704891
 * AuthToken: eyJ0b2tlbiI6ImZlNTdkNzUzMDI1ZDIwMzdlNGZjMDdhMmRkYTBhZmMxIiwidGltZXN0YW1wIjoxNTk0NzA0ODkxLCJ1c2VyYWNjb3VudCI6InJlc2VydmUiLCJyb2xlIjoicmVzZXJ2ZSJ9lADPXbcGTTuoSBvn
 */


public class App {
    public static void main(String[] args) {
        String authKey = "Wbyk*****Q8pwd3";
        String secretKey = "234****3sdfsfs";
        String channelName = "1234****9qiushao";
        String userId = "15947****766-101726";
        Integer timestamp = 1594704891;

        Client client = new Client(authKey, secretKey);
        String authToken = null;
        try {
            authToken = client.getToken(channelName, userId, timestamp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(authToken);
    }
}
