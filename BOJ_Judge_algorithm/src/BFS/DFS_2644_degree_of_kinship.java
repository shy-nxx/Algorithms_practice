package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DFS_2644_degree_of_kinship {
    /**
     * 우리 나라는 가족 혹은 친척들 사이의 관계를 촌수라는 단위로 표현하는 독특한 문화를 가지고 있다. 이러한 촌수는 다음과 같은 방식으로 계산된다. 기본적으로 부모와 자식 사이를 1촌으로 정의하고 이로부터 사람들 간의 촌수를 계산한다. 예를 들면 나와 아버지, 아버지와 할아버지는 각각 1촌으로 나와 할아버지는 2촌이 되고, 아버지 형제들과 할아버지는 1촌, 나와 아버지 형제들과는 3촌이 된다.
     * <p>
     * 여러 사람들에 대한 부모 자식들 간의 관계가 주어졌을 때, 주어진 두 사람의 촌수를 계산하는 프로그램을 작성하시오.
     */

    static int N, M;
    static int a, b;
    static ArrayList<Integer>[] list;

    static boolean[] visited;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //사람의 수

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 관계수

        list = new ArrayList[N+1];

        for (int i = 1; i <=N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            list[parent].add(child); //부모 - 자식
            list[child].add(parent);
        }

        dp = new int[N+1];
        visited = new boolean[N+1];
        System.out.println(bfs());

    }

    static int bfs() {
        Queue<Integer> q = new LinkedList<>();

        q.add(a);

        /**
         * depth[j] = depth[temp] + 1;  temp의 정점에서 j(정점)으로 갈 수 있을 때 +1 시켜준다.
         */

        while (!q.isEmpty()) {
            int parent = q.poll();

            visited[parent] = true;

            if (parent == b) break;

            else {
                for (int child : list[parent] ) {
                    if (!visited[child]) {
                        dp[child] = dp[parent] + 1;
                        q.add(child);
                    }
                }
            }
        }
        return dp[b] == 0 ? -1 : dp[b];
    }

}