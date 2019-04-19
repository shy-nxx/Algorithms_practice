package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW14500_2 {
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

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                dfs(i, j, 0,0); //'ㅗ'를 제외한 모양은 dfs로 탐색 가능
                CalculationSpectialBlock(i, j); // 'ㅗ' 모양

            }
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
        int wings = 4; //상하좌우
        int min = Integer.MAX_VALUE;
        int sum = matrix[x][y];

        for (int i = 0; i < 4; i++) {
            int nX = x + dx[i];
            int nY = y + dy[i];

            if (wings <= 2) //'ㅗ' , 'ㅏ', 'ㅜ', 'ㅓ'이 아님
                return;

            if (nX < 0 || nX >= N || nY < 0 || nY >= M) { //날개가 매트릭스 밖에 위치하면 날개가 아님
                wings--;
                continue;
            }

            min = Math.min(min, matrix[nX][nY]);
            sum = sum + matrix[nX][nY];

        }
        if (wings == 4) { //날개가 네개일 경우 가장 작은 날개를 없애야지 'ㅗ' , 'ㅏ', 'ㅜ', 'ㅓ'모양이 나온
            sum = sum - min;

        }
        MAX = Math.max(MAX, sum);
    }
}
