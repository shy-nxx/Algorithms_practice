package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_2293_coin1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[101];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            coins[i] = Integer.parseInt(st.nextToken());
        }

        //dp[N][K] = N원으로 k원을 만드는 경우의 수
        int[] dp = new int[10001];

        dp[0] = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = coins[i]; j <= K; j++) {
                dp[j] = dp[j - coins[i]] + dp[j];
            }
        }
//        for (int i = 1; i <= N; i++) {
//            System.out.println(coins[i]);
//            for (int j = coins[i]; j <= K; j++) {
//                System.out.print(dp[j] + " ");
//
//            }
//            System.out.println();
//        }


        System.out.println(dp[K]);
    }
}