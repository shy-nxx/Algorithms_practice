package BFS.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Build_bridge_BFS {
    /**
     * 각 섬마다 따로 번호를 매겨줘야 한다.
     * 번호를 새긴 후 각 섬마다의 거리를 구해준다. (0이 아니고 번호가 같지 않으면 그 사이의 거리를 구하면 된다)
     * 0 -> 섬 근처가 아닌 바다의 영역, 즉, 다리가 세워질 수 있는 영역
     *
     * bfs + dp
     */

    static int N;
    static int[][] map;
    static boolean[][] visited;

    static int[][] dp;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    static ArrayList<Integer> list = new ArrayList<>();

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Queue<Point> q = new LinkedList<>();

        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j =0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][N];

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    nominatedIslandNum(i, j, ++count);
                }
            }
        }



        for (int i = 0; i < N; i++) {
            for (int j =0; j < N; j++) {
                if (map[i][j] != 0) { //바다가 아니고 방문하지 않은 노드일 경우
                    q.add(new Point(i, j));
                }
            }
        }

        bfs(q);

        for (int dist : list) {
            min = Math.min(min, dist);
        }

        System.out.println(min);
    }

    static void nominatedIslandNum(int x, int y, int idx) {
        Queue<Point> q = new LinkedList<>();

        q.add(new Point(x, y));

        map[x][y] = idx;

        while (!q.isEmpty()) {
            Point p = q.poll();

            int cx = p.x;
            int cy = p.y;

            visited[cx][cy] = true;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                if (!visited[nx][ny] && map[nx][ny] == 1) {
                    map[nx][ny] = idx;
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny));
                }
            }
        }

    }

    static void bfs(Queue<Point> q) {

        while (!q.isEmpty()) {
            Point p = q.poll();

            int cx = p.x;
            int cy = p.y;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                if (map[nx][ny] != 0 && map[nx][ny] != map[cx][cy]) {
                    list.add(dp[nx][ny] + dp[cx][cy]);
                }

                if (map[nx][ny] == 0) {
                    dp[nx][ny] = dp[cx][cy] + 1;
                    map[nx][ny] = map[cx][cy];
                    q.add(new Point(nx, ny));
                }
            }
        }
    }
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}