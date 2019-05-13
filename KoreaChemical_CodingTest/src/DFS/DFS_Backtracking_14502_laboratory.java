package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DFS_Backtracking_14502_laboratory {
    static int N, M;

    static int[][] map, copy_map;
    static boolean[][] visited;

    static int MAX = 0;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    static ArrayList<Position> virusList= new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //0은 빈 칸, 1은 벽, 2는 바이러스가 있는 위치이다.
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        copy_map = new int[N][M];
        visited = new boolean[N][M];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virusList.add(new Position(i, j));
                }
            }
        }

//        for (int i = 0; i < N; i++) {
//            copyMap();
//            for (int j = 0; j < M; j++) {
//                if (map[i][j] == 0 && !visited[i][j]) {
//                    visited[i][j] = true;
//                    dfs(i, j, 0);
//                    visited[i][j] = false;
//                }
//            }
//        }

        dfs(0,0);


        System.out.println(MAX);
    }

    static void dfs(int start, int count) {
        if (count == 3) {

            copyMap();

            for (Position p : virusList) {
                extendVirus(p.x, p.y);
            }

            int n = findSafeAreas();

            MAX = Math.max(MAX, n);

            return;

        }

        for (int j = start; j < M*N; j++) {
            int x = j / M;
            int y = j % M;

            if (map[x][y] == 0) {
                map[x][y] = 1;
                dfs(j+1, count+1);
                map[x][y] = 0;
            }

        }


    }

    static void copyMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy_map[i][j] = map[i][j];
            }
        }
    }

    static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(copy_map[i][j] + " ");
            }
            System.out.println();
        }
    }
    static void extendVirus(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M ) continue;

            if (copy_map[nx][ny] == 0) {
                copy_map[nx][ny] = 2;
                extendVirus(nx, ny);
            }
        }

    }

    static int findSafeAreas() {
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copy_map[i][j] == 0) {
                    count++;
                }
            }
        }

        return count;

    }
    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
