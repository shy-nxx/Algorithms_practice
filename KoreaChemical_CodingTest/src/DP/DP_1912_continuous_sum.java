package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_1912_continuous_sum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] nums = new int[N + 10];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        /**
         *  10, -4, 3, 1, 5, 6, -35, 12, 21, -1
         *
         *  더했을 때 이전보다 작으면 max는 이전 값이 최대
         *
         */

        int[] dp = new int[N + 10];

        dp[1] = nums[1];
        int max=  nums[1];

        for (int i = 2; i <= N; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);

        }
        System.out.println(dp[N-1]);
    }
}