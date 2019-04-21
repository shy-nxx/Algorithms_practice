package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ascending_nums_re_DP {
    /**
     * 0도 포함되기 때문에 N==1일때를 제외하고 그 이상의 자리수는 0-9 / i-9 반복문을 돌면서 값을 증가시켜야한다.
     * dp[2][0] + dp[1][0] = 1
     * ...
     * dp[2][9] + dp[1][9] = 1
     * <p>
     * dp[2][1] + dp[1][1] = 1
     * ,,,
     * dp[2][9] + dp[1][9] = 1
     * <p>
     * dp[2][2] + dp[1][2] = 1
     * ,,,
     * dp[2][9] + dp[1][9] = 1
     * <p>
     * <p>
     * dp[i-1][k]는 계산하는 숫자를 줄여나가야 한다.
     * <p>
     * 0 1 2 3 4 5 6 7 8 9 ->10
     * 11 12 13 14 15... 19 -> 9
     * 22 23 24 ... 29 -> 8
     * 33 34 ... 39 -> 7
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());


        int[][] dp = new int[1001][1001];

        for (int i = 0; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = j; k <= 9; k++) {
                    dp[i][j] = dp[i][j] + dp[i-1][k] % 10007;
                }
            }
        }

        long sum = 0;

        for (int i = 0; i <= 9; i++) {
            sum += dp[N][i];
        }

        System.out.println(sum);
    }

}