package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_11048_move {
    /**
     * 준규는 N×M 크기의 미로에 갇혀있다. 미로는 1×1크기의 방으로 나누어져 있고, 각 방에는 사탕이 놓여져 있다. 미로의 가장 왼쪽 윗 방은 (1, 1)이고, 가장 오른쪽 아랫 방은 (N, M)이다.
     * <p>
     * 준규는 현재 (1, 1)에 있고, (N, M)으로 이동하려고 한다. 준규가 (r, c)에 있으면, (r+1, c), (r, c+1), (r+1, c+1)로 이동할 수 있고, 각 방을 방문할 때마다 방에 놓여져있는 사탕을 모두 가져갈 수 있다. 또, 미로 밖으로 나갈 수는 없다.
     * <p>
     * 준규가 (N, M)으로 이동할 때, 가져올 수 있는 사탕 개수의 최댓값을 구하시오
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] mirro = new int[N+1][M+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                mirro[i][j] = Integer.parseInt(st.nextToken()); //각 미로에 있는 사탕의 개수
            }
        }

        /**
         * 비교할 3가지 요소란 Dp[i-1][j] , Dp[i][j-1], Dp[i-1][j-1] 이렇게 3가지를 비교 최대값만 구하면 된다.
         */

        int[][] dp = new int[1001][1001];

        dp[1][1] = mirro[1][1];


        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                int max = 0;
                max = Math.max(dp[i-1][j], Math.max(dp[i-1][j-1], dp[i][j-1]));
                dp[i][j] = mirro[i][j] + max;
//                System.out.println(dp[i][j]);
            }
        }
        System.out.println(dp[N][M]);

    }
}