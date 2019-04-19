package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ascending_num_DP {
    /**
     * 0 1 2 3 4 5 6 7 8 9
     * 11 12 13 ... 19
     * 22 23 ... 29
     * <p>
     * 앞의 자리수와 같거나 증가하는 수만큼을 더해줘야 하고, 현재의 j 자릿수에 앞의 숫자가 i 라고 했을 때 이 숫자는 j-1 자릿수의 앞자리가 i 보다 크거나 같은 값으로 만들 수 있다.
     * -> N자리수가 될때까지 반복한다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] dp = new int[1001][11];

        for (int i = 0; i <= 9; i++) {
            dp[1][i] =1; //자리수가 1일때
        }

        for (int i = 2; i <= N; i++) { //자리수가 2이상일 때
            for (int j = 0; j <= 9; j++) {
                for (int k = j; k <= 9; k++) {
                    dp[i][j] = (dp[i][j] + dp[i-1][k]) % 10007;

                }
            }
        }

        long sum = 0;

        for (int i = 0; i <=9; i++) {
            sum += dp[N][i];
        }

        System.out.println(sum);
    }
}