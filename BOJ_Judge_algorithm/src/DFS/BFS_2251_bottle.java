package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BFS_2251_bottle {
    /**
     * 각각 부피가 A, B, C(1≤A, B, C≤200) 리터인 세 개의 물통이 있다.
     * 처음에는 앞의 두 물통은 비어 있고, 세 번째 물통은 가득(C 리터) 차 있다. 이제 어떤 물통에 들어있는 물을 다른 물통으로 쏟아 부을 수 있는데,
     * 이때에는 한 물통이 비거나, 다른 한 물통이 가득 찰 때까지 물을 부을 수 있다. 이 과정에서 손실되는 물은 없다고 가정한다.
     * <p>
     * 이와 같은 과정을 거치다보면 세 번째 물통(용량이 C인)에 담겨있는 물의 양이 변할 수도 있다.
     * 첫 번째 물통(용량이 A인)이 비어 있을 때, 세 번째 물통(용량이 C인)에 담겨있을 수 있는 물의 양을 모두 구해내는 프로그램을 작성하시오.
     */

    static int A, B, C;
    static boolean[][] visited = new boolean[201][201];
    static boolean[] result = new boolean[201];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        /**
         * 한 물통이 가득차거나 빌때까지 부을 수 있다.
         * A-> B
         * A-> C
         * B-> A
         * B-> C
         * C-> A
         * C-> B
         *
         */

        dfs(0,0, C); //A, B는 처음에 비어잇음

        for (int i = 0; i < result.length; i++) {
            if (result[i]) {
                System.out.print(i + " ");
            }
        }
    }

    static void dfs(int ca, int cb, int cc) {
        if (visited[ca][cb]) return;

        if (ca == 0) { //a가 비어있을 때
            result[cc] = true;
        }

        visited[ca][cb] = true;

        //모든 경우의 수를 계산한다.
        // a -> b
        if (ca + cb > B) {
            dfs((ca + cb) - B, B, cc);
        } else {
            dfs(0, ca+cb, cc);
        }

        //b -> a
        if (ca + cb > A) {
            dfs(A, (ca+cb) - A, cc);
        } else {
            dfs(ca+cb, 0, cc);
        }

        //c->a
        if (cc+ca > A) {
            dfs(A, cb, (cc+ca) - A);
        } else {
            dfs(ca+cc, cb, 0);
        }

        //c-> b
        if (cc+cb > B) {
            dfs(ca, B, (cc+cb) - B);
        } else {
            dfs(ca, cb+cc, 0);
        }

        //a->c / b-> c
        //a + b = c -> c에서 이동하기 때문에 (넘치지 않는다)
        dfs(ca,0, cc+cb);
        dfs(0, cb, cc+ca);


    }
}