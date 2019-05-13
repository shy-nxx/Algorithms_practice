package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BFS_9372_travel_of_sanggin {
    /**
     * 상근이는 겨울방학을 맞아 N개국을 여행하면서 자아를 찾기로 마음먹었다.
     * <p>
     * 하지만 상근이는 새로운 비행기를 무서워하기 때문에, 최대한 적은 종류의 비행기를 타고 국가들을 이동하려고 한다.
     * <p>
     * 이번 방학 동안의 비행 스케줄이 주어졌을 때, 상근이가 가장 적은 종류의 비행기를 타고 모든 도시들을 여행할 수 있도록 도와주자.
     * <p>
     * 상근이가 한 국가에서 다른 국가로 이동할 때 다른 국가를 거쳐 가도(심지어 이미 방문한 국가라도) 된다.
     */

    static int N, M;
    static int[] graph;
    static boolean[] visited;

    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int k = 0; k < T; k++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //여행 국가 수
            M = Integer.parseInt(st.nextToken()); //비행기 수

            graph = new int[N + 1];

            visited = new boolean[10001];

            for (int i = 1; i <= N; i++) {
                graph[i] = i;
            }

            ans = 0;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                union(a, b); //부모로 합치기

                for (int j = 1; j < N; j++ ) {
                    System.out.print(graph[j] + " ");
                }
            }

            System.out.println(ans);
            /**
             * 국가를 여행하는데 탈 수 있는 비행기의 최소 개수
             * -> 모든 국가를 다 방문해야하니까 N만큼?
             */
        }

    }
    static void union(int a, int b) {
        int node1 = find(a);
        int node2 = find(b);

        if (node1 == node2) return; //이미 같은 부모임
        graph[node2] = node1; //부모를 바꿔줌 -> 합치기

        ans++; //합치기 할 때마다 증가시켜준다.
    }

    static int find(int n) {
        if (graph[n] == n ) return n;
        return graph[n] = find(graph[n]); //최종 부모를 찾아 떠남
    }


//    static void dfs(int x, int[] dp ){
//
//        for (int i = 1; i < N; i++) {
//            if (!visited[x][i] && graph[x][i] == 1) {
//                dp[i] = dp[x] + 1;a
//                visited[x][i] = true;
//                dfs(i, dp);
//            }
//
//        }
//    }
}