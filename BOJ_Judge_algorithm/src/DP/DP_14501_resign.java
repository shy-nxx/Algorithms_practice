package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_14501_resign {
    /**
     * 오늘부터 N+1일째 되는 날 퇴사를 하기 위해서, 남은 N일 동안 최대한 많은 상담을 하려고 한다.
     * 각각의 상담은 상담을 완료하는데 걸리는 기간 Ti와 상담을 했을 때 받을 수 있는 금액 Pi로 이루어져 있다.
     * <p>
     * 1일에 잡혀있는 상담은 총 3일이 걸리며, 상담했을 때 받을 수 있는 금액은 10이다. 5일에 잡혀있는 상담은 총 2일이 걸리며, 받을 수 있는 금액은 15이다.
     * <p>
     * 1일	2일	3일	4일	5일	6일	7일
     * Ti	3	5	1	1	2	4	2
     * Pi	10	20	10	20	15	40	200
     * <p>
     * 상담을 하는데 필요한 기간은 1일보다 클 수 있기 때문에, 모든 상담을 할 수는 없다. 예를 들어서 1일에 상담을 하게 되면, 2일, 3일에 있는 상담은 할 수 없게 된다. 2일에 있는 상담을 하게 되면, 3, 4, 5, 6일에 잡혀있는 상담은 할 수 없다.
     * <p>
     * 또한, N+1일째에는 회사에 없기 때문에, 6, 7일에 있는 상담을 할 수 없다.
     * <p>
     * 퇴사 전에 할 수 있는 상담의 최대 이익은 1일, 4일, 5일에 있는 상담을 하는 것이며, 이때의 이익은 10+20+15=45이다.
     * <p>
     * 상담을 적절히 했을 때, 백준이가 얻을 수 있는 최대 수익을 구하는 프로그램을 작성하시오.
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

        int max = 0;

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                if (i - j >= t[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + p[i]);
                }
            }
        }
        System.out.println(dp[N]);

        for (int i = 1; i <= N; i++) {
            if (i + t[i] <= N + 1) {
                max = Math.max(max, dp[i]);
            }
        }

        System.out.println(max);
    }
}