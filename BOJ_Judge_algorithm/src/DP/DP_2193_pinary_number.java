package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_2193_pinary_number {
    /**
     * 0과 1로만 이루어진 수를 이진수라 한다. 이러한 이진수 중 특별한 성질을 갖는 것들이 있는데, 이들을 이친수(pinary number)라 한다. 이친수는 다음의 성질을 만족한다.
     *
     * 이친수는 0으로 시작하지 않는다.
     * 이친수에서는 1이 두 번 연속으로 나타나지 않는다. 즉, 11을 부분 문자열로 갖지 않는다.
     * 예를 들면 1, 10, 100, 101, 1000, 1001 등이 이친수가 된다. 하지만 0010101이나 101101은 각각 1, 2번 규칙에 위배되므로 이친수가 아니다.
     *
     * N(1 ≤ N ≤ 90)이 주어졌을 때, N자리 이친수의 개수를 구하는 프로그램을 작성하시오.
     */
    static final int T = 1;
    static final int F = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //N자리의 이친수를 구하라 101 100

        /**
         * 1. 이 문제는 앞에 첫 자리 숫자가 1이 꼭 나와야 한다. 하지만 여기서 0이 앞으로 나오는 경우도 카운트 해줘야 한다.
         * 2. 그렇기에 현재 1이 앞으로 나오는 경우에 이전 자릿수에 앞에 0이 나오는 경우의 수랑 같다.
         * 3. 현재 0이 앞으로 나오는 경우는 이전  자릿수에 앞에 0, 1 이 나오는 경우의 합과 같다.
         * 4. 즉 이 규칙을 이용하여서 진행하면 DP[N][1] 에 원하는 답을 구 할 수 있다.
         *
         ** 1010 1000 1001
         * 10001 10010 10101 10000 10100
         */
        long dp[][] = new long[100][2];
        dp[1][0] = 1;
        dp[1][1] = 1;

        for (int i = 2; i <= N; i++) {
            //이전 자리수의 값이 1이면 0 / 0이면 1 또는 0
            //현재 0이 앞으로 나오는 경우에는 이전에 0, 1이 나오는 경우의 합과 같다
            //현재 1이 앞으로 나오는 경우에는 앞에 0이 나오는 경우의 수와 같다

            dp[i][0] = dp[i-1][0]+ dp[i-1][1];
            dp[i][1] = dp[i-1][0];
        }

        System.out.println(dp[N][1]);

    }
}
