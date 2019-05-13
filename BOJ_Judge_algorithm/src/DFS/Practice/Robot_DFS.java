package DFS.Practice;

import BFS.Practice.Robot_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Robot_DFS {
    static int M, N;
    static int[][] map;
    static boolean[][][] visited;

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M+1][N+1];

        visited = new boolean[M+1][N+1][5]; //x,y,dir

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++)  {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),0);

        st = new StringTokenizer(br.readLine());
        Point end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),0);

        dfs(start, end);

    }

    static void dfs(Point start, Point end) {
        if (map[start.x][start.y] != 0) return;
        if (start.x == end.x && start.y == end.y && start.dir == end.dir) {
            System.out.println(start.count);
            return;
        }
        visited[start.x][start.y][start.dir] = true;

        int x = start.x;
        int y = start.y;
        int dir = start.dir;
        int count = start.count;

        for (int i = 1; i <= 3; i++) {
            int nx = dx[dir -1] * i + x;
            int ny = dy[dir -1] * i + y;

            if (nx > 0 && nx <= M && ny > 0 && ny <= N) {
                if (map[nx][ny] == 0) {
                    if (!visited[nx][ny][dir]) {
                        visited[nx][ny][dir] = true;
                        dfs(new Point(nx, ny, dir, count+1), end);
                    }
                }
                else {

                }
            }
        }

        for (int i = 1; i <= 4; i++) {
            int add = 1;

            if (!visited[x][y][i]) {
                if (dir == 1) {
                    if (i == 2) {
                        ++add;
                    }
                } else if (dir == 2) {
                    if (i == 1) {
                        ++add;
                    }
                } else if (dir == 3) {
                    if (i == 4 ) {
                        ++add;
                    }
                } else {
                    if (i == 3) ++add;
                }
            }

            visited[x][y][i] = true;
            dfs(new Point(x,y,i,count+add), end);
        }
    }
    static class Point {
        int x, y, dir, count;

        public Point(int x, int y, int dir, int count) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.count = count;
        }
    }

}