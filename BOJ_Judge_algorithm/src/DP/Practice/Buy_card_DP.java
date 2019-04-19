package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Buy_card_DP {
    /**
     * N개의 카드를 구매 ->
     * 4
     * 1 5 6 7
     * 2개의 카드를 구매한다고 할 때 (1개의 카드를 2개 구매 -> 1 + 1/ 2개의 카드를 1개 구매 -> 5)
     * -> max값은 5 -> 배열에 5를 저장한다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //구매하려는 카드의 개수

        int[] pay = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            pay[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N+1];

        int max = 0;

        for (int i = 1; i <= N; i++) {
            int temp_max = 0;

            for (int j = 1; j<= i; j++) {
                temp_max = Math.max(temp_max, pay[j] + dp[i-j]);
            }

            dp[i] = temp_max;

            if (max < dp[i]) max = dp[i];

        }
        System.out.println(max);

    }
}