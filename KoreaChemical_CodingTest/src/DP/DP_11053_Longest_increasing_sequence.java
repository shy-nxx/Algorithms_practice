package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_11053_Longest_increasing_sequence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        /**
         * 자신보다 작은 수를 탐색한다. (탐색된 수만큼 count를 늘려서 저장)
         *
         */

        int[] seq = new int[1001];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[1001];

        int max = 0;

        for (int i= 1; i <= N; i++) {
            int min = 0;
            for (int j = 0; j <= i; j++) {
                if (seq[i] > seq[j]) {
                    System.out.println(dp[j]);
                    if (min < dp[j]) {
                        min = dp[j];
                    }
                }
            }
            dp[i] = min + 1;
            max = Math.max(dp[i], max);
        }
        System.out.println(max);
    }
}