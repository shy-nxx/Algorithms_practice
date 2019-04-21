package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Coin2_re_DP {
    /**
     * 동전의 최소 개수이므로 현재 금액에서 해당 가치의 동전을 뺀 금액에서 동전을 추가하는 개수와 이전 동전에서 구한 개수 중 최소값을 도출하낟.
     * 불가능한 경우를 추려내기 위해 모든 배열의 값을 1001(max)로 설정한다.
     * <p>
     * dp[0] = 0; (0원을 만들 때는 0개의 동전이 필요하다)
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N+1];

        for (int i = 1; i <= N; i++ ) {
            st = new StringTokenizer(br.readLine());
            coins[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[K+1];

        for (int i = 1; i <= K; i++) {
            dp[i] = 1001;
        }

        dp[0] = 0;

        for (int i = 1; i<= N; i++) {
            for (int j = coins[i]; j<= K; j++) {
               dp[j] = Math.min(dp[j], dp[j-coins[i]] + 1);
            }
        }

        System.out.println((dp[K] == 1001) ? -1 : dp[K]);
    }
}