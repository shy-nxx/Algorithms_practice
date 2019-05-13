package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BFS_1613_history {
    /**
     * 역사, 그 중에서도 한국사에 해박한 세준이는 많은 역사적 사건들의 전후 관계를 잘 알고 있다.
     * 즉, 임진왜란이 병자호란보다 먼저 일어났으며, 무오사화가 기묘사화보다 먼저 일어났다는 등의 지식을 알고 있는 것이다.
     * <p>
     * 세준이가 알고 있는 일부 사건들의 전후 관계들이 주어질 때, 주어진 사건들의 전후 관계도 알 수 있을까? 이를 해결하는 프로그램을 작성해 보도록 하자.
     */

    static int N, K, S;
    static ArrayList<Integer>[] list;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        list = new ArrayList[N+1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b); //가중치 없는 방향 그래프
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());

        int[] result = new int[S];

        for (int i = 0; i < S; i++) {
            st = new StringTokenizer(br.readLine());
            int q1 = Integer.parseInt(st.nextToken());
            int q2 = Integer.parseInt(st.nextToken());

            visited = new boolean[N+1];

            result[i] = dfs(q1, q2);
        }

        for (int i = 0; i < S; i++) {
            System.out.println(result[i]);
        }
    }

    static int dfs(int a, int b) {
        visited[a] = true;

        for (int node : list[a]) {
            if (!visited[node]) {
                if (node == b) return -1;
                dfs(a, node);
            }
        }

        visited = new boolean[N+1];

        for (int node : list[b]) {
            if (!visited[node]) {
                if (node == a) return 1;
                dfs(a, node);
            }
        }

        return 0;
    }
}