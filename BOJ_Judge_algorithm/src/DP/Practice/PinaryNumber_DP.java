package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PinaryNumber_DP {
    /**
     * 풀이
     * 맨 앞자리 수는 무조건 1 / 그 다음 수는 1이 겹쳐 나오면 안되기 때문에 0
     *
     * -> 현재 1이 나오려면 앞에 0
     * 현재 0이 나오려면 앞에 0, 1 -> 즉, dp[0] = 앞에 1이 있을 경우와 0이 있틀 경우의 합
     * dp[1] = 앞에 0이 있을 경우의 수
     *
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //N자리의 이친수를 구하라 101 100

        long dp[][] = new long[100][2];

        dp[1][0] = 1;
        dp[1][1] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-1][1];
            dp[i][1] = dp[i-1][0];
        }

        System.out.println(dp[N][1] + " " + dp[N][0]);
    }

}
