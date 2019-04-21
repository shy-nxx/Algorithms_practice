package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_9251_LCS {
    /**
     * LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때, 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.
     * <p>
     * 예를 들어, ACAYKP와 CAPCAK의 LCS는 ACAK가 된다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        String f = br.readLine();
        String s = br.readLine();

        char[] first = new char[f.length()];
        char[] second = new char[s.length()];

        for (int i = 0; i < f.length(); i++) {
            first[i] = f.charAt(i);
            second[i] = s.charAt(i);
        }
//
//        for (int i = 0; i < f.length(); i++) {
//            System.out.println(first[i]);
//            System.out.println(second[i]);
//        }

        int[][] dp = new int[1001][1001];

        int result = 0;

        dp[0][0] = 0;
        for (int i = 1; i <= first.length; i++) {
            for (int j = 1; j <= second.length; j++) {
                if (first[i-1] == second[j-1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
//                    System.out.println(dp[i][j]);
                }
                else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j] );
                }
                result = Math.max(result, dp[i][j]);
            }
        }

        System.out.println(result);

    }
}