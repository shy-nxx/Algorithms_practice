package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Resign_DP {
    /**
     * 현재 날짜 + 소요일  = dp[i + t[i]] -> 벌 수 있는 비용을 저장해둔다.
     * max = max와 dp[i]를 비교해서 가능한 날짜 중 최대 비용을 가져온다.
     *
     * dp[5] = Max(5일의 이익 + 1일까지의 이익, 5일까지의 이익)
     *
     * dp[5] = Max(5일의 이익 + 2일까지의 이익, 5일까지의 이익)
     *
     * dp[5] = Max(5일의 이익 + 3일까지의 이익, 5일까지의 이익)
     *
     * dp[5] = Max(5일의 이익 + 4일까지의 이익, 5일까지의 이익)
     *
     *
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] t = new int[N+1];
        int[] p = new int[N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[1001];

        int max = 0;

//        for (int i = 0; i < N; i++) {
//            dp[i] = Math.max(max, dp[i]);
//            dp[i + t[i]] = Math.max(dp[i + t[i]], dp[i] + p[i]);
//            max = Math.max(dp[i] , max);
//
//            System.out.println(dp[i] + " " + max + " " +   dp[i + t[i]]);
//
//        }

        for (int i = 1; i <= N; i++) {
            dp[i] = p[i];
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j < i; j++ ) {
                if (i-j >= t[i]) {
                    System.out.println( i + " " + j + " " + t[i] + " " + p[i]);
                    dp[i] = Math.max(dp[i], dp[j] + p[i]);
                }
            }

        }

        for (int i = 1; i <= N; i++ ) {
            if (i + t[i] <= N+1) {
                max = Math.max(max, dp[i]);
            }
        }
        System.out.println(max);
    }
}