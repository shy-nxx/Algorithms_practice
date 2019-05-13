package Graph.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Travel_of_sanggin_MST {
    /**
     * 최소신장트리
     * <p>
     * 비행기는 국가 수 -1만큼만 타면 모든 국가를 돌 수 있다.
     * <p>
     * 최소 신장 트리를 갖고 모든 경로를 이을 간선의 수를 구할 수 있다. -> 최소신장 트리를 사용하여 해결함
     */

    static int N, M;
    static int[] graph;

    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int k = 0; k < T; k++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            graph = new int[N+1];

            for (int i = 1; i <= N; i++) {
                graph[i] = i;
            }

            ans = 0;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                union(a, b);
            }

            System.out.println(ans);
        }

    }
    static void union(int a, int b) {
        int node1 = find(a); //부모를 찾음
        int node2 = find(b);

        if (node1 == node2) return; //이미 같은 부모

        graph[node2] = node1; //부모를 합쳐줌

        ans++;

    }

    static int find(int n) {
        if (graph[n] == n) return n; //자기자신이 부모

        return graph[n] = find(graph[n]); //최종 부모를 찾아 떠남
    }
}