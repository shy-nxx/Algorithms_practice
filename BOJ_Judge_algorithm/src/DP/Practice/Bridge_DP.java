package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bridge_DP {
    /**
     * 다리는 크로스 되지 않는다. -> 맨 위의 N 지점은 맨위의 M지점과 이어진다면
     * 1개의 사이트에서 M 개의 사이트를 잇는 경우 -> M개의 경우의 수 가 나온다.
     * <p>
     * 2개의 사이트가 주어진 경우 -> 맨 위의 다리름 먼저 연결하여 고정한 후 나머지 자리의 다리를 연결한다.(크로스 되지 않기 때문이다.)
     * <p>
     * dp[N][M] = N과 M을 잇는 다리의 경우의 수
     * <p>
     * 포인트는 일단 제일 위의 다리를 먼저 연결해서 고정시켜놓은 뒤, 나머지 자리에 남은 다리들을 이어주는 것이다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken()); //테스트 케이스 개수

        for (int k = 0; k < T; k++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] dp = new int[N+1][M+1];

            if (N == 1) {
               System.out.println(M);
               continue;
            }
            if (N == M) {
                System.out.println(1);
                continue;
            }

            for (int i = 1; i <= M; i++) { //이을 다리가 한 개인 경우
                dp[1][i] = i;
            }

            for (int i = 2; i <= N; i++) { //이을 다리가 두개 이상인 경우
                for (int j = i; j <= M; j++ ){
                    dp[i][j] = dp[i][j-1] + dp[i-1][j-1];
                }
            }
            System.out.println(dp[N][M]);
        }
    }
}