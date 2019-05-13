package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DFS_1012_organic_cabbage {
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    static int[][] map;
    static boolean[][] visited;

    static int M, N, K;

    static int[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        ans = new int[T];
        for (int k = 0; k < T; k++) {
            st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken()); //가로
            N = Integer.parseInt(st.nextToken()); //세로
            K = Integer.parseInt(st.nextToken()); //배추 위치 개수
            //y, x로 들어옴

            map = new int[N][M];
            visited = new boolean[N][M];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());

                map[x][y] = 1;
            }

            int result = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) {
                        dfs(i, j);
                        result++;
                    }
                }

            }
            ans[k] = result;

        }
        for (int i = 0; i < T; i++) {
            System.out.println(ans[i]);
        }

    }
    static void dfs(int x, int y) {

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;

            if (map[nx][ny] == 1) {
                visited[nx][ny] = true;
                dfs(nx, ny);
            }

        }
    }

}
