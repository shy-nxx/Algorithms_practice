package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_11726_2ntile {
    /**
     * 2×n 크기의 직사각형을 1×2, 2×1 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.
     *
     * 아래 그림은 2×5 크기의 직사각형을 채운 한 가지 방법의 예이다.
     *
     * 1*2 1*2 2*1 1*2
     *         2*1
     *
     * 가로는 모두 합쳐서 5가 되어야 하고
     * 세로는 모든 조각들이 2를 나타내야 한다. (2*1의 경우 위의 것과 합쳐서 2)
     *
     * 첫째 줄에 2×n 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지를 출력한다.
     *
     * 합쳐서 1이 되는 경우의 수 = 1
     * 합쳐서 2가 되는 경우의 수 = 1+1 / 2 * 1
     *                             2 * 1
     * 합쳐서 3이 되는 경우의 수 = 1+1+1 / 1 +  1 + 2 / 1 + 2 + 1
     *                                    1 + 2 / 1 + 2
     * 합쳐서 4가 되는 경우의 수 = 1+1+1+1 / 2 + 1 2 + 1 / 1+1 2+1 / 2+1 1+1
     * 합쳐서 5가 되는 경우의 수 = 1+1+1+1+1 / 2 + 1 2 + 1 1 / 1 + 1 + 1 2+1 / 2+1 1+1+1 / 1 + 2+1 2+1 / 1+ 2+1 + 1 + 1 / 1 + 1 2+1 + 1
     * 합쳐서 n이 되는 경우의 수 =
     * +
     * 합쳐서 높이가 2가 되는 경우의 수
     *
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] dp = new int[N+1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= N; i++ ) {
            dp[i] = (dp[i-1] + dp[i-2]) % 10007;
        }
        System.out.println(dp[N]);

    }
}