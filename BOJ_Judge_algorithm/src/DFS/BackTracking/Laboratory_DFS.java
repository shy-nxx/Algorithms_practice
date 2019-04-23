package DFS.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Laboratory_DFS {
    /**
     * 바이러스가 퍼지기 전에 벽을 세워서 막아야 한다.
     * 0- 빈캄 / 1 - 벽 / 2 - 바이러스
     * 벽을 세울 수 있는 개수는 최대 3개 이므로 이차원 배열을 돌면서 벽을 세운 후 바이러스를 퍼트려야 한다.
     * 바이러스를 퍼트리려면 값을 변경해야 하므로 맵을 카피 한 후 바이러스를 퍼트리고 safe area의 개수를 센다.
     * 이를 반복해서 안전 구역의 최대개수를 구한다.
     */

    static int N, M;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {-1,1,0,0};

    static int[][] map, copyed_map;
    static ArrayList<Pos> virusList;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        copyed_map = new int[N][M];

        virusList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    virusList.add(new Pos(i,j));
                }
            }
        }

        dfs(0,0);

        System.out.println(max);

    }

    static void dfs(int start, int count) {
        if (count == 3) {
            //벽이 세개 세워지면
            copy_map();

            for (Pos p : virusList) {
                spread_virus(p.x, p.y);
            }
            max = Math.max(max, find_safe_area());

            return;
        }

        for (int i = start; i < N*M; i++) { //맵을 일차원 배열 식으로 늘여놓는다.
            int x = i / M;
            int y = i % M;

            if (map[x][y] == 0) {
                map[x][y] = 1;
                dfs(i+1, count+1);
                map[x][y] = 0; //backtracking

            }
        }
    }

    static void copy_map() {
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                copyed_map[i][j] = map[i][j];
            }
        }
    }

    static void spread_virus(int x, int y) {
        for (int i = 0; i < dx.length; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

            if (copyed_map[nx][ny] == 0) {
                copyed_map[nx][ny] = 2;
                spread_virus(nx, ny);
            }
        }
    }

    static int find_safe_area() {
        int safe_area = 0;

        for (int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if (copyed_map[i][j] == 0) {
                    safe_area++;
                }
            }
        }
        return safe_area;

    }

    static class Pos{
        int x, y;

        public Pos (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}