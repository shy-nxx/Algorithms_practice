package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_2133_fill_of_tiles {
    /**
     *
     * 3×N 크기의 벽을 2×1, 1×2 크기의 타일로 채우는 경우의 수를 구해보자.
     *
     * 1.  타일이 만들어 질 수 있는 경우는 짝 수 일때 뿐이다.
     * 2.  타일이 추가될 때의 경우를 생각해 보자. 예를 들어
     * -2 개일 때 : 3개이다.
     * -4 개일 때 : 앞이 2개로 만들 수 있는 경우 * 4-2개로 만들 수 있는 경우 + 4개로만 만들 수 있는 경우 * 4-4개로 만들 수 있는 경우
     * -6 개일 때 : 앞이 2개로 만들 수 있는 경우 * 6-2개로 만들 수 있는 경우 + 앞이 4개로만 만들 수 있는 경우 *  6-4개로 만들 수 있는 경우 + 앞이 6개로만 만들 수 있는 경우 * 6-6개로 만들 수 있는 경우
     * 3. 위에 껄로 보면 과거의 것으로 새롭게 만드는 것과 자기 자신의 숫자만큼 유일하게 만들 수 있는 경우를 더하면 되는 것이다.
     * 4. 그렇게 진행하면 마지막에 K에 들어오는 값이 만들 수 있는 타일의 수가 된다.
     *
     *
     * 좀 더 연구해봐야함
     **/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] dp = new int[31];
        int[] result = new int[31];

        dp[0] = 1;
        dp[2] = 3;
        result[0] = 1;
        for (int i = 4; i <= 30; i += 2)
            dp[i] = 2;

        for (int i = 2; i <= N; i+=2) {
            for (int j = 2; j <= i; j+=2)
                result[i] += result[i-j] * dp[j];
        }
        System.out.println(result[N]);
    }

}
