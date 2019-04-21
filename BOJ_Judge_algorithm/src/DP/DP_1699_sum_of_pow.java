package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_1699_sum_of_pow {
    /**
     * 어떤 자연수 N은 그보다 작거나 같은 제곱수들의 합으로 나타낼 수 있다. 예를 들어 11=3^2+1^2+1^2(3개 항)이다.
     * 이런 표현방법은 여러 가지가 될 수 있는데, 11의 경우 11=2^2+2^2+1^2+1^2+1^2(5개 항)도 가능하다.
     * 이 경우, 수학자 숌크라테스는 “11은 3개 항의 제곱수 합으로 표현할 수 있다.”라고 말한다.
     * 또한 11은 그보다 적은 항의 제곱수 합으로 표현할 수 없으므로, 11을 그 합으로써 표현할 수 있는 제곱수 항의 최소 개수는 3이다.
     * <p>
     * 주어진 자연수 N을 이렇게 제곱수들의 합으로 표현할 때에 그 항의 최소개수를 구하는 프로그램을 작성하시오.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //제곱수들의 최소 개수

        int[] dp = new int[100001];

        /**
         * 1이 되기 위한 제곱수 > 1
         * 2가 되기 위한 제곱수 > 1+1 -> 2
         * 3이 되기 위한 제곱수 > 1 + 1 + 1 -> 3
         *
         * 1. 이 문제는 제곱수를 만드는 건데 현재의 제곱수의 최소값을 위해서 이전의 최소값을 이용하여서 최소값을 만든다.
         * 2. 검색의 범위는 제곱수기 때문에 1 부터 j * j <=i 까지로 충분하다. 그러므로 검색시간을 줄일 수 있다.
         * 3. 즉 Dp[i] = Dp[i - j*j] + 1 라는 식이 나오는데 이 식은 j*j 수의 제곱수 값 1 + 이전에 값 Dp[i- j*j] 값으로 구 할수 있다.
         * 4. 하지만 j의 최대값만으로 하면 예외 조건이 생길 수 수 있으므로 1~ j의 최대값까지 전부 비교 해야한다. 그 예외 조건의 예시는 12가 있다.
         * - 12 = 3*3 + 1*1 + 1*1 + 1*1 = 4
         * - 12 = 2*2 + 2*2 + 2*2 = 3
         *
         */
//        dp[1] = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j*j < i; j++) {
                    dp[i] = dp[i - j*j] + 1;
            }

        }
        System.out.println(dp[N]);
    }

}