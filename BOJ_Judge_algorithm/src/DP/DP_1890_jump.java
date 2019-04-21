package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_1890_jump {
    /**
     * N×N 게임판에 수가 적혀져 있다. 이 게임의 목표는 가장 왼쪽 위 칸에서 가장 오른쪽 아래 칸으로 규칙에 맞게 점프를 해서 가는 것이다.
     * <p>
     * 각 칸에 적혀있는 수는 현재 칸에서 갈 수 있는 거리를 의미한다. 반드시 오른쪽이나 아래쪽으로만 이동해야 한다. 0은 더 이상 진행을 막는 종착점이며, 항상 현재 칸에 적혀있는 수만큼 오른쪽이나 아래로 가야 한다. 한 번 점프를 할 때, 방향을 바꾸면 안된다. 즉, 한 칸에서 오른쪽으로 점프를 하거나, 아래로 점프를 하는 두 경우만 존재한다.
     * <p>
     * 가장 왼쪽 위 칸에서 가장 오른쪽 아래 칸으로 규칙에 맞게 이동할 수 있는 경로의 개수를 구하는 프로그램을 작성하시오.
     *
     * 입력값
     * 4
     * 2 3 3 1
     * 1 2 1 3
     * 1 2 3 1
     * 3 1 1 0
     */

    static int[][] map, dp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        dp = new int[N][N];

        for (int i = 0; i< N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); // 점프할 수 있는 거리  / 오른쪽 / 아래쪽 / 0은 종착점

            }
        }

        /**
         * DFS , DP..?
         *
         * 사실상 모든 칸을 완벽히 돌 필요는 없다.
         *
         * 거를 수 있는 건 거르면 된다.
         *
         * 예를 들어, (1, 1)에서 오른쪽으로 3칸 이동하면 (1, 4) 가 된다.
         *
         * (1, 2), (1, 3) 은 사실상 어떠한 경우라도 필요 없는 칸이 된다. (오른쪽과 아래쪽만 거리값을 통해 이동하기 때문)
         *
         * 1. dp[i][j] == 0
         *
         * => 위에서 말한 거르는 경우
         *
         *
         *
         * 2. i == n - 1 && j == n - 1
         *
         * => 마지막 도착 지점에서는 더이상 이동할 필요가 없다.
         *
         */

        dp[0][0] =1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dp[i][j] == 0 || (i == N-1 && j == N-1)) continue;
                int jump = map[i][j];
                int nx = i + jump;
                int ny = j + jump;

                if (nx < N) {
                    dp[nx][j] += dp[i][j];
                }
                if (ny < N) {
                    dp[i][ny] += dp[i][j];
                }
            }
        }

        System.out.println(dp[N-1][N-1]);
    }

//    static int dfs(int x, int y) {
//        if (x == 0 && y == 0) return 1;
//
//        if (dp[x][y] == -1) {
//            dp[x][y] = 0;
//
//            int nx = x + map[x][y];
//            int ny = y + map[x][y];
//            if (nx >= 0 && nx < N && ny >= 0 && nx < N ){
//                dp[x][y] += dfs(nx, y);
//                dp[x][y] += dfs(x, ny);
//                System.out.println(dp[x][y]);
//            }
//        }
//        return dp[x][y];
//    }
}