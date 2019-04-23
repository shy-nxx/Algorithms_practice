package DFS.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Find_path_DFS {
    /**
     * 한 정점에 연결된 연결관계를 찾기 -> BFS 보다 DFS로 해결하는 것이 좋다.
     * 한 정점을 기준으로 탐색하면서 일차원 배열에 연결 관계를 저장한 다음에 이차원 결과 행렬에 저장한다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] graph = new int[N][N];
        int[] link = new int[N];
        int[][] result = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken()); //가중치 없는 방향 그래프
            }
        }

        for (int i =0; i < N; i++) {
            dfs(graph, link, i);

            for (int j = 0; j < N; j++) {
                result[i][j] = link[j];
            }
            Arrays.fill(link, 0);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j< N; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void dfs(int[][] graph, int[] link, int v) {
        //v : 기준이 되는 정점

        int n = graph.length - 1;

        for (int i = 0; i <= n; i++) {
            if (graph[v][i] == 1 && link[i] == 0) {
                link[i] =1;
                dfs(graph, link, i);
            }
        }
    }
}