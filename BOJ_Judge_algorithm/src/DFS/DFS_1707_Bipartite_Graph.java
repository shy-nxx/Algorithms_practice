package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DFS_1707_Bipartite_Graph {
    /**
     * 그래프의 정점의 집합을 둘로 분할하여, 각 집합에 속한 정점끼리는 서로 인접하지 않도록 분할할 수 있을 때, 그러한 그래프를 특별히 이분 그래프 (Bipartite Graph) 라 부른다.
     * <p>
     * 그래프가 입력으로 주어졌을 때, 이 그래프가 이분 그래프인지 아닌지 판별하는 프로그램을 작성하시오.
     */
    static final int RED = 1;
    static final int BLUE = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int k = 0; k < T; k++) {

            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            int[][] matrix = new int[V+1][V+1];

            int[] color = new int[V+1]; //아직 안 본 정점 : 0 / 집합1 : 1 / 집합2 : 2
            //각 노드의 같은 컬러들끼리 인접하지 않아야 한다.

            for (int i = 0; i <= V; i++) {
                Arrays.fill(color, 0); //아직 탐색되지 않는 노드들
            }

            for (int i =0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                matrix[a][b] = matrix[b][a] = 1;

            }

            if(BFS(V, matrix, color)){
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }

        }
    }

    /**
     * 이분 그래프의 예시는 http://mathworld.wolfram.com/BipartiteGraph.html에서 확인할 수 있습니다.
     * 이는 즉, DFS(Depth First Search) 혹은 BFS(Breadth First Search) 알고리즘을 적용하여 서로 이어지는 노드끼리 같은 색깔이면  안 된다는 뜻입니다.(링크에서는 빨간색과 검은색으로 표시했습니다.)
     * 정점의 집합을 둘로 분할하기 때문에 색깔은 2가지이면 되고 아직 방문하지 않은 노드를 0으로 표시하기 때문에 nodeColor[i]에는 0, 1, 2만 저장될 수 있습니다.
     * 따라서, DFS 알고리즘을 적용하여 next를 방문하면서 nodeNum과 다른 색깔을 입히고 다시 그래프 정점들을 순회하면서 이분 그래프 여부를 판별하면 풀 수 있는 문제였습니다!
     *
     */

    static boolean BFS(int N, int[][] matrix, int[] color) {

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++ ) {
            if (color[i] == 0) {
                color[i] = RED;
                q.add(i);

                while(!q.isEmpty()) {
                    int node = q.poll();

                    for (int j = 1; j <= N; j++) {
                        if (matrix[node][j] == 1) {
                            System.out.println(j + " " + node);
                            if (color[j] == 0) {
                                q.add(j);
                                color[j] = color[node] * -1; //인접한 노드는 다른 색으로 칠해준다.
                            }
                            else if (color[node] + color[j] != 0) {
                                return false;
                            }
                        }
                    }
                }

            }
        }

        return true;

    }
}