package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Coin_DP {
    /**
     * 풀이
     *
     * 현재 사용할 수 잇는 동전의 가치가 주어진다면
     * 원하는 가격까지 더해가면서 만들 수 있는 경우의 수만큼 더해준다.
     *
     * 1 / 3 / 5 원이고 만들려는 수가 10인 경우
     *   1 2 3 4 5 6 7 8 9 10
     * 1 1 1 1 1 1 1 1 1 1 1
     * 3     2 2 2 3 3 3 4 4
     * 5         3 4 4 4 5 7
     *
     * dp[j] = 이전의 동전(i-1)을 기준으로 누적시킨 경우의 수
     * dp[j-coins[i]] = 현재 동전(i)을 사용하여 만들 수 있는 경우의 수
     *
     * dp[0] = 1은 합이 1원의 경우 동전 1원으로 만들 수 있는 경우, 합이 3원일 경우 동전 3원으로 만들 수 있는 경우와 같은 경우를 위함이다.
     *
     * j를 coin[i]로 시작하는 이유는 coin[i]에 해당하는 가치보다 작은 합은 구할 수가 없기 때문이다.
     *
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N+1];

        for (int i=1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            coins[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[10010];

        dp[0] = 1;
        for (int i = 1; i <= N; i++ ) {
            for (int j = coins[i]; j <= K; j++) {
                dp[j] = dp[j - coins[i]] + dp[j];

            }
        }

        System.out.println(dp[K]);

    }

}
