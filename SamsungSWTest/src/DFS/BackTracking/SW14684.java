package DFS.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SW14684 {
    /**
     * 가로선이 있을 경우 matrix[a][b] = 1;
     * 왼쪽이 1이다 -> 왼쪽으로 이동 / 오른쪽이 1이다 -> 오른쪽으로 이동
     */
    static int N, M, H;
    static boolean[][] visited;
    static int[][] matrix;
    static int MAX;
    static boolean ok;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        if (M == 0) { //가로선이 없을 경우 -> 추가할 선 또한 없음
            MAX = 0;
            System.out.println(MAX);
            System.exit(0);
        }

        matrix = new int[H+2][N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            matrix[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }

        for (int i = 0; i < 3; i++) { //최대 사다리수는 3
            MAX = i;
            DFS(1,1, 0);
            if (ok) break; //사다리가 각자 제자리로 작동한다.
        }

        System.out.println((ok)? MAX : -1);
    }

    static void DFS(int x, int y, int cnt) {
        if (ok) return;

        if (MAX == cnt) {
            if (check()) {
                ok = true;

            }
            return;
        }

        for (int i = (y < N ? x : x+1); i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (matrix[i][j] == 1 || matrix[i][j-1]==1 || matrix[i][j + 1] == 1) continue;

                matrix[i][j] = 1;
                DFS(i, j, cnt+1);
                matrix[i][j] = 0;

            }
        }
    }
    static boolean check() {
        for (int i = 1; i <= N; i++) {
            int j = 1;
            int temp = i; // 1-1 / 1-2/ 1-3/.....

            while(j <= H + 1) {
                if (matrix[j][temp] == 1) {
                    temp++;
                }
                else if (matrix[j][temp-1] == 1) {
                    temp--;
                }
                j++;
            }
            if (i != temp) {
                return false;
            }
        }
        return true;
    }

}