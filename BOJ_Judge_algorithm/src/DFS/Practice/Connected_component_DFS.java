package DFS.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Connected_component_DFS {
    /**
     * 연결 요소의 개수 구하기
     * -> 엣지와 노드가 겹치지 않는 완전 연결 그래프의 개수를 구한다.
     * <p>
     * DFS로 한 정점을 기준으로 연결 상태를 파악하고 한 번 방문한 정점은 다시 방문하지 않도록 메모라이즈한다.
     * DFS 함수의 진입 횟수는 = 연결 요소의 개수
     */

    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //정점
        int M = Integer.parseInt(st.nextToken()); //간선

        int[][] graph = new int[N+1][N+1];

        visited= new boolean[N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x][y] = graph[y][x] = 1;
        }

        int result = 0;

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                dfs(graph, i);
                result++;
            }
        }

        System.out.println(result);
    }

    static void dfs(int[][] graph, int v) {
        int n = graph.length -1;

        visited[v] = true;
        for (int i = 1; i <= n; i++) {
            if (graph[v][i] == 1 && !visited[i]) {
                dfs(graph, i);
            }
        }
    }
}