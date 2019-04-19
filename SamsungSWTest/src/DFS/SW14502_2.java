package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SW14502_2 {
    static int N, M, MAX = 0;
    static int[][] matrix;
    static int[][] copyed;
    static ArrayList<Position> virusList = new ArrayList<>();
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        copyed = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());

                if (matrix[i][j] == 2) {
                    virusList.add(new Position(i, j));
                }
            }
        }

        dfs(0,0);
        System.out.println(MAX);

    }

    public static void dfs(int start, int count ) {
        if (count == 3) {
            copyMap();

            //바이러스 퍼뜨리기
            for (Position p : virusList) {
                spreadVirus(p.x, p.y);
            }

            MAX = Math.max(MAX, getSafeAreas());
            return;
        }

        //벽세우기
        for (int i = start; i < N*M; i++) {
            int x = i/M;
            int y = i%M;


            if (matrix[x][y] == 0) {
                matrix[x][y] = 1;
                dfs(i+1, count+1);
                matrix[x][y] = 0;

            }
        }
    }

    static void copyMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                copyed[i][j] = matrix[i][j];
        }
    }

    static void spreadVirus(int x, int y) {
        for (int i = 0; i < dx.length; i++) {
            int nX = x + dx[i];
            int nY = y + dy[i];

            if (nX <0 || nX >= N || nY <0 || nY >=M) continue;

            if (copyed[nX][nY] == 0) {
                copyed[nX][nY] = 2;
                spreadVirus(nX, nY);
            }
        }
    }

    static int getSafeAreas() {
        int count = 0;
        for (int i= 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if (copyed[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }
    public static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}