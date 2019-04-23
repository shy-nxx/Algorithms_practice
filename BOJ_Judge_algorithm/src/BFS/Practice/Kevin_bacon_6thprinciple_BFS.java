package BFS.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Kevin_bacon_6thprinciple_BFS {
    /**
     * 다익스트라 알고리즘 활용
     * <p>
     * 간선의 초기값을 무한대 또는 음수로 설정 (다익스트라는 음수가 없음)
     * <p>
     * 다익스트라는 이미 결정된 간선의 가중치 결과를 되돌리지 않기 때문에 -1이 아니면 진행하지 않는다.
     * <p>
     * 각 간선의 최소 거리를 구하는 것과 같다.
     */

    static int N, M;
    static int[][] link;
    static int[][] dp;
    static Queue<Integer> q = new LinkedList<>();

    static int min = Integer.MAX_VALUE;
    static int min_p = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        link = new int[N+1][N+1];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            link[a][b] = link[b][a] = 1;
        }


        dp = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = N; i >= 1; i--){
            bfs(i);
            System.out.println(min_p);
        }


    }

    static void bfs(int start) {
        dp[start][start] = 0;

        q.add(start);

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int i = 1; i <= N; i++) {
                if (link[now][i] == 0 || dp[start][i] != -1) continue;

                dp[start][i] = dp[start][now] + 1;

                q.add(i);
            }
        }

        int sum = 0;

        for (int i = 1; i <= N; i++) {
            sum += dp[start][i];
        }

        System.out.println(start + " " + sum);
        if (min >= sum) {
            min = sum;
            min_p = start;
        }
    }
}