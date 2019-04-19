package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Plus123_DP {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken()); //테스트 케이스 개수

        /**
         * 푸는 방법 : n = 4 이라면 4-1 : f(3) -> f(n) += f(n-1)
         * 4-2 : f(2) -> f(n) += f(n-2)
         * 4-3 : f(3) -> f(n) += f(n-3)
         * 모두 0보다 클때만 고려한다!!
         */
        for (int j = 0; j < T; j++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            int dp[] = new int[15];

            dp[0] = 1; //0이 되기 위한 합은 없으므로 0

            for (int i = 1; i <= k; i++) {
                if (i - 1 >= 0) {
                    dp[i] += dp[i-1];
                }
                if (i - 2 >= 0) {
                    dp[i] += dp[i-2];
                }
                if (i - 3 >= 0) {
                    dp[i] += dp[i-3];
                }
            }
            System.out.println(dp[k]);
        }

    }
}
