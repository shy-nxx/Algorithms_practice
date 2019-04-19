package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_2156_wine {
    /**
     * 효주는 포도주 시식을 하려고 하는데, 여기에는 다음과 같은 두 가지 규칙이 있다.
     * <p>
     * 포도주 잔을 선택하면 그 잔에 들어있는 포도주는 모두 마셔야 하고, 마신 후에는 원래 위치에 다시 놓아야 한다.
     * 연속으로 놓여 있는 3잔을 모두 마실 수는 없다.
     * 효주는 될 수 있는 대로 많은 양의 포도주를 맛보기 위해서 어떤 포도주 잔을 선택해야 할지 고민하고 있다. 1부터 n까지의 번호가 붙어 있는 n개의 포도주 잔이 순서대로 테이블 위에 놓여 있고, 각 포도주 잔에 들어있는 포도주의 양이 주어졌을 때, 효주를 도와 가장 많은 양의 포도주를 마실 수 있도록 하는 프로그램을 작성하시오.
     * <p>
     * 예를 들어 6개의 포도주 잔이 있고, 각각의 잔에 순서대로 6, 10, 13, 9, 8, 1 만큼의 포도주가 들어 있을 때, 첫 번째, 두 번째, 네 번째, 다섯 번째 포도주 잔을 선택하면 총 포도주 양이 33으로 최대로 마실 수 있다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //포도주 잔 개수

        int[] wines = new int[10010];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            wines[i] = Integer.parseInt(st.nextToken());
        }

        /**
         *
         * * 연속으로 3잔을 모두 고를 수 없다는 것을 고려해야한다.
         *
         * 1. n-1 잔을 마시지 않은 경우. => n-2 잔을 고를 수 있음.
         *
         * 2. n-1 잔을 마신 경우. => n-2 잔을 고르면 연속 3잔이 되기 때문에 고를 수 없음.
         *
         * 1. dp[n] = dp[n-2] + array[n]
         *
         * 2. dp[n] = dp[n-3] + array[n-1] + array[n]
         *
         * dp[n] = max(dp[n-2] + array[n], dp[n-3] + array[n-1] + array[n])
         *
         * 하지만 , 포도주를 2번 연속 안 먹을 경우가 존재한다.
         *
         * dp[n] = max(dp[n-1], dp[n])
         *
         * 그렇기에 위와 같이 하나의 점화식이 더 필요하게 된다.
         *
         * dp 배열을 채워가는 과정에서 이전 값과 현재 값을 비교해주면 해결된다.
         *
         * 계단오르기와 비슷한 방식으로 해결 (연속 세개는 안되니까 현재 잔의 이전잔을 마신 경우 / 이전 잔을 마시지 않은 경우로 나눠서 생각한다.
         * -현재 잔의 이전 잔을 마신 경우 : 전전 잔을 마시면 안됨 -> [i-3] + i-1 + i
         * -현제 잔의 이전 잔을 마시지 않은 경우 : 전전잔을 마셔야 함 -> [i-2] + i
         *
         * 2. 즉 현재 먹는 포도주의 합이 최대일 경우를 생각해보면
         * - 내가 현재의 포도주를 먹지 않았을 경우와
         * - 현재의 포도주를 마시고 이전꺼를 안마실 경우
         * - 현재의 포도주와 이전의 포도주를 마신 경우
         * 3. 이렇게 3개의 경우를 따져서 저장해 주면 된다. 즉
         * - Dp[i-1] : 내가 현재의 포도주를 먹지 않았을 경우와
         * - wine[i] + Dp[i-2] : 현재의 포도주를 마시고 이전꺼를 안마실 경우
         * - wine[i] + wine[i-1] + Dp[i-3] :현재의 포도주와 이전의 포도주를 마신 경우
         *
         */


        int[] dp = new int[10010];
        dp[0]= 0;

        //연속으로 먹지 않을 경우를 고려해사 계속 이전 dp값과 비교해야한다.

        for (int i = 1; i < 3; i++) {
            if (i == 1)
                dp[i] = wines[i];

            else
                dp[i] = dp[i-1] + wines[i];

//            if (i != 3) {
//
//                dp[i] = dp[i-1] + wines[i];
//            }
//            else {
//                dp[i] = Math.max(dp[i-2]+wines[i], wines[i] + wines[i-1]);
//            }

        }

        for (int i = 3; i <= N; i++) {
            int result = 0;
            result = Math.max(dp[i-1], dp[i-2] + wines[i]);

            result = Math.max(result, dp[i-3] + wines[i-1] + wines[i]);

            dp[i] = result;
        }

        System.out.println(dp[N]);
    }

}
