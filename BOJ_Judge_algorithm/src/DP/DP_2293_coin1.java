package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_2293_coin1 {
    /**
     * n가지 종류의 동전이 있다. 각각의 동전이 나타내는 가치는 다르다. 이 동전을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다. 그 경우의 수를 구하시오. 각각의 동전은 몇 개라도 사용할 수 있다.
     * <p>
     * 사용한 동전의 구성이 같은데, 순서만 다른 것은 같은 경우이다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //동전의 개수

        int K = Integer.parseInt(st.nextToken()); //k원

        int[] coins = new int[N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            coins[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[10001]; //각 N에 따른 다른 코인들의 개수를 저장

        /**

         dp[j] => 이전의 동전(i-1)을 기준으로 누적시킨 경우의 수
         dp[j - coin[i]] => 현재 동전(i)을 사용하여 만들 수 있는 경우의 수
         dp[j - coin[i]] 의 경우, 이해가 가지않는다면, 다음과 같은 예를 생각해보자.

         합 j 가 7원이면서 coin[i] 이 3원일 경우를 보자. => dp[7] - dp[7 - 3]

         7원에서 3원을 사용하면 4원이 남는다.

         단순히 4원이 아니라, 4원을 만들 수 있는 경우의 수라고 생각한다.

         이것은 그대로 생각해보면, 3원을 사용해 만들 수 있는 7원의 경우의 수가 된다.

         * dp[0] = 1은 합이 1원의 경우 동전 1원으로 만들 수 있는 경우, 합이 3원일 경우 동전 3원으로 만들 수 있는 경우와 같은 경우를 위함이다.

         * j를 coin[i]로 시작하는 이유는 coin[i]에 해당하는 가치보다 작은 합은 구할 수가 없기 때문이다.

         1원 동전을 기준으로 1원부터 10원까지 경우의 수 누적.

         3원 동전을 기준으로 3원부터 10원까지 경우의 수 누적.

         5원 동전을 기준으로 5원부터 10원까지 경우의 수 누적.

         *
         */

        dp[0] = 1;
        for (int i = 1; i <= N; i++) { //코인을 두개 이상 사용했을 때의 값
            for (int j = coins[i]; j <= K; j++) {
                dp[j] = dp[j-coins[i]] + dp[j];
            }

        }

        System.out.println(dp[K]);

    }
}