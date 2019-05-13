package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_2156_wine {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] wine = new int[N+1];
        int[] dp = new int[N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            wine[i] = Integer.parseInt(st.nextToken());
        }

        /**현재 잔 이전 잔을 마시지 않은 경우 -> 전전전 잔 +이전 잔 +현재잔
         * 현재 잔 이전 잔을 마셨을 경우 -> 전전 잔 + 현재잔
         * 현재 잔과 이전 잔을 마시지 않은 경우 => dp[n-1] + dp[n];
         *
         * **/

        for (int i =1; i < 3; i++) {
            if (i==1)  {
                dp[i] = wine[i];
            }
            else {
                dp[i] = dp[i-1] + wine[i];
            }
        }

        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i-1] + dp[i], Math.max(dp[i-3] + wine[i] + wine[i-1], dp[i-2] + wine[i]));
        }

        System.out.println(dp[N]);
    }
}