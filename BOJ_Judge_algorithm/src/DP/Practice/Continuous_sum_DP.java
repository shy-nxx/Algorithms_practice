package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Continuous_sum_DP {
    /**
     *
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //정수의 개수

        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N+1];

        dp[0] = nums[0];
        int max = nums[0];

        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            if (max < dp[i]) max = dp[i];
        }

        System.out.println(max);

    }
}
