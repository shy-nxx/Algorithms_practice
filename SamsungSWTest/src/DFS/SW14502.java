package DFS;

import javafx.geometry.Pos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SW14502 {
    /**
     * n*m 개 중에서 3 개를 뽑는 Combination (조합)을 구한다고 생각하면 됩니다.
     *
     * 기존에는 1차원 배열에서 구했던 조합을 2차원에서 구하는 것이 조금 어색할 수 있습니다.
     *
     * 숫자를 0 ~ n*m 까지 증가시킬때 (i/m, i%m) 을 좌표로 하면 2차원 배열의 모든 인덱스를 탐색할 수 있습니다.
     *
     * 예를 들어 n = 3, m = 2 인 3*2 배열을 탐색한다고 할 때
     *
     * i = 0 이면 (0/2, 0%2) = (0, 0)
     * i = 1 이면 (1/2, 1%2) = (0, 1)
     * i = 2 이면 (2/2, 2%2) = (1, 0)
     * i = 3 이면 (3/2, 3%2) = (1, 1)
     * i = 4 이면 (4/2, 4%2) = (2, 0)
     * i = 5 이면 (5/2, 5%2) = (2, 1)
     *
     * 이렇게 모든 좌표를 방문합니다.
     */
    static int[][] matrix;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int N, M;
    static int max = 0;
    static int[][] copyed;
    static List<Position> virusList = new ArrayList<Position>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        visited = new boolean[N][M];

        copyed = new int[N][M];

        //0- 빈칸 / 1- 벽 / 2 - 바이러스
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if(matrix[i][j] == 2)
                    virusList.add(new Position(i, j)); //바이러스가 존재하는 자리를 미리 저장해두고 미리 값이 2인 좌표들을 따로 List에 저장하여 맵 전체를 스캔할 필요 없이 List만 순회하도록 합니다.
            }
        }

        dfs(0,0);
        System.out.println(max);

    }
    public static void dfs(int start, int depth) {
        if (depth == 3) {
            copyMap(); //맵 복사

            //바이러스 퍼트리기
            for (Position p : virusList) {
                spreadVirus(p.x, p.y);
            }

            max = Math.max(max, getSafeArea());
            return;
        }

        //벽세우기
        for (int i = start; i < N*M; i++) {
            int x = i/M;
            int y = i%M;

            if (matrix[x][y] == 0) {
                matrix[x][y] = 1;
                dfs(i+1, depth+1);
                matrix[x][y] = 0;
            }

        }
    }

    // 기존 맵을 유지하기 위해 바이러스 퍼트릴 맵 복사하기
    static void copyMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
//                System.out.print(matrix[i][j] + " ");
                copyed[i][j] = matrix[i][j];
            }
//            System.out.println();
        }
    }

    //dfs로 바이러스 퍼트리기
    static void spreadVirus(int x, int y) {
        for (int i = 0; i< dx.length; i++) {
            int nX = x + dx[i];
            int nY = y + dy[i];

            if (nX < 0 || nX >= N || nY < 0 || nY >= M) continue;

            if(copyed[nX][nY] == 0) {
                copyed[nX][nY] = 2;
                spreadVirus(nX, nY);
            }
        }
    }
    static int getSafeArea() {
        int safe = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(copyed[i][j] == 0)
                    safe++;
            }
        }
        return safe;
    }


    public static class Position {
        int x;
        int y;

        public Position (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}