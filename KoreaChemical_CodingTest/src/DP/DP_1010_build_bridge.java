package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_1010_build_bridge {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int k = 0; k < T; k++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken()); //서쪽
            int M = Integer.parseInt(st.nextToken()); //동쪽

            /**
             * 총 N개의 다리를 지을 수 있다. -> 맨 윗 칸의 다리의 위치를 지정한 후 이외의 다리들의 경우의 수를 구해주면 된다.
             */

            int[][] dp = new int[31][31];

            if (N == 1) {
                System.out.println(M);
                continue;
            }
            if (N == M) {
                System.out.println(1);
                continue;
            }

            for (int i = 1; i <= M; i++) {
                dp[1][i] = i; //1번 다리는 모든 M 다리에 지을 수 있다.
            }

            for (int i = 2; i <= N; i++) {
                for (int j = i; j <= M; j++) {
                    dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
                }
            }

            System.out.println(dp[N][M]);

        }
    }
}