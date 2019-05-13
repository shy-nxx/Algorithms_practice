package DFS;

import BFS.Practice.Escape_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_2573_iceberg {
    /**
     * 지구 온난화로 인하여 북극의 빙산이 녹고 있다. 빙산을 그림 1과 같이 2차원 배열에 표시한다고 하자.
     * 빙산의 각 부분별 높이 정보는 배열의 각 칸에 양의 정수로 저장된다. 빙산 이외의 바다에 해당되는 칸에는 0이 저장된다. 그림 1에서 빈칸은 모두 0으로 채워져 있다고 생각한다.
     * <p>
     * 빙산의 높이는 바닷물에 많이 접해있는 부분에서 더 빨리 줄어들기 때문에, 배열에서 빙산의 각 부분에 해당되는 칸에 있는 높이는 일년마다
     * 그 칸에 동서남북 네 방향으로 붙어있는 0이 저장된 칸의 개수만큼 줄어든다. 단, 각 칸에 저장된 높이는 0보다 더 줄어들지 않는다.
     * 바닷물은 호수처럼 빙산에 둘러싸여 있을 수도 있다. 따라서 그림 1의 빙산은 일년후에 그림 2와 같이 변형된다.
     * <p>
     * 그림 3은 그림 1의 빙산이 2년 후에 변한 모습을 보여준다. 2차원 배열에서 동서남북 방향으로 붙어있는 칸들은 서로 연결되어 있다고 말한다.
     * 따라서 그림 2의 빙산은 한 덩어리이지만, 그림 3의 빙산은 세 덩어리로 분리되어 있다.
     * <p>
     * 한 덩어리의 빙산이 주어질 때, 이 빙산이 두 덩어리 이상으로 분리되는 최초의 시간(년)을 구하는 프로그램을 작성하시오.
     * 그림 1의 빙산에 대해서는 2가 답이다. 만일 전부 다 녹을 때까지 두 덩어리 이상으로 분리되지 않으면 프로그램은 0을 출력한다.
     */

    static int N, M;

    static int[][] iceberg, melt_iceberg;
    static boolean[][] visited = new boolean[301][301];

    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        iceberg = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                iceberg[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        melt_iceberg = new int[N][M];

        int temp = 0;
        while ((temp = componentIceberg()) < 2) {
            if (temp == 0) {
                ans = 0;
                break;
            }

            ++ans;

            visited = new boolean[301][301];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (iceberg[i][j] > 0) {
                        melt_iceberg[i][j] = meltingIceBerg(i, j);
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    iceberg[i][j] -= melt_iceberg[i][j];
                }
            }
        }
        System.out.println(ans);
    }

//    static int meltingIceBerg(int x, int y) {
//        int n = ice_statusQ.size();
//
//        for (int i = 0; i < n; i++) {
//            Ice_status now = ice_statusQ.poll();
//
//            int x = now.x;
//            int y = now.y;
//            int ice_height = now.height;
//
//            for (int j = 0; j < 4; j++) {
//                int nx = x + dx[j];
//                int ny = y + dy[j];
//
//                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
//
//                if (copy_iceberg[nx][ny] == 0) {
//                    int height = --ice_height;
//                    if (height < 0) {
//                        height = 0;
//                    }
//                    iceberg[x][y] = height;
//                    ice_statusQ.add(new Ice_status(nx, ny, height));
//                }
//            }
//        }
//    }

    static int meltingIceBerg(int x, int y) {

        int years = 0;

        for (int j = 0; j < 4; j++) {
            int nx = x + dx[j];
            int ny = y + dy[j];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

            if (iceberg[nx][ny] <= 0 && iceberg[x][y] > 0) {
                ++years;
            }
        }
        return years;
    }

    static int componentIceberg() {
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
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

            if (!visited[nx][ny] && iceberg[nx][ny] > 0) {
                dfs(nx, ny);
            }
        }
    }
//    static boolean searchIceBergHeight() {
//        //두개 이상으로 갈라지면 true
//
//        int n = ice_statusQ.size();
//
//        int result = 0;
//
//        for (int i = 0; i < n; i++) {
//            Ice_status now = ice_statusQ.poll();
//
//            int x = now.x;
//            int y = now.y;
//            int height = now.height;
//
//            if (result > 1) {
//                return true;
//            }
//
//            if (!visited[x][y] && iceberg[x][y] > 0) {
//                result++;
//
//                visited[x][y] = true;
//
//                System.out.println(x + " " + y  + " " + height);
//                for (int j = 0; j < 4; j++) {
//                    int nx = x + dx[j];
//                    int ny = y + dy[j];
//
//                    if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;
//
//                    if (iceberg[nx][ny] > 0) {
//                        visited[nx][ny] = true;
//                        ice_statusQ.add(new Ice_status(nx, ny, iceberg[nx][ny]));
//                    }
//                }
//            }
//
//        }
//        return false;
//
//    }

}