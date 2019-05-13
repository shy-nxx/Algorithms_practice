package DFS.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Iceberg_DFS {
    /**
     * 먼저 component가 2개 이상이 아닐때만 반복하도록 한다.
     * component가 0(모든 빙하가 다 녹았는데 집합이 없을 경우)이면 그대로 0을 출력하고 종료한다.
     * <p>
     * 그 다음에는 주변의 0의 개수만큼 빙항의 높이를 줄여서 저장한다.
     * <p>
     * 이를 반복한다.
     * <p>
     * compnent는 dfs를 사용해서 visited되지 않고 빙하의 높이가 0보다 클 때 재귀호출을 이용해서 반복한다.
     */

    static int N, M;

    static int[][] iceberg, afterYearsIceberg;
    static boolean[][] visited;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        iceberg = new int[N][M];
        afterYearsIceberg = new int[N][M];

        visited = new boolean[301][301];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                iceberg[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int temp = 0;
        while ((temp = findComponent()) < 2) {
            if (temp == 0) {
                ans = temp;
                break;
            }

            ++ans;

            visited= new boolean[301][301];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (iceberg[i][j] > 0) {
                        afterYearsIceberg[i][j] = meltingForYears(i, j);
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    iceberg[i][j] -= afterYearsIceberg[i][j];
                }
            }
        }
        System.out.println(ans);
    }

    static int findComponent() {
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && iceberg[i][j] > 0) {
                    ++count;
                    dfs(i, j);
                }
            }
        }
        return count;

    }

    static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                continue;

            if (!visited[nx][ny] && iceberg[nx][ny] > 0) {
                dfs(nx, ny);
            }
        }
    }

    static int meltingForYears(int x, int y) {
        int countOfZero = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                continue;

            if (iceberg[nx][ny] <= 0 && iceberg[x][y] > 0) {
                countOfZero++;
            }
        }

        return countOfZero;
    }
}
