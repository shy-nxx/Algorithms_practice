package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_1912_continuousSum {
    /**
     * n개의 정수로 이루어진 임의의 수열이 주어진다. 우리는 이 중 연속된 몇 개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합을 구하려고 한다. 단, 수는 한 개 이상 선택해야 한다.
     * <p>
     * 예를 들어서 10, -4, 3, 1, 5, 6, -35, 12, 21, -1 이라는 수열이 주어졌다고 하자. 여기서 정답은 12+21인 33이 정답이 된다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //정수의 개수

        int[] nums = new int[N+10];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++ ) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N+10];
        dp[1] = nums[1];

        int max = nums[1];

        /**
         *
         * 현재 값과 이전 값들의 합을 합쳐서 max값 보다 큰 경우
         *
         * 만약 max값보다 작아지는 경우는 이전의 인덱스를 지우고 다시 시작해야한다. (연속값이기 때문에 인덱스가 연결되어있어야하는데)
         * dp[i-2] + num[i-1] + nums[i] / dp[i-1] + nums[i] /
         *
         *
         */
        for (int i = 2; i <= N; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            if (max < dp[i]) max = dp[i];
        }
        System.out.println(max);


    }
}