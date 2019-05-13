package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_1937_greedy_panda {
    /**
     * 판다가 어느 경로로 이동해야 오래 살 수 있는가 -> 판다의 초기위치가 주어지지 않음 ( 많은 경우의수..)
     * 자기자신보다 큰 걸로 나아가야 하지만 가장 큰 것은 안된다. (다음 큰 게 없기 때문에)
     * dp[][]
     * 오름차순을 해서 순서대로 시작? -> 그러면 너무 많이 반복문이 돌 것 같은데..
     *
     * DFS + DP
     *
     */

    static int MAX = 0;
    static int[][] visited = new int[501][501];
    static int[][] forest= new int[501][501];
    static int[][] dp = new int[501][501];

    static int N;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i< N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)  {
                forest[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == 0) //1이상은 이미 체크한 곳 -> 더이상 탐색이 필요없다.
                    dfs(i, j);

            }

        }
        System.out.println(MAX);
    }
    static void dfs(int x, int y ) {

        int amount = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

            if (forest[nx][ny] > forest[x][y])  { //지금 있는 곳보다 작은 곳이면
                System.out.println(visited[nx][ny]);
                if (visited[nx][ny] == 0) { //아직 탐색하지 않은 곳이라면
                    dfs(nx,ny);
                }

                if (amount < visited[nx][ny]) amount = visited[nx][ny];
            }
        }
        visited[x][y] = amount+1; //생존했다!!-> 생존일수 + 1
        if (MAX < visited[x][y]) MAX = visited[x][y];

    }
}