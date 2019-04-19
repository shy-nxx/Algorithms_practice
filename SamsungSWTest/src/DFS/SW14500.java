package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW14500 {
    static int[][] matrix, visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int N, M, MAX = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        visited = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N*M; i++) {
            int x = i / M;
            int y = i % M;
            visited[x][y] = 1;
            dfs(x, y, 1, matrix[x][y]); //'ㅗ'를 제외한 모양은 dfs로 탐색 가능
            CalculationSpectialBlock(x,y); // 'ㅗ' 모양
            visited[x][y] = 0;
        }
        System.out.println(MAX);

    }
    public static void dfs(int x, int y, int count, int sum) {
        if (count == 4) {
            MAX = Math.max(MAX, sum);
            return;
        }

        for (int i = 0; i < dx.length; i++) {
            int nX = x + dx[i];
            int nY = y + dy[i];

            if (nX < 0 || nX >= N || nY < 0 || nY >= M)
                continue;

            if (visited[nX][nY] == 0) {
                visited[nX][nY] = 1;
                dfs(nX, nY, count + 1, sum + matrix[nX][nY]);
                visited[nX][nY] = 0;
            }
        }
    }
    //'ㅗ' , 'ㅏ', 'ㅜ', 'ㅓ' 블록 처리
    public static void CalculationSpectialBlock(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int total = matrix[x][y];

            boolean flag = true;

            for (int j = 0; j < 3; j++) {
                int nX = x + dx[(i + j) % 4];
                int nY = x + dy[(i + j) % 4];

                if (nX >= 0 && nX <N && nY >= 0 && nY < N && visited[nX][nY] == 0) {
                    total += matrix[nX][nY];
                }
                else {
                    flag = false;
                    break;
                }
            }
            if (flag) MAX = Math.max(MAX, total );
        }
    }
}
