package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW14510_dp {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] t = new int[N+10];
        int[] p = new int[N+10];
        int[] dp = new int[N+10];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;

        for (int i = 1; i <= N + 1; i++) {
            dp[i] = Math.max(dp[i], max);
            dp[t[i] + i] = Math.max(dp[t[i] + i], p[i] + dp[i]);
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }

}
