package DFS;

import BFS.Practice.Organic_cabbage_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Organic_cabbage_DFS {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken()); // testcase

        for (int k = 0; k < T; k++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); // width
            N = Integer.parseInt(st.nextToken()); // height
            int K = Integer.parseInt(st.nextToken()); // number of cabbage

            map = new int[N][M];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                map[x][y] = 1; //cabbage
            }

            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(map[i][j] == 1) {
                        dfs(i, j);
                        count++; // 한번 들어갈 때마다 지렁이 한 마리씩 추가
                    }
                }
            }
            System.out.println(count);
        }
    }

    public static void dfs(int x, int y) {
        if (x == N-1 && y == M-1) return;

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

            if (map[nx][ny] == 0) continue;

            else {
                map[nx][ny] = 0; //주변 배추들을 모두 0으로 만든다. (지나쳐갈 수 있도록)
                dfs(nx,ny);
            }
        }

    }
    static class Pos{
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
