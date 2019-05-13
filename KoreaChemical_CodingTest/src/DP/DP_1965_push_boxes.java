package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DP_1965_push_boxes {
    /**
     * 정육면체 모양의 상자가 일렬로 늘어서 있다. 상자마다 크기가 주어져 있는데, 앞에 있는 상자의 크기가 뒤에 있는 상자의 크기보다 작으면, 앞에 있는 상자를 뒤에 있는 상자 안에 넣을 수가 있다. 예를 들어 앞에서부터 순서대로 크기가 (1, 5, 2, 3, 7)인 5개의 상자가 있다면, 크기 1인 상자를 크기 5인 상자에 넣고, 다시 이 상자를 크기 7인 상자 안에 넣을 수 있다. 하지만 이렇게 상자를 넣을 수 있는 방법은 여러 가지가 있을 수 있다. 앞의 예에서 차례대로 크기가 1, 2, 3, 7인 상자를 선택하면 총 4개의 상자가 한 개의 상자에 들어가게 된다.
     * <p>
     * 상자의 크기가 주어질 때, 한 번에 넣을 수 있는 최대의 상자 개수를 출력하는 프로그램을 작성하시오.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] box_size = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            box_size[i] = Integer.parseInt(st.nextToken());
        }

//        Arrays.sort(box_size);

//        for (int i = 0; i < N; i++) {
//            System.out.println(box_size[i]);
//        }

        int[] dp = new int[N+1];

        int max = 0;

        for (int i = 1; i <= N; i++) {
            dp[i] = 1;
            int min = 0;

            for (int j = 0; j < i; j++) {
                if (box_size[i] > box_size[j]) {
                    if (min < dp[j]) {
                        min = dp[j];
                    }
                    dp[i] = min + 1; //i번째 수까지 증가하는 수의 개수 = d 배열 중 가장 큰 값을 골라서 +1을 해준다.
                }
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}