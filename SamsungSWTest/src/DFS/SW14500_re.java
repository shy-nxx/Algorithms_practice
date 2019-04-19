package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW14500_re {
    static int N, M, MAX= 0;
    static int[][] matrix;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                dfs(i, j, 0, 0);
                calcuationWings(i, j);
            }
        }

        System.out.println(MAX);
    }

    static void dfs(int x, int y, int count, int sum) {
        if (count == 4) {
            MAX = Math.max(MAX, sum);
            return;
        }

        for (int i = 0; i < dx.length; i++) {
            int nX = x + dx[i];
            int nY = y + dy[i];

            if (nX < 0 || nX >= N || nY < 0 || nY >= M) continue;
            if (!visited[nX][nY]) {
                visited[nX][nY] = true;
                dfs(nX, nY, count + 1, sum + matrix[nX][nY]);
                visited[nX][nY] = false;
            }
        }
    }

    static void calcuationWings(int x, int y) {
        int wings = 4;
        int min = Integer.MAX_VALUE;

        int sum = matrix[x][y];

        for (int i = 0; i < 4; i++) {
            int nX = x + dx[i];
            int nY = y + dx[i];

            if (wings <=2) return;

            if (nX < 0 || nX >= N || nY < 0 || nY >= M) {
                wings--;
                continue;
            }

            min = Math.min(min, matrix[nX][nY]); //날개가 3개여야 하기 때문에 가장 작은 수를 구해줌
            sum = sum + matrix[nX][nY];
        }

        if (wings == 4) {
            sum = sum - min;

        }
        MAX = Math.max(MAX, sum);

    }
}
