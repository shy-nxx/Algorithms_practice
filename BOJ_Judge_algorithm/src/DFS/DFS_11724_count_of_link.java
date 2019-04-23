package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DFS_11724_count_of_link {
    /**
     * 방향 없는 그래프가 주어졌을 때, 연결 요소 (Connected Component)의 개수를 구하는 프로그램을 작성하시오.
     * 첫째 줄에 정점의 개수 N과 간선의 개수 M이 주어진다.
     * (1 ≤ N ≤ 1,000, 0 ≤ M ≤ N×(N-1)/2) 둘째 줄부터 M개의 줄에 간선의 양 끝점 u와 v가 주어진다. (1 ≤ u, v ≤ N, u ≠ v) 같은 간선은 한 번만 주어진다.
     */

    static boolean[] visited;
    static int result= 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //정점의 개수

        int M = Integer.parseInt(st.nextToken()); //간선의 개수

        int[][] graph = new int[N+1][N+1];


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x =  Integer.parseInt(st.nextToken());
            int y =  Integer.parseInt(st.nextToken());
            graph[x][y] = graph[y][x] = 1;//무방향 그래프

        }

        visited = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            if (!visited[i])  {
                //방문 안한 것 같으면 방문
                dfs(graph, i);
                result++; //진입횟수만 구하면 된다.
            }
        }


        System.out.println(result);
    }
    static void dfs(int[][] graph, int v) {
        int n = graph.length-1;

        visited[v] = true;
        /**
         * 연결요의 개수 -> 엣지와 정점의 겹침이 없는 완전 연결 그래프의 개수
         */
        for (int i = 1; i <= n; i++){
            if (graph[v][i] == 1 && !visited[i]) {
//                System.out.println(v + " " + i);
                dfs(graph, i);
            }
        }
    }
}