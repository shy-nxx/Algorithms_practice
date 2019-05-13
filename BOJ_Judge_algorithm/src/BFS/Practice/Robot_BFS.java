package BFS.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Robot_BFS {
    /**
     * 로봇은 한 방향으로 1, 2,3 만큼 움직일 수 있고, 1을 움직일 수 없으면 2, 3도 움직일 수 없다.
     * <p>
     * 또한 방향 전환에 있어 정반대편으로 도는 것은 2번 방향을 바꿔야 한다. 이 점을 유의해야한다.
     */

    static int M, N;
    static int[][] map;
    static boolean[][][] visited;

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    //동서남북
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[101][101];

        visited = new boolean[101][101][5]; //x,y,dir

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

        bfs(start, end);

    }

    static void bfs(Point start, Point end) {
        Queue<Point> q = new LinkedList<>();

        q.add(start);

        while(!q.isEmpty()) {
            Point p = q.poll();

            int x = p.x;
            int y = p.y;
            int dir = p.dir;
            int count= p.count;
            
            if (x == end.x && y == end.y && dir == end.dir) {
                System.out.println(count);
                return;
            }
            visited[x][y][dir] = true;

            for (int i = 1; i <= 3; i++) { //Go 1~3
                int nx = (dx[dir-1] * i) + x;
                int ny = (dy[dir-1] * i) + y;

                if (nx > 0 && nx <= M && ny > 0 && ny <= N) {
                    if(map[nx][ny] == 0) {
                        if (!visited[nx][ny][dir]) {
                            visited[nx][ny][dir] = true;
                            q.add(new Point(nx, ny, dir, count+1));
                        }
                    }
                    else {
                        break;
                    }
                }
            }

            //방향 전환하기 동/ 서/ 남/ 북
            for (int i = 1; i <=4; i++) {
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
                q.add(new Point(x, y, i, count+add));
            }
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