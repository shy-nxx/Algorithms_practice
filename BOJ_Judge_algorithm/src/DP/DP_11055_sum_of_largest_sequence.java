package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_11055_sum_of_largest_sequence {
    /**
     * 수열 A가 주어졌을 때, 그 수열의 증가 부분 수열 중에서 합이 가장 큰 것을 구하는 프로그램을 작성하시오.
     * <p>
     * 예를 들어, 수열 A = {1, 100, 2, 50, 60, 3, 5, 6, 7, 8} 인 경우에 합이 가장 큰 증가 부분 수열은 A = {1, 100, 2, 50, 60, 3, 5, 6, 7, 8} 이고, 합은 113이다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] seq = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i =1; i <= N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[1001];

        int sum = 0;

//        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            int temp_sum = 0;
            for (int j = 1; j <= i; j++) {
                if (seq[i] > seq[j]) {
                    if (temp_sum < dp[j]) temp_sum = dp[j];

//                    System.out.println(i + " " + j + " " + dp[j] + " " + temp_sum);
                }
            }
            dp[i] = temp_sum + seq[i];
            sum = Math.max(sum, dp[i]);
        }

        System.out.println(sum);
    }
}