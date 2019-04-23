package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BFS_11403_find_path {
    /**
     * 가중치 없는 방향 그래프 G가 주어졌을 때, 모든 정점 (i, j)에 대해서, i에서 j로 가는 경로가 있는지 없는지 구하는 프로그램을 작성하시오.
     * 첫째 줄에 정점의 개수 N (1 ≤ N ≤ 100)이 주어진다. 둘째 줄부터 N개 줄에는 그래프의 인접 행렬이 주어진다.
     * i번째 줄의 j번째 숫자가 1인 경우에는 i에서 j로 가는 간선이 존재한다는 뜻이고, 0인 경우는 없다는 뜻이다. i번째 줄의 i번째 숫자는 항상 0이다.
     */

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        int[][] graph = new int[N+1][N+1];
        int[][] result = new int[N+1][N+1];
        int[] link = new int[N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                graph[i][j]= Integer.parseInt(st.nextToken());
            }
        }

        /**
         * 위와 같이 주어진 인접행렬을 통해 정점 간의 간선과 그래프를 표현할 수 있다.
         *
         * 문제에서 원하는 것은 어떤 정점에서 어떤 정점까지의 경로가 존재하는 여부이다.
         *
         *
         *
         * 예를 들어, i가 1이고, j가 5일 경우를 보자.
         *
         * 1->2->4->5 연결된 간선을 통해 경로가 존재한다.
         *
         * i가 2이고, j가 3일 경우를 보자.
         *
         * 간선은 있지만 무방향 그래프가 아니므로 2에서 3으로 가는 간선이 존재하지 않는다.
         *
         * i가 3이고, j가 4일 경우를 보자.
         *
         * 3에서 4의 경로를 찾을 때 3과 4는 연결된 간선이 없으므로 경로가 없다라고 생각할 수 있지만, 이 경우 헷갈려서는 안된다.
         *
         * 3->2->4 처럼 탐색이 가능하기 때문이다.
         *
         *
         *
         * DFS의 특성상 깊이 탐색하기 때문에  각 정점을 기준으로 DFS를 돌면서 방문여부를 체크하면 된다.
         *
         * 즉, DFS는 한 정점을 기준으로 잡고 그 정점에 연결된 정점을 탐색한다.
         *
         * 방문했다면 기준 정점과 방문 정점간의 연결되었기 때문에 경로가 존재하는 것이 된다.
         *
         *
         */


        for (int i = 1; i <= N; i++) {
            dfs(graph, link, i); //1번 정점부터 차례대로 dfs 실행
            for (int j = 1; j <= N; j++) {
                result[i][j] = link[j];
            }
            Arrays.fill(link, 0);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(result[i][j]);
            }
            System.out.println();
        }

    }
    static void dfs(int[][] graph, int[] link, int v) {
        int n = graph.length - 1;

        for (int i = 1; i <= n; i++) {
            System.out.println(v + " " + i);
            if (graph[v][i] == 1 && link[i] == 0) { //정해진 간선과 1부터 차례대로 증가하는 간선이 이어져 있는 지 확인 (이미 1로 설정되어있으면 다시 검사 안함)
                link[i] = 1;
                dfs(graph, link, i);
            }
        }


    }
}