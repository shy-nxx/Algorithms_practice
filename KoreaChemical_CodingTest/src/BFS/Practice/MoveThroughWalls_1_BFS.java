package BFS.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MoveThroughWalls_1_BFS {
    static int N, M;
    static int[][] map = new int[1001][1001];
    static int[][] dp = new int[1001][1001];
    static boolean[][][] visited = new boolean[1001][1001][2];

    static final int YES = 1;
    static final int NO = 0;

    static int MIN = 0;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - 48;
            }
        }

        if (bfs(0,0,NO))
            System.out.println(MIN);
        else {
            System.out.println(-1);
        }
    }
    static boolean bfs(int x, int y, int isBrokenWall) {
        visited[x][y][NO] = true;
        visited[x][y][YES] = true;

        Queue<Position> q = new LinkedList<>();

        q.add(new Position(x,y,isBrokenWall));

        int temp = 0;

        while (!q.isEmpty()) {
            ++temp;

            int n = q.size();

            for (int i = 0; i < n; i++) {
                Position p = q.poll();

                if (p.x == N-1 && p.y == M-1) {
                    MIN = temp;
                    return true;
                }

                for (int j = 0; j < 4; j++ ) {
                    int nx = p.x + dx[j];
                    int ny = p.y + dy[j];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                    if (map[nx][ny] == 1) {
                        if (!visited[nx][ny][YES] && p.isBroken < YES) {
                            visited[nx][ny][YES] = true;
                            q.add(new Position(nx, ny, YES));
                        }
                    }
                    else {
                        if (!visited[nx][ny][p.isBroken]) {
                            visited[nx][ny][p.isBroken] = true;
                            q.add(new Position(nx, ny, p.isBroken));
                        }
                    }
                }
            }
        }
        return false;

    }
    static class Position {
        int x, y;
        int isBroken;

        public Position(int x, int y, int isBroken) {
            this.x = x;
            this.y = y;
            this.isBroken = isBroken;
        }
    }
}