package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DP_1309_zoo {
    /**
     * 이 동물원에는 사자들이 살고 있는데 사자들을 우리에 가둘 때, 가로로도 세로로도 붙어 있게 배치할 수는 없다.
     * 이 동물원 조련사는 사자들의 배치 문제 때문에 골머리를 앓고 있다.
     * <p>
     * 동물원 조련사의 머리가 아프지 않도록 우리가 2*N 배열에 사자를 배치하는 경우의 수가 몇 가지인지를 알아내는 프로그램을 작성해 주도록 하자.
     * 사자를 한 마리도 배치하지 않는 경우도 하나의 경우의 수로 친다고 가정한다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N+1][3];
        /**
         * 0 - 둘 다 없음
         * 1 - 왼쪽만 있음
         * 2 - 오른쪽만 있음
         */

        /**
         * 현재 있는 칸에 사자가 있다면 내 대각선에 존재하거나 존재하지 않을 수있다.
         *
         */
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], 1);
        }

        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-1][1] + dp[i-1][2] % 9901;
            dp[i][1] = dp[i-1][0] + dp[i-1][2] % 9901;
            dp[i][2] = dp[i-1][0] + dp[i-1][1] % 9901;

        }

        System.out.println(dp[N][0] + dp[N][1] + dp[N][2]);

    }
}
