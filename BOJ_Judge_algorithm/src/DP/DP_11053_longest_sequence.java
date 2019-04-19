package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_11053_longest_sequence {
    /**
     * 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
     * <p>
     * 예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //정수의 개수

        int[] sequ = new int[1001];


        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {

            sequ[i] = Integer.parseInt(st.nextToken());


        }

        /**
         * 현재의 숫자보다 작은 값을 탐색한다.
         * 과거의 배열을 돌아보면서 자신보다 작은 수 만큼 값을 +1을 해준다.
         *
         *
         *
         *
         *
         */
        int[] dp = new int[1001];

        int max = 0;

        for (int i = 1; i <= N; i++) {
            int min = 0;
            for (int j = 0; j <= i; j++) {
                if (sequ[i] > sequ[j]) {
                    if (min < dp[j]) min = dp[j];
                }
            }
            dp[i] = min + 1;

            if (max < dp[i]) max = dp[i];

        }

        System.out.println(max);

    }
}