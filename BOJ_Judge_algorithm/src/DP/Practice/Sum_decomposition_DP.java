package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sum_decomposition_DP {
    /**
     * 합분해
     * 0~N까지의 숫자를 K번 사용해서 N을 만들수 있는 경우의 수
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[K+1][201];

        for (int i = 0; i <= N; i++) {
            dp[1][i] = 1;

        }

        for (int i = 1; i <= K; i++) {
            for (int j = 0; j <= N; j++) {
                for (int l = 0; l <= j; l++) {
                    dp[i][j] += dp[i-1][j-l];
                }
            }
        }

        System.out.println(dp[K][N]);

    }
}
