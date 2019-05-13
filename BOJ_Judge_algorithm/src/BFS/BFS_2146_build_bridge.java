package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_2146_build_bridge {
    /**
     * 여러 섬으로 이루어진 나라가 있다. 이 나라의 대통령은, 섬들을 잇는 다리를 만들겠다는 공약으로 인기몰이를 해 당선될 수 있었다. 하지만 막상 대통령에 취임하자, 다리를 놓는다는 것이 아깝다는 생각을 하게 되었다. 그래서 그는, 생색내는 식으로 한 섬과 다른 섬을 잇는 다리 하나만을 만들기로 하였고, 그 또한 다리를 가장 짧게 하여 돈을 아끼려 하였다.
     * <p>
     * 이 나라는 N×N크기의 이차원 평면상에 존재한다. 이 나라는 여러 섬으로 이루어져 있으며, 섬이란 동서남북으로 육지가 붙어있는 덩어리를 말한다. 다음은 세 개의 섬으로 이루어진 나라의 지도이다.
     * <p>
     * <p>
     * <p>
     * 위의 그림에서 색이 있는 부분이 육지이고, 색이 없는 부분이 바다이다. 이 바다에 가장 짧은 다리를 놓아 두 대륙을 연결하고자 한다. 가장 짧은 다리란, 다리가 격자에서 차지하는 칸의 수가 가장 작은 다리를 말한다. 다음 그림에서 두 대륙을 연결하는 다리를 볼 수 있다.
     * <p>
     * <p>
     * <p>
     * 물론 위의 방법 외에도 다리를 놓는 방법이 여러 가지 있으나, 위의 경우가 놓는 다리의 길이가 3으로 가장 짧다(물론 길이가 3인 다른 다리를 놓을 수 있는 방법도 몇 가지 있다).
     * <p>
     * 지도가 주어질 때, 가장 짧은 다리 하나를 놓아 두 대륙을 연결하는 방법을 찾으시오.
     */

    static int N;

    static int[][] map;
    static boolean[][] visited;
    static int[][] dp;

    static int min = Integer.MAX_VALUE;

    static ArrayList<Integer> list = new ArrayList<>();
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        visited = new boolean[101][101];
        dp = new int[101][101];

        Queue<Position> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); // 1 - 섬 / 0 - 바다
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                        extendRegion(i, j, ++count); //육지인 애들을 각자 번호로 노미네이션함
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) { //0 -> 섬 근처 영역이 아님 (섬과 접해 있어야 다리를 지을 수 있다.)
                    q.add(new Position(i, j));
                }
            }
        }

        bfs(q);

        for (int dist : list) {
            min = Math.min(dist, min);
        }

        System.out.println(min);



    }

    static void extendRegion(int x, int y, int idx) {
        Queue<Position> q = new LinkedList<>();

        q.add(new Position(x,y));

        map[x][y] = idx;

        int n = q.size();

        while(!q.isEmpty()){
            Position p = q.poll();

            int cx = p.x;
            int cy = p.y;

            visited[x][y] = true;

            for (int j = 0; j < 4; j++ ) {
                int nx = cx + dx[j];
                int ny = cy + dy[j];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;

                /**
                 * 다음칸이 1(육지) 현재 칸이 0 -> 다리 끝 (다리 결과 반영해야함)
                 * 다음 칸이 0 / 현재 칸이 1 -> 다리 시작 (더해줘야함.)
                 *
                 * 시작 칸은 1일때만 들어온다. -> 다음칸이 0이어야지 다리를 지을 수 있다.
                 */

                if (map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    q.add(new Position(nx, ny));
                    map[nx][ny] = idx;
                }
            }
        }
    }

    static void bfs(Queue<Position> q) {

        //각 섬마다 번호가 노미네이션 되어있을 때 그 영역들 사이의 거리를 구한다.
        while (!q.isEmpty()) {

            Position p = q.poll();

            int x = p.x;
            int y = p.y;

            for (int i = 0; i< 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if (map[nx][ny] != 0 && map[nx][ny] != map[x][y]) { //섬과 섬이 만남
                    list.add(dp[nx][ny] + dp[x][y]);
                }

                if (map[nx][ny] == 0) {
                    dp[nx][ny] = dp[x][y] + 1;
                    map[nx][ny] = map[x][y];
                    q.add(new Position(nx, ny));
                }

            }
        }
    }
    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}