package BFS.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MovingThroughWalls_BFS {
    static int N, M;
    static int ans = 0;

    static int[][] map = new int[1001][1001];
    static boolean[][][] visited = new boolean[1001][1001][2];

    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    static final int YES = 1;
    static final int NO = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - 48;
            }
        }

        if (bfs(0,0, NO)) {
            System.out.println(ans);
        }
        else
            System.out.println(-1);
    }

    static boolean bfs(int x, int y, int isBroken) {
        Queue<Point> q = new LinkedList<>();

        q.add(new Point(x, y, isBroken));

        visited[x][y][YES] = true;
        visited[x][y][NO] = true;

        int temp = 0; //최단 경로

        while (!q.isEmpty()) {
            ++temp;

            int n = q.size();

            for (int i = 0; i < n; i++) {
                Point p = q.poll();

                if (p.x == N - 1 && p.y == M - 1) {
                    ans = temp;
                    return true;
                }

                for (int j = 0; j < 4; j++) {
                    int nx = p.x + dx[j];
                    int ny = p.y + dy[j];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                    if (map[nx][ny] == 1) { //벽이라면
                        if (!visited[nx][ny][YES] && p.isBroken < YES) {
                            visited[nx][ny][YES] = true;
                            q.add(new Point(nx, ny, YES));
                        }
                    }
                    else {
                        if (!visited[nx][ny][p.isBroken]) {
                            visited[nx][ny][p.isBroken] = true;
                            q.add(new Point(nx, ny, p.isBroken));
                        }
                    }

                }
            }
        }
        return false;
    }
    static class Point {
        int x, y;
        int isBroken;

        public Point(int x, int y, int isBroken) {
            this.x = x;
            this.y = y;
            this.isBroken = isBroken;
        }
    }
}