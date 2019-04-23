package BFS.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Degree_of_kinship_BFS {
    /**
     * 노드의 레벨수를 구하는 방식
     * 인접 리스트로 부모 자식 관계를 저장한 후 촌수를 찾는 첫번째 점 a를 중심으로 탐색한다.
     * 만약 찾는 촌수 b가 탐색되면 반복을 중단하고, b가 아니라면 visited가 false인 경우에 부모의 촌수 +1을 해준다.
     */

    static ArrayList<Integer>[] list;
    static boolean[] visited;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N+1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            list[parent].add(child); //무방향 그래프로 이어주어야 한다.
//            list[child].add(parent);
        }

        visited = new boolean[N+1];

        dp = new int[N+1];

        System.out.println(bfs(a,b));

    }

    static int bfs(int p1, int p2) {
        Queue<Integer> q = new LinkedList<>();

        q.add(p1);

        while (!q.isEmpty()) {
            int parent = q.poll();

            visited[parent] = true;

            if (parent == p2) break;

            for (int child : list[parent]) {
                if (!visited[child]) {
                    dp[child] = dp[parent] + 1;
                    q.add(child);
                }
            }
        }

        return dp[p2] == 0 ? -1 : dp[p2];
    }
}