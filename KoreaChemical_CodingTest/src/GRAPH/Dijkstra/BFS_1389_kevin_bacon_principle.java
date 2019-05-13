package GRAPH.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_1389_kevin_bacon_principle {
    static int N, M;
    static int[][] friends = new int[101][101];
    static boolean[][] visited = new boolean[101][101];

    static int min = Integer.MAX_VALUE;
    static int min_p;

    static int[][] dp = new int[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            friends[a][b] = 1;
            friends[b][a] = 1;

        }

        for (int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], -1); //다익스트라알고리즘
        }

        for (int i = N; i >= 1; i--) {
            bfs(i);

        }

        System.out.println(min_p);

    }
    static void bfs(int start) {
        dp[start][start] = 0; //자기자신과 자기자신은 0

        Queue<Integer> q = new LinkedList<>();

        q.add(start);

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int i = 1; i <= N; i++) {
//                System.out.println(dp[start][i]);
                //다익스트라는 빠꾸가 안됨 -> dp[start][i] != -1
                if (friends[now][i] == 0  || dp[start][i] != -1) continue;
                dp[start][i] = dp[start][now] + 1;

                q.add(i);
            }

        }

        int sum = 0;
        for (int i =1; i<= N; i++) {
            sum += dp[start][i];
        }

        if (min >= sum) {
            min = sum;
            min_p = start;
        }
    }
}