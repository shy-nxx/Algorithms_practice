package DFS.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Safe_area_DFS {
    /**
     * 기준보다 높은 높이를 가진 칸들의 연결 요소를 검사
     * -> 연결 요소 검사하는 방식과 동일하게 한다.
     * 기준보다 낮은 높이를 가진 칸은 visited를 true로 해서 방문하지 않도록 한다.
     */

    static int N;
    static int[][] matrix;
    static boolean[][] visited;
    static int max = 1;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //정점의 개수

        matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                max = Math.max(max, matrix[i][j] = Integer.parseInt(st.nextToken()));
            }
        }

        int result = 0;

        for (int k = 1; k <= max; k++) {
            visited = new boolean[N][N];
            int temp_result = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (matrix[i][j] <= k) visited[i][j] = true;
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && matrix[i][j] > k) {
                        temp_result++;
                        dfs(i, j);
                    }
                }
                result = Math.max(result, temp_result);
            }
        }
        System.out.println(result);
    }

    static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;

            dfs(nx,ny);
        }
    }

}
