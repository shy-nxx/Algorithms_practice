package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Coin2_DP {
    /**
     * 경우의 수 구하는 것과 비슷한 방식으로 진행하지만 최소값을 구하기 위해 dp[0] = 0 으로 두고 dp를 정수의 최대값으로 초기화헌다.
     * dp[j]와 dp[j-coin[i]] + 1을 비교하여 최소값을 저장한다.
     * dp[k] == 정수의 최대값일 경우 불가능한 경우이므로 -1을 출력한다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N+1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            coins[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[K+1];

        Arrays.fill(dp, 1001);

        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = coins[i]; j <= K; j++) {
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }

        System.out.println((dp[K] == 1001) ? -1 : dp[K]);


    }
}