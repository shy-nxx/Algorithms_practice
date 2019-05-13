package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DFS_1613_history {
    /**
     * 역사, 그 중에서도 한국사에 해박한 세준이는 많은 역사적 사건들의 전후 관계를 잘 알고 있다.
     * 즉, 임진왜란이 병자호란보다 먼저 일어났으며, 무오사화가 기묘사화보다 먼저 일어났다는 등의 지식을 알고 있는 것이다.
     * <p>
     * 세준이가 알고 있는 일부 사건들의 전후 관계들이 주어질 때, 주어진 사건들의 전후 관계도 알 수 있을까? 이를 해결하는 프로그램을 작성해 보도록 하자.
     */

    static int N, K, S;
    static ArrayList<Integer>[] list;
    static int[][] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        edges = new int[N+1][N+1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            edges[a][b] = -1;
            edges[b][a] = 1;
        }

                for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(edges[i][j] + " ");
            }
            System.out.println();
        }


        for (int a = 1; a <= N; a++) { //a (...) b
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (edges[i][a] != 0 && edges[i][a] == edges[a][j]) {
                        //i -> a , i -> a && a -> j == i-> j
                        //a -> i , a -> i && j -> a ==> j-> i
                        edges[i][j] = edges[i][a];
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(edges[i][j] + " ");
            }
            System.out.println();
        }
        /**
         *
         * 플로이드 와셜 알고리즘에 따라
         *
         * Start -> 임의의 노드 -> End를 만족하는 임의의 노드가 존재할 때 (자기 자신을 포함하여)
         *
         * Start -> End 를 만족함을 알 수 있습니다.
         *
         * x y 두 사건의 관계를 x < y 라고 하자. 이렇게 되면 이제 우리가 찾아야 할 것은 x < (...) < y 의 관계를 만족하는 다른 사건들과의 관계 이다.
         * 가장 바깥쪽 for문이 우리가 찾을 (...) 에 해당한다.
         *
         * 플로이드와샬은 음수 가중치를 계산할 수 있다는 점이 다익스트라와 다르다.
         * 문제에서 친절히 -1,0,1 과 같이 힌트를 주었다. 주어지는 사건 x,y 에 대해 1 과 -1 을 부여한다. x y 순으로 주어진다면 아래와 같다.
         *
         * i < a 이고 i < a && a < j 라면, 당연히 i < j 가 성립한다.
         *
         * i > a 이고 i > a && a > j 라면, 당연히 i > j 가 성립한다.
         */
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());

        int[] result = new int[S];

        for (int i = 0; i < S; i++) {
            st = new StringTokenizer(br.readLine());
            int q1 = Integer.parseInt(st.nextToken());
            int q2 = Integer.parseInt(st.nextToken());

            result[i] = edges[q1][q2];
        }

        for (int i = 0; i < S; i++) {
            System.out.println(result[i]);
        }

    }

}
