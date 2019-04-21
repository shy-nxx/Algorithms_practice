package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Build_bridge_DP {
    /**
     *
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken()); //테스트 케이스 개수

        for (int k = 0; k < T; k++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); //서쪽
            int M =  Integer.parseInt(st.nextToken()); //동쪽

            int[][] dp = new int[31][31];

            if (N == 1) {
                System.out.println(M);
                continue;
            }

            if (N == M) {
                System.out.println(1);
                continue;
            }

            for (int i = 1; i <= N; i++) {
                dp[1][i] = i; // 서쪽 다리가 하나일 경우 모든 경우의 수를 적립
            }

            for (int i = 2; i <= N; i++) {
                for (int j = i; j <= M; j++) {
                    dp[i][j] = dp[i][j-1] + dp[i-1][j-1];

                    System.out.println("(" + i + " " + j + ") " + dp[i][j-1] + " " + dp[i-1][j-1]);
                }
            }

            System.out.println(dp[N][M]);
        }
    }
}