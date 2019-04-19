package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_10844_easy_upstair_num {
    /**
     * 45656이란 수를 보자.
     * <p>
     * 이 수는 인접한 모든 자리수의 차이가 1이 난다. 이런 수를 계단 수라고 한다.
     * <p>
     * 세준이는 수의 길이가 N인 계단 수가 몇 개 있는지 궁금해졌다.
     * <p>
     * N이 주어질 때, 길이가 N인 계단 수가 총 몇 개 있는지 구하는 프로그램을 작성하시오. (0으로 시작하는 수는 없다.)
     *
     * N==1
     * 1,2,3,4,5,6,7,8,9
     *
     * N==2
     * 10, 12, 21,23, 32,34, 43,45 -> N-1의 수에서 += 1을 일의 자리에 붙인다.
     *
     * dp[N][L] =  dp[N-1][L-1] + dp[N-1][L+1]
     * 길이가 N 일 때, 마지막 수가 L일 경우의 계단 수
     *
     * 주의할 점은 위의 점화식은 L이 (1 ~ 8) 일 때 성립한다.
     *
     * 왜냐하면 0은 +1을 한 1만 허용되고 9는 -1을 한 8만 적용되기 때문이다.
     *
     * 구체적인 점화식은 다음과 같다.
     *
     *
     * L == 0 (10)
     * dp[N-1][L+1]
     *
     * L == (1~8)
     * dp[N-1][L-1] + dp[N-1][L+1]
     *
     * L == 9 (89)
     * dp[N-1][L-1]
     *
     * 이해가 안돼...
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //정수의 개수

        int[][] dp = new int[101][11];

        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1; //N==1 일 때

        }

        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i-1][1]; //L==0일 때

            for (int j = 1; j <= 9; j++) {
                if (j == 9) { //L==9 -> 89
                    dp[i][j] = dp[i-1][j-1] % 1000000000;
                }
                else
                    dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1])% 1000000000;

//                System.out.println(dp[i-1][j-1] + " " + dp[i-1][j+1]);
            }

        }

        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += dp[N][i];
        }

        System.out.println(sum);

    }
}