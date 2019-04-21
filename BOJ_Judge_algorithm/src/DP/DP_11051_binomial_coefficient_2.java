package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_11051_binomial_coefficient_2 {
    /**
     *
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[1001][1001];

        dp[0][0] = 1;
        dp[1][0] = 1;
        dp[1][1] = 1;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == N) dp[i][j] = 1; // 양끝단이므로 1을 저장한다.
                else {
                    dp[i][j] = (dp[i-1][j] + dp[i-1][j-1]) % 10007; //현재 지점의 왼쪽위와 오른쪽 위를 더하고,  mod연산.
                }
                if (i == N && j == K) break; //  5,2와 같이 j가 기존의 조건식의 끝에 다다르기전에 문제의 요구하는 답을 얻을수있다.
            }

        }

        System.out.println(dp[N][K]);
    }
}
