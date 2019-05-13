package DFS.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bipartite_graph_DFS {
    /**
     * 이분 그래프는 각 인접하는 정점들끼리 같은 색을 띄고 있으면 안된다.
     * 색으로 나눠서 인접한 정점이면 다른 색을 칠해준다.
     * <p>
     * 아직 탐색하지 않는 노드는 0
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

            int[] color = new int[V+1];
            int[][] link = new int[V+1][V+1];

            for (int i = 0; i <= V; i++) {
                Arrays.fill(color, 0);
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                link[a][b] = link[b][a] = 1;
            }

            if (bfs(V, link, color)) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }
    static boolean bfs(int N, int[][] link, int[] color) {
        Queue<Integer> q= new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (color[i] == 0) {
                color[i] = RED;
                q.add(i);

                while (!q.isEmpty()) {
                    int node = q.poll();

                    for (int j = 1; j <= N; j++) {
                        if (link[node][j] == 1) {
                            if (color[j] == 0) {
                                q.add(j);
                                color[j] = color[node] * -1;
                            }else if (color[j] + color[node] != 0) {
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