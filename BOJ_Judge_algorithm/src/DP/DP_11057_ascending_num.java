package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_11057_ascending_num {
    /**
     * 오르막 수는 수의 자리가 오름차순을 이루는 수를 말한다. 이때, 인접한 수가 같아도 오름차순으로 친다.
     * <p>
     * 예를 들어, 2234와 3678, 11119는 오르막 수이지만, 2232, 3676, 91111은 오르막 수가 아니다.
     * <p>
     * 수의 길이 N이 주어졌을 때, 오르막 수의 개수를 구하는 프로그램을 작성하시오. 수는 0으로 시작할 수 있다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] dp = new int[1001][11];

        //dp[N][L]

        /**
         * 1. 이 문제는 한번에 우리가 원하는 자릿수를 구하는게 아니고 차근차근 자릿수를 올려가면서 구하면 될 거 같다 마치 피보나치 수열을 구하는 것 처럼
         * 2. 현재의 j 자릿수에 앞의 숫자가 i 라고 했을 때 이 숫자는 j-1 자릿수의 앞자리가 i 보다 크거나 같은 값으로 만들 수 있다.
         * 3. 이를 우리가 원하는 N자리수 까지 반복한다면 우리는 정답을 구할 수 있다.
         *

         */
        for (int i = 0; i <= 9; i++) {
            dp[1][i] = 1; //N이 한자리 수일 때
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = j; k <= 9; k++) {
                    dp[i][j] = (dp[i][j] + dp[i-1][k]) % 10007;
                }
           }
        }

        long sum = 0;

        for (int i = 0; i < 10; i++) {
            sum += dp[N][i] ;
        }

        System.out.println(sum);

    }
}