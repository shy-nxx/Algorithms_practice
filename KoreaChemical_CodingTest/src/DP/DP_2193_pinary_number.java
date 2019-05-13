package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_2193_pinary_number {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        //내가 현재 1이면 다음 수는 0이어야 하고 내가 현재 0이면 다음은 0이 가능하다.

        int[][] dp = new int[N+1][2];
        dp[1][0] = 1;
        dp[1][1] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-1][1];
            dp[i][1] = dp[i-1][0];

        }

        System.out.println(dp[N][1]);
    }
}