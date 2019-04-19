package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Stickers_DP {
    /**
     * 경우를 나눠야 한다.
     * 내 이전의 max 값을 선택하고 지금 자리의 스티커를 선택하지 않을 때
     * 내 이전의 대각선 max를 선택하고 지금 자리의 스티커를 선택할 때
     * 내 이전전 대각선 max를 선택하고 지금 자리의 스티커를 선택할 때
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken()); //테스트 케이스 개수

        for (int k = 0; k < T; k++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());

            int[][] stickers = new int[2][100010];

            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 2; j <= N+1; j++) {
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dp = new int[2][100010];

            for (int i = 2; i <= N+1; i++) {
                dp[0][i] = Math.max(Math.max(dp[0][i-1], dp[1][i-1]+ stickers[0][i]), dp[1][i-2] + stickers[0][i]);
                dp[1][i] = Math.max(Math.max(dp[1][i-1], dp[0][i-1]+ stickers[1][i]), dp[0][i-2] + stickers[1][i]);

            }

            if (dp[0][N+1] > dp[1][N+1]) System.out.println(dp[0][N+1]);
            else{
                System.out.println(dp[1][N+1]);
            }
        }
    }
}
