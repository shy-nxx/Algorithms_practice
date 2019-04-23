package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DFS_1707_Bipartite_Graph {
    /**
     * 그래프의 정점의 집합을 둘로 분할하여, 각 집합에 속한 정점끼리는 서로 인접하지 않도록 분할할 수 있을 때, 그러한 그래프를 특별히 이분 그래프 (Bipartite Graph) 라 부른다.
     * <p>
     * 그래프가 입력으로 주어졌을 때, 이 그래프가 이분 그래프인지 아닌지 판별하는 프로그램을 작성하시오.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int k = 0; k < T; k++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            int[][] matrix = new int[V+1][V+1];

            for (int i =0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                matrix[a][b] = matrix[b][a] = 1;

            }

            boolean[] visited = new boolean[V+1];

            for (int i = 1; i <= V; i++) {

                if(dfs(i, visited)){
                    System.out.println("YES");
                    return;
                }
                else {
                    System.out.println("NO");
                    return;
                }
            }
        }
    }

    static boolean dfs(int start, boolean[] visited) {


    }
}