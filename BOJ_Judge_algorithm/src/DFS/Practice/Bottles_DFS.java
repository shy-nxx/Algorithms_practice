package DFS.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bottles_DFS {
    /**
     * 모든 경우를 고려해야한다.
     * 일단 c에만 물이 차있고 a,b는 비어있기 때문에
     * 처음 dfs는 0,0,C 로 시작한다.
     * <p>
     * 문제의 결과는 a가 비어있을 때이기 때문에 a가 0일 때 중단한다.
     * <p>
     * a->b
     * a->c
     * b->a
     * b->c
     * c->a
     * c->b
     * <p>
     * 여기서 중요한 점은, c는 a나 b에서 물을 받더라도 넘칠 일이 없다는 것이다.
     */

    static int A, B, C;

    static boolean[][] visited = new boolean[201][201];
    static boolean[] aIsVacant = new boolean[201];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        dfs(0,0, C);

        for (int i = 0; i < aIsVacant.length; i++) {
            if (aIsVacant[i]) {
                System.out.print(i + " ");
            }
        }
    }

    static void dfs(int a, int b, int c) {
        if (visited[a][b]) return;

        if (a == 0) aIsVacant[c] = true;

        visited[a][b] = true;

        //a-> b
        if ((a+b) > B) {
            dfs((a+b)-B, B, c);
        } else {
            dfs(0, a+b, c);
        }

        //b->a
        if ((a+b) > A) {
            dfs(A, (a+b) -A, c);
        } else {
            dfs(a+b, 0, c);
        }

        //c->a
        if ((c+a) > A) {
            dfs(A, b, (c+a) - A );
        } else {
            dfs(c+a, b, 0);
        }

        //c-> b
        if ((c+b) > B) {
            dfs(a, B, (c+b) - B);
        } else {
            dfs(a, c+b, 0);
        }

        //a-> c / b-> c
        dfs(0, b, c+a);
        dfs(a, 0, c+b);
    }
}