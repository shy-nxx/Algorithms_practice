package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_11052_buy_cards {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //구매하려는 카드의 개수

        int[] cost = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++ ) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        /**
         * 1개 팩만 구매하는 경우
         * 2개 팩만 구매하는 경우
         *
         */

        int max = 0;

        int[] dp = new int[10001];

        for (int i = 1; i <= N; i++) {
            int max_Price = 0;
           for (int j = 1; j <= i; j++) {
               max_Price = Math.max(dp[i-j]+cost[j], max_Price);
           }
           dp[i] = max_Price;
           max = Math.max(max, dp[i]);
        }

        System.out.println(max);

    }
}