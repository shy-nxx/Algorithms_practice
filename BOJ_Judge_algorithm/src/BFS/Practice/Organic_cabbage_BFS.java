package BFS.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Organic_cabbage_BFS {
    /**
     * DFS, BFS 모두 가능
     * <p>
     * 1 - 배추가 심어진 곳  0 - 배추가 심어지지 않은 곳
     * 흰배추지렁이는 근접한 배추들을 모두 건너다니면서 해충을 잡아먹는다. 따라서 배추가 근접한 곳에는 지렁이 한 마리로 커버 가능
     * 총 지렁이가 얼마나 필요한지 구하시오
     */

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
                        bfs(i, j);
                        count++; // 한번 들어갈 때마다 지렁이 한 마리씩 추가
                    }
                }
            }
            System.out.println(count);
        }
    }

    public static void bfs(int x, int y) {
        Queue<Pos> q = new LinkedList<>();

        q.add(new Pos(x, y));

        while (!q.isEmpty()) {
            Pos p = q.poll();

            int cx = p.x;
            int cy = p.y;

            for (int i = 0; i < dx.length; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                if (map[nx][ny] == 0) continue;

                else {
                    map[nx][ny] = 0; //주변 배추들을 모두 0으로 만든다. (지나쳐갈 수 있도록)
                    q.add(new Pos(nx, ny));
                }
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