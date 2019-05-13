package DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DP_6359_drunken_sangbum {
    /**
     * n번째 라운드를 진행 (각 n의 배수만큼 문을 열고 닫는다. 처음 라운드에는 전부 연다)
     */
    static final int OPEN = 1;
    static final int CLOSE = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());

        for (int k = 0; k < T; k++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            boolean[][] visited = new boolean[N+1][2]; //열고 닫음을 표시

            for (int i = 1; i <= N; i++) {
                Arrays.fill(visited[i], false);
            }

            int ans = 0;

            for (int i = 1; i <= N; i++) { //라운드
                for (int j = 1; j <= N; j++) { //방
                    if (j % i == 0) {
                        visited[j][CLOSE] = !visited[j][CLOSE];
                        visited[j][OPEN] = !visited[j][OPEN];
                    }
                }

                if (visited[i][OPEN]) {
                    ans++;
                }

            }
            sb.append(ans + "\n");
        }
        System.out.println(sb.toString());



    }
}