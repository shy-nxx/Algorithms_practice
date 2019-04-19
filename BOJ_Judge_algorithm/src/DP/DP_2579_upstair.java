package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_2579_upstair {
    /**
     * 계단 오르는 데는 다음과 같은 규칙이 있다.
     * <p>
     * 계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
     * 연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
     * 마지막 도착 계단은 반드시 밟아야 한다.
     * 따라서 첫 번째 계단을 밟고 이어 두 번째 계단이나, 세 번째 계단으로 오를 수 있다. 하지만, 첫 번째 계단을 밟고 이어 네 번째 계단으로 올라가거나, 첫 번째, 두 번째, 세 번째 계단을 연속해서 모두 밟을 수는 없다.
     * <p>
     * 각 계단에 쓰여 있는 점수가 주어질 때 이 게임에서 얻을 수 있는 총 점수의 최댓값을 구하는 프로그램을 작성하시오.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken()); //계단의 개수

        int[] upstairs = new int[T + 1];

        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            upstairs[i] = Integer.parseInt(st.nextToken()); //각 계단들의 점수를 입력받는다.

        }

        //계단은 1 or 2계단씩 오를 수 있고 연속 3계단은 안됨
        /**
         * 풀이
         * dp[i] = Math.max(dp[i] + dp[i + 1] , dp[i] + dp[i + 2]
         *
         * 그렇다면, 마지막 계단을 밟았을 경우, 이전의 경우를 2가지로 생각할 수 있다.
         *
         * 1. 마지막 계단 전의 계단을 밟은 경우.
         *
         * 2. 마지막 계단 전의 계단을 밟지 않은 경우.
         *
         * 1번의 경우에는 마지막 계단 전의 계단을 밟았음으로, 마지막 계단 전전의 계단은 밟지 못한다.
         *
         * 2번의 경우에는 마지막 계단 전의 계단을 밟지 않았음으로, 마지막 계단 전전의 계단을 밟고 왔다.
         *
         * 1. dp[n] = dp[n-3] + array[n-1] + array[n]
         *
         * 2. dp[n] = dp[n-2] + array[n]
         *
         * dp 배열은 합을 구하는 배열이고, array 배열은 계단의 값이다.
         *
         * 2개의 배열을 이용하는 이유는 dp 배열 하나만 쓸 경우에는 dp[i]의 연속 여부를 확인하기 힘들기 때문이다.
         *
         *
         *
         * 문제는 답은 최대값을 찾는 것이기 때문에 위 점화식 2개를 비교하면서 큰 값을 넣으면 된다.
         *
         *
         *
         * dp[n] = max(dp[n-3] + array[n-1] + array[n], dp[n-2] + array[n]
         *
         *
         */

        int dp[] = new int[301];

        dp[0] = 0;

        for (int i = 1; i <= 3; i++)
            if (i != 3)
                dp[i] = dp[i - 1] + upstairs[i];
            else
                dp[i] = Math.max(upstairs[i] + dp[i - 2], upstairs[i] + upstairs[i - 1]);

        for (int i = 4; i <= T; i++ ) { //마지막 계단은 반드시 밟아야 한다.

            dp[i] = Math.max(dp[i-3] + upstairs[i-1] + upstairs[i], dp[i-2] + upstairs[i]);
//            System.out.println(dp[i]);

        }
        System.out.println(dp[T]);
    }
}