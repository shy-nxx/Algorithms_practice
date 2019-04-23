package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_2589_treasure_map {
    /**
     * 보물섬 지도를 발견한 후크 선장은 보물을 찾아나섰다. 보물섬 지도는 아래 그림과 같이 직사각형 모양이며 여러 칸으로 나뉘어져 있다. 각 칸은 육지(L)나 바다(W)로 표시되어 있다. 이 지도에서 이동은 상하좌우로 이웃한 육지로만 가능하며, 한 칸 이동하는데 한 시간이 걸린다. 보물은 서로 간에 최단 거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳에 나뉘어 묻혀있다. 육지를 나타내는 두 곳 사이를 최단 거리로 이동하려면 같은 곳을 두 번 이상 지나가거나, 멀리 돌아가서는 안된다.
     * <p>
     * <p>
     * <p>
     * 예를 들어 위와 같이 지도가 주어졌다면 보물은 아래 표시된 두 곳에 묻혀 있게 되고, 이 둘 사이의 최단 거리로 이동하는 시간은 8시간이 된다.
     * <p>
     * <p>
     * <p>
     * 보물 지도가 주어질 때, 보물이 묻혀 있는 두 곳 간의 최단 거리로 이동하는 시간을 구하는 프로그램을 작성하시오.
     */

    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int[][] dp;

    static Queue<Pos> q = new LinkedList<>();

    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//
//            }
//        }

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(i);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L') {
                    bfs(i, j);
                }
            }
        }

        System.out.println(max);
    }

    static void bfs(int x, int y) {
        q.add(new Pos(x, y));

        visited = new boolean[N][M];
        dp = new int[N][M];

        visited[x][y] = true;

        dp[x][y] = 1;
        while (!q.isEmpty()) {
            Pos now_p = q.poll();

            int cx = now_p.x;
            int cy = now_p.y;

//            System.out.println(map[cx][cy] + " " + time);
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                if (map[nx][ny] == 'L' && !visited[nx][ny]) {
                    dp[nx][ny] = dp[cx][cy] + 1;

                    visited[nx][ny] = true;

                    q.add(new Pos(nx, ny));

                }

            }

        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
//                System.out.println(dp[i][j]);
                max = Math.max(max, dp[i][j]);
            }
        }

    }

    static class Pos {
        int x, y;

        public Pos (int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
}