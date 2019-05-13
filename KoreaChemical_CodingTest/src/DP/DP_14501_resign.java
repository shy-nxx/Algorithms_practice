package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_14501_resign {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] t = new int[N+1];
        int[] p = new int[N+1];

        int[] dp = new int[1001];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
            dp[i] = p[i];
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                if (i - j >= t[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + p[i]);
                    System.out.println(dp[i]);
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (i + t[i] <= N+1) {
               max = Math.max(max, dp[i]);
            }
        }
        System.out.println(max);

    }
}