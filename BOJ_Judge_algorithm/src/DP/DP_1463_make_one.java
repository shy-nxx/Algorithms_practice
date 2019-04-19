package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_1463_make_one {
    /**
     * X가 3으로 나누어 떨어지면, 3으로 나눈다.
     * X가 2로 나누어 떨어지면, 2로 나눈다.
     * 1을 뺀다.
     * -> 연산의 최소 카운트 수
     *
     * 10 -> 9 -> 3 -> 1
     */
    /**
     *
     * 1번 규칙 (3으로 나누어 떨어진다) : D[N] = D[N/3] + 1
     * 2번 규칙 (2로 나누어 떨어진다) : D[N] = D[N/2] + 1
     * 3번 규칙 ( 1 을 뺀다 ): D[N] = D[N-1] + 1
     * 작은 문제서 부터 큰 문제를 풀어가는 방식입니다.
     * 숫자 1에서 부터
     * 2로 갈때의 경우의 수 중 가장 작은 방법.
     * 3으로 갈때의 경우의 수 중 가장 작은 방법.
     * .
     * .
     * .
     * N일때 가장 작은 방법.
     * 으로 구하면됩니다.
     */
    public static  void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int dp[] = new int[N+1];

        /**
         * dp
         * n = 1일 때 가장 작은 방법
         * n = 2일 때 가장 작은 방법
         * ....
         * n = 10일 때 가장 작은 방법을 구해간다.
         */

        dp[1] = 0;
        for (int j = 2; j <= N; j++) {
            dp[j] = dp[j - 1] + 1;
            if (j % 2 == 0) {
                dp[j] = Math.min(dp[j], dp[j/2] + 1);
            }
            if (j % 3 == 0) {
                dp[j] = Math.min(dp[j], dp[j/3] + 1);
            }
            System.out.println(dp[j]);
        }

        System.out.println(dp[N]);
    }
}
