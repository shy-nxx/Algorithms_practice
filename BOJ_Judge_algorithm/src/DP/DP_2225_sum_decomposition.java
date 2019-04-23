package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_2225_sum_decomposition {
    /**
     * 0부터 N까지의 정수 K개를 더해서 그 합이 N이 되는 경우의 수를 구하는 프로그램을 작성하시오.
     * <p>
     * 덧셈의 순서가 바뀐 경우는 다른 경우로 센다(1+2와 2+1은 서로 다른 경우). 또한 한 개의 수를 여러 번 쓸 수도 있다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());


        /**합분해 ->
         * 1이 되기 위한 합분해 => 0+1
         * 2가 되기 위한 분해 => 1+1
         * 12는 1+2+....+(12-n) => 각 dp를 더하면 되륻ㅅ..?
         *
         * K(5)개의 숫자
         * 1 2 3 4 5  = 15 (N)
         * ------- L
         * sig(1,2,3,4) -> N-L
         * 1,2,3,4 -> K-1
         *
         * dp[K][N] += dp[K-1][N-L]
         */

        int[][] dp = new int[K+1][N+1];

        for (int i = 0; i <= N; i++) {
            dp[1][i] = 1; // 한자리는 하나씩 더하는 수밖에 없음
        }

        for (int i = 1; i <= K; i++) {
            for (int j = 0; j <= N; j++) {
                for (int l = 0; l <= j; l++)
                //0~N 원소를 K개 더해서 N을 만든다.
                    dp[i][j] += dp[i-1][j-l];
//                System.out.println(dp[i][j]);
            }
        }
        System.out.println(dp[K][N]);
    }
}