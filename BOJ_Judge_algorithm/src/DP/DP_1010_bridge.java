package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class DP_1010_bridge {
    /**
     * 재원이는 한 도시의 시장이 되었다. 이 도시에는 도시를 동쪽과 서쪽으로 나누는 큰 강이 흐르고 있다. 하지만 재원이는 다리가 없어서 시민들이 강을 건너는데 큰 불편을 겪고 있음을 알고 다리를 짓기로 결심하였다. 강 주변에서 다리를 짓기에 적합한 곳을 사이트라고 한다. 재원이는 강 주변을 면밀히 조사해 본 결과 강의 서쪽에는 N개의 사이트가 있고 동쪽에는 M개의 사이트가 있다는 것을 알았다. (N ≤ M)
     * <p>
     * 재원이는 서쪽의 사이트와 동쪽의 사이트를 다리로 연결하려고 한다. (이때 한 사이트에는 최대 한 개의 다리만 연결될 수 있다.) 재원이는 다리를 최대한 많이 지으려고 하기 때문에 서쪽의 사이트 개수만큼 (N개) 다리를 지으려고 한다. 다리끼리는 서로 겹쳐질 수 없다고 할 때 다리를 지을 수 있는 경우의 수를 구하는 프로그램을 작성하라.
     */

    static final int MAX = 31;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken()); //테스트 케이스 개수

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); //서쪽 사이트
            int M = Integer.parseInt(st.nextToken()); //동쪽 사이트

//            System.out.println(combination(N, M)); //Method 1
            /**
             * N-M을 1대 1 매칭하는 경우의 수
             * nCr => 조합을 이용한 방법 (BigInteger사용 )
             *
             * DP로 조합 구하기
             * 다리는 크로스가 되지 않는다.
             * -> N < M
             * N의 가장 위에 점이 M의 가장 위의 점에다 다리를 놓게 된다면
             * dp[N-1][M-1]의 경우의 수를 얻을 수 있다.
             * 거기에다 M에 하나의 점이 추가되기 전인 dp[N][M-1]의 경우의 수를 더해주면 점화식을 완성할 수 있다.
             *
             **/

            int[][] dp = new int[N+1][M+1];

            if (N == M) {
                System.out.println(1);
                continue;
            }

            if (N == 1) {
                System.out.println(M);
                continue;
            }

            for (int j = 1; j <= M; j++) { //다리 놓을 곳이 한 곳일 경우 모든 경우의 수를 찾아나간다.
                dp[1][j] = j;
            }
            for (int j = 2; j <= N; j++) { //다리 놓을 포인트가 2곳 이상일 경우
                for (int k = j; k <= M; k++) {
                    dp[j][k] = dp[j][k-1] + dp[j-1][k-1];
                }
            }

            System.out.println(dp[N][M]);
        }
    }
    //method 1
    static BigInteger combination(int r, int n) {
        BigInteger sum = new BigInteger("1");
        int temp = r;

        while(r > 0) {
            sum = sum.multiply(BigInteger.valueOf(n));
            --r;
            --n;
        }

        while (temp > 0) {
            sum = sum.divide(BigInteger.valueOf(temp));
            --temp;

        }
        return sum;
    }
}