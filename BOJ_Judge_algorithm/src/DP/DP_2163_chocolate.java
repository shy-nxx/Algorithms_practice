package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_2163_chocolate {
    /**
     * 정화는 N×M 크기의 초콜릿을 하나 가지고 있다. 초콜릿은 금이 가 있는 모양을 하고 있으며, 그 금에 의해 N×M개의 조각으로 나눠질 수 있다.
     * <p>
     * 초콜릿의 크기가 너무 크다고 생각한 그녀는 초콜릿을 친구들과 나눠 먹기로 했다. 이를 위해서 정화는 초콜릿을 계속 쪼개서 총 N×M개의 조각으로 쪼개려고 한다. 초콜릿을 쪼갤 때에는 초콜릿 조각을 하나 들고, 적당한 위치에서 초콜릿을 쪼갠다. 초콜릿을 쪼갤 때에는 금이 가 있는 위치에서만 쪼갤 수 있다. 이와 같이 초콜릿을 쪼개면 초콜릿은 두 개의 조각으로 나눠지게 된다. 이제 다시 이 중에서 초콜릿 조각을 하나 들고, 쪼개는 과정을 반복하면 된다.
     * <p>
     * 초콜릿을 쪼개다보면 초콜릿이 녹을 수 있기 때문에, 정화는 가급적이면 초콜릿을 쪼개는 횟수를 최소로 하려 한다. 초콜릿의 크기가 주어졌을 때, 이를 1×1 크기의 초콜릿으로 쪼개기 위한 최소 쪼개기 횟수를 구하는 프로그램을 작성하시오.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //N*M 크기의 초콜렛 -> 1*1로 몇번을 나눠야지 만들어지는가 최소값을 구하라
        //총 조각은 N*M개가 나온다.
        int[] dp = new int[301];

        dp[0] = 0; //한번도 자르지 않으면 1

        /**
         * 한번 자른다고 1*1이 만들어지지 않은 경우가 많다
         * 2*2의 경우ㅎ 한 번 자르면 1*2 1*2 두개가 만들어진다.=> 2번 잘라야지 1*1이 4개 만들어진다. -> 1*1이 만들어지는 경우를 찾아야 함
         *
         * 2*3 / 3*2 -> 3번을 잘라야 함. -> N < M
         */

        int result = M*N -1;
        System.out.println(result);

    }
}