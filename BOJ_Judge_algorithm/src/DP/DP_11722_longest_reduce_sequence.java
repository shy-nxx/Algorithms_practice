package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_11722_longest_reduce_sequence {
    /**
     * 수열 A가 주어졌을 때, 가장 긴 감소하는 부분 수열을 구하는 프로그램을 작성하시오.
     * <p>
     * 예를 들어, 수열 A = {10, 30, 10, 20, 20, 10} 인 경우에 가장 긴 감소하는 부분 수열은 A = {10, 30, 10, 20, 20, 10}  이고, 길이는 3이다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] seq = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());

        }

        int[] dp = new int[1001];
        dp[0] = 0;
        int result = 0;

        for (int i = 2; i <= N; i++) {
            int min = 0;
            for (int j = 1; j <= i; j++) {
                if (seq[i] < seq[j]) {
                    if (min < dp[j]) {
                        min = dp[j];
                    }
                }
            }
            dp[i] = min + 1;
            result= Math.max(result, dp[i]);

        }
        System.out.println(result);
    }
}