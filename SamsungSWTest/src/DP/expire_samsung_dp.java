package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class expire_samsung_dp {
    /**
     * 입력값
     *
     * 7
     * 3 10
     * 5 20
     * 1 10
     * 1 20
     * 2 15
     * 4 40
     * 2 200
     *
     * 출력
     * 45
     */
    static int N;
    static int MAX = 0;
    static int[] dp;

    static int[] time;
    static int[] pay;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //퇴사까지 남은 날짜

        time = new int[N+10];
        pay = new int[N+10];

        dp = new int[N+10];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            pay[i] = Integer.parseInt(st.nextToken());

        }

        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i], MAX);
            dp[time[i] + i] = Math.max(dp[time[i] + i], pay[i] + dp[i]);
            MAX = Math.max(MAX, dp[i]);
        }


        System.out.println(MAX);


    }

    public static class TP {
        int time;
        int pay;

        public TP (int time, int pay) {
            this.time = time;
            this.pay = pay;

        }

    }

}
