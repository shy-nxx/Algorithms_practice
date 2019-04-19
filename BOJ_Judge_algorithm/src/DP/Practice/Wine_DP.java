package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Wine_DP {
    /**
     * 풀이
     * 계단오르기와 비슷한 방식으로 해결하면 되지만 계단과는 다르게 마지막 잔을 꼭 마시지 않아도 되고
     * 연속으로 마시지 않을 경우를 고려하여 계속 이전 값과 비교하여 갱신을 해야한다.
     * <p>
     * 현재의 잔을 마시지 않을 경우 -> 이전 잔과 전전 잔을 마실 수 있다.
     * 현재 잔을 마시고 이전 잔을 마셨을 경우 -> 전전 잔을 마실 수 없다.
     * 현재 잔을 마시고 이전 잔을 마시지 않았을 경우 -> 전전 잔과 현재 잔을 마실 수 있다.
     * <p>
     * dp[i-1] = 현재 잔을 마시지 않을 경우
     * dp[i-2] + wine[i] = 현재 잔을 마시고 이전 잔을 마시지 않을 경우
     * DP[I-3] + wine[i-1] + wine[i] = 현재 잔을 마시고 이전 잔도 마실 경우
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //와인의 개수

        int[] wines = new int[10010];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            wines[i] = Integer.parseInt(st.nextToken());
        }


        int[] dp = new int[10010];

        dp[0] = 0;

        for (int i = 1; i < 3; i++) {
            if (i ==1) {
                dp[i] = wines[i];
            }

            else {
                dp[i] = dp[i-1] + wines[i];
            }
        }

        for (int i = 3; i <= N; i++) {
            int result = 0;
            result = Math.max(dp[i-1], dp[i-2] + wines[i]);
            result = Math.max(result, dp[i-3] +wines[i-1] + wines[i]);

            dp[i] = result;
        }

        System.out.println(dp[N]);
    }
}