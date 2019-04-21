package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Descending_map_DP {
    /**
     * dp[y][x] = y,x지점에서 존재하는 내리막수
     * <p>
     * DFS + DP (탐색을 하는데 이미 돌출한 경로의 수를 이용하여 시간을 단축한다.)
     */

    static int M, N;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] map, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1; //존재하지 않는 경로를 표시한다.
            }
        }

        System.out.println(DFS(M-1, N-1));
    }

    static int DFS(int y, int x) {
        if (y == 0 && x == 0) return 1;

        if (dp[y][x] == -1) {
            dp[y][x] = 0;

            for (int i = 0; i < dx.length; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny < 0 || ny >= M || nx < 0 || nx >= N) continue;

                if (map[y][x] < map[ny][nx]) {
                    dp[y][x] += DFS(ny, nx);
                }
            }
        }

        return dp[y][x];
    }


}
