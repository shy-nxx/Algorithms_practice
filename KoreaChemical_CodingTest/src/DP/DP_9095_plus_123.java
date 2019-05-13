package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_9095_plus_123 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        /**
         * 1,2,3을 더해 N을 만드는 경우의 수
         */

        for (int k = 0; k < T; k++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());

            int[] dp = new int[N+1];
            dp[0] = 1;

            for (int i = 1; i <= N; i++) {
                if (i - 1 >= 0) {
                    dp[i] += dp[i-1];
                }
                if (i - 2 >= 0) {
                    dp[i] += dp[i-2];
                }
                if (i - 3 >= 0) {
                    dp[i] += dp[i-3];
                }
            }
            System.out.println(dp[N]);
        }

    }
}
