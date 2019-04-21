package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Binomial_coefficient_DP {
    /**
     * 이항계수 -> 파스칼의 삼각형으로 게산해야지 런타임 에러를 피할 수 있다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[1001][1001];

        dp[0][0] =1;
        dp[1][0] =1;
        dp[1][1] =1;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == N) dp[i][j] = 1;
                else {
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-1] % 10007;
                }
                if (i == N && j == K) break;
            }
        }
        System.out.println(dp[N][K]);
    }
}
