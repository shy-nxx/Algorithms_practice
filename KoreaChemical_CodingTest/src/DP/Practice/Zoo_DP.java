package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Zoo_DP {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N+1][3];

        /**
         * dp[n][0] -> 현재 줄에 사자가 없을 경우 = 윗줄에 사자가 모두 없을 경우 + 오른쪽에만 있을 경우 + 왼쪽에만 있을 경우
         * dp[n][1] -> 현재 줄의 왼쪽에만 사자가 잇을 경우 = 윗줄에 사자가 모두 없을 경우 + 오른쪽에만 있을 경우
         * dp[n][2] -> 현재 줄의 오른쪽에만 사자가 잇을 경우 = 윗줄에 사자가 모두 없을 경우 + 왼족에만 있을 경우
         *
         */

        for (int i= 1; i <= N; i++) {
            Arrays.fill(dp[i], 1);
            // 사자가 없을 경우도 1가지 경우로 간주하기 때문에 1로 초기화한다.
        }

        for (int i = 2; i <= N; i++ ){
            dp[i][0] = dp[i-1][0] + dp[i-1][1] + dp[i-1][2] % 9901;
            dp[i][1] = dp[i-1][0] + dp[i-1][2] % 9901;
            dp[i][2] = dp[i-1][0] + dp[i-1][1] % 9901;

        }

        System.out.println(dp[N][0] + dp[N][1] + dp[N][2]);

    }
}
