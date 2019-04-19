package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_11727_2n_tile_2 {
    /**
     * 2×n 직사각형을 2×1과 2×2 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.
     *
     * 아래 그림은 2×17 직사각형을 채운 한가지 예이다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //2*n

        /**
         * 타일의 개수
         * 2*1로만 이루어진 경우 (갸로 세로 다 허용됨)
         * 2*2로만 이루어진 경우 (짝수의 경우에만 가능)
         *
         * 너비가 1일 때 경우의 수 => 1
         * 너비가 2일때 경우의 수 = 1 일때 경우의 수와 2일때 경우의 수의 합  -> 1 + 1 / 가로 2 / 2*2 => 3
         * 너비가 3일 때의 경우의 수 = 1일때 경우의 수와 2일때 경우의 수의 합 => 5
         *
         * 너비가 짝수의 배수일 때는 +1 ??
         *
         * 현재 너비가 1일 때 -> dp[i] = dp[i-1] + 1;
         * 현재 너비가 2일 때 -> dp[i] = dp[i-1] + dp[i-2] //너비가 1일 때  + 2일 때
         *
         */
        int[] dp = new int[N+1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= N; i++ ) {
            dp[i] = (dp[i-1] + dp[i-2] + dp[i-2]) % 10007;
        }

        System.out.println(dp[N]);



    }
}
