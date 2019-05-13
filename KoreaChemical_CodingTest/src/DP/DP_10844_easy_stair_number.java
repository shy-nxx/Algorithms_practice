package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_10844_easy_stair_number {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        /**
         * 45656이란 수를 보자.
         *
         * 이 수는 인접한 모든 자리수의 차이가 1이 난다. 이런 수를 계단 수라고 한다.
         *
         * 1 2 3 4 5 6 7 8 9
         * 10 12
         * 21 23 -> N-1의 수에서 += 1을 일의 자리에 붙인다.
         * 32 34...
         *
         * dp[N][L] -> L이 0일 때(10밖에 안됨)  / 1~8일 때 / 9일때 (8밖에 안됨)
         */

        int[][] dp = new int[101][101];

        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }



        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i-1][1];
            for (int j = 1; j <= 9; j++) {
                if (j == 9) {
                    dp[i][j] = dp[i-1][j-1] % 1000000000;
                }
                else {
                    dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000;
                }
            }

        }

        long sum = 0;

        for (int i = 0; i <= 9; i++) {
            sum += dp[N][i];
        }
        System.out.println(sum);
    }
}