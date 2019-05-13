package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_9095_plus123 {
    /**
     * 정수 4를 1, 2, 3의 합으로 나타내는 방법은 총 7가지가 있다. 합을 나타낼 때는 수를 1개 이상 사용해야 한다.
     * <p>
     * 1+1+1+1
     * 1+1+2
     * 1+2+1
     * 2+1+1
     * 2+2
     * 1+3
     * 3+1
     * 정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램을 작성하시오.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken()); //테스트 케이스 개수


        /**
         * 1, 2, 3만 사용해서 만드는 방법 + 1,2를 사용해서 만드는 방법 + 1,3을 사용해서 만드는 방법 + 2,3을 사용해서 만드는 방법 + 1,2,3을 사용해서 만드는 방법?
         * 1만을 사용해서 만들고 나서 한 원소씩 더함 -> 2,1,1 / 1,2,1 / 1,1,2 -> 2,2 -> 3,1 / 1,3 -> 중복제거를 어떻게 할 것인가...
         *
         *  1. 이 문제 같은경우는 1,2,3 만 고려해줘야하기 때문에 푸는 방법이 어렵지 않다.
         *  2. 각 숫자에 대해서 어떤 경우의 수가 있을지만 생각하면 된다.
         *  3. 간단하게 앞자리 수에 1, 2, 3 이 나올 경우를 생각해 보면 된다.
         *  즉 예를 들면 숫자 4 같은경우는 앞에 1이 추가됬을때 1  -> 4-1 인 3을 만드는 모든 경우의  수가 되고 2가 추가된다면 -> 4-2 인 2를 만드는 모든 경우의 수를 추가하고
         *  3을 앞에 추가하면 3-1인 1을 만드는 모든 경우의 수를 추가하면 된다.
         *  4. 즉 Dp[i] += Dp[i-1]  , Dp[i] += Dp[i-2] , Dp[i] += Dp[i-3] 의 값을 꾸준히 더해주면서 구하면되는데 계산을 쉽게 하기위해 Dp[0]의 경우를 1로 채워 넣는다.
         *
         *
         */


        for (int i =0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int[] dp = new int[15];
            dp[0] =1;
            int k = Integer.parseInt(st.nextToken());

            for (int j = 1; j <= k; j++ ) {
                if (j - 1 >= 0) {
                    dp[j] += dp[j-1];
                }
                if (j - 2 >= 0) {
                    dp[j] += dp[j-2];
                }
                if (j - 3 >= 0) {
                    dp[j] += dp[j-3];
                }
            }
            System.out.println(dp[k]);
        }




    }
}