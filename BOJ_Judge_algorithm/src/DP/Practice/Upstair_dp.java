package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Upstair_dp {
    /**
     * 풀이
     * 마지막 계단을 반드시 밟아야 하고 연속 세 계단을 밟으면 안된다.
     * -> 현재 계단 전 계단을 밟았는가 or 밟지 않았는가
     * 밟았을 경우 현재 계단 전전(i-2) 계단을 밟으면 안되고(전전전 계단 + 현재 전 계단 + 현재 계단), 밟지 않았을 경우 현재 계단 전전 계단을 밟아야 한다.(현재 전전 계단, 현재 계단)
     * f[n] = dp[n-3] + upstair[n-1] + upstair[n]
     * f[n] = dp[n-2] + upstair[n]
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int upstair[] = new int[N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            upstair[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[301];
        dp[0] = 0;
        for (int i = 1; i <= 3; i++) {
            if (i != 3) { //세번째까지 도달하지 않았을 경우 1번째 계단  + 2번째 계단
                dp[i] = dp[i-1] + upstair[i];
            }
            else {
                dp[i] = Math.max(dp[i-2] + upstair[i], upstair[i] + upstair[i-1] );
            }

        }

        for (int i = 4; i <= N; i++) {
            dp[i] = Math.max(dp[i-3] + upstair[i-1] + upstair[i], dp[i-2] + upstair[i]);
        }

        System.out.println(dp[N]);

    }

}