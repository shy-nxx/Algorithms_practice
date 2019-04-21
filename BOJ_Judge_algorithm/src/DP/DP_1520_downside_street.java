package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_1520_downside_street {
    /**
     * DFS + DP
     *
     * 한 칸은 한 지점을 나타내는데 각 칸에는 그 지점의 높이가 쓰여 있으며, 각 지점 사이의 이동은 지도에서 상하좌우 이웃한 곳끼리만 가능하다.
     * 현재 제일 왼쪽 위 칸이 나타내는 지점에 있는 세준이는 제일 오른쪽 아래 칸이 나타내는 지점으로 가려고 한다.
     * 그런데 가능한 힘을 적게 들이고 싶어 항상 높이가 더 낮은 지점으로만 이동하여 목표 지점까지 가고자 한다. 위와 같은 지도에서는 다음과 같은 세 가지 경로가 가능하다.
     * 지도가 주어질 때 이와 같이 제일 왼쪽 위 지점에서 출발하여 제일 오른쪽 아래 지점까지 항상 내리막길로만 이동하는 경로의 개수를 구하는 프로그램을 작성하시오.
     */
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int[][]dp, map;

    static int M, N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;//방문여부를 파악
            }
        }



        /**
         * 일단 자신보다 낮은 곳으로 이동한다. 만약 다음 칸이 자신보다 낮은 곳이 아니면
         *
         * botton-up?
         * top-down?
         *
         * 1. 이 문제는 내리막 길만 찾기 때문에 경로가 한쪽 경로이다. 즉 싸이클이 이루어 질 수 없다.
         * 2. 완전 탐색이나 BFS를 이용해서 풀어볼려고 했으나 메모리 제한이나, 시간 제한이 걸려서 풀 수 없었다.
         * 3. 즉 DFS를 이용한 DP로 문제를 풀어야 한다.
         * 4. 각 현재의 값에 대해서 4방향을 검색해서 얻게 되는 경우수를 더하면서 진행한다.  즉 끊임 없이 파고 들지만 이미 파고들어서 나온 결과값을 그대로 사용하는 것이다.
         * 5. return에 대한 값은 우리가 마지막 위치에 도달할때 1을 리턴해줘서 어떻게 보면 파고드는건 위에서 부터인데 채워지는것은 아래서부터라고 보면 맞다.
         * 6. 그리고 한번 갔던 곳에 대해서는 또다시 갈 필요없기 때문에 여기서 또 가지치기를 해준다.
         * 7. 어떻게 동작하는지는 아래 그림으로 표현했다.
         *
         *
         * dp[y][x] = (y, x) 지점일 때 존재하는 경로의 갯수
         *
         */

        System.out.println(DFS(M-1,N-1));
    }
    static int DFS(int y, int x) {
        if (y == 0 && x == 0) return 1;

        if (dp[y][x] == -1) {
            dp[y][x] = 0;

            for (int i = 0; i < dx.length; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny < 0 || ny >= M || nx < 0 || nx >= N) continue;

                if (map[y][x] < map[ny][nx]) {
                    dp[y][x] += DFS(ny, nx);
                    System.out.println(y + " " + x + " " + dp[y][x]);
                }

            }
        }
        return dp[y][x];

    }
}