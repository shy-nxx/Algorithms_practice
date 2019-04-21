package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Resign_re_DP {
    /**
     * 첫째날은 반드시 상담 날에 포함되어야 하기 때문에 for문을 두번 돌면서 날짜가 소요날짜보다 클 때 dp[i]와 dp[j] + p[i] 중 큰 값ㅇ릊 ㅓ장한다.
     * <p>
     * 첫째 날부터 마지막 날까지 돌면서 소요 날짜가 N을 넘지 않을 경우 max값을 갱신해준다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //퇴사까지 남은 일수

        int[] t = new int[N+1];
        int[] p = new int[N+1];
        int[] dp = new int[1001];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
            dp[i] = p[i];
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j< i; j++) {
                if (i - j >= t[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + p[i]);
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (i + t[i] <= N+1) {
                max = Math.max(max, dp[i]);
            }
        }

        System.out.println(max);
    }
}