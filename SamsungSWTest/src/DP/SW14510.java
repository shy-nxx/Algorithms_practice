package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW14510 {

    /**
     * dp[i] = Max(p[i] + dp[j], dp[i]) // i - 기준일, j - (1 ~ i-1)일
     *
     *
     *
     * dp[5] = Max(5일의 이익 + 1일까지의 이익, 5일까지의 이익)
     *
     * dp[5] = Max(5일의 이익 + 2일까지의 이익, 5일까지의 이익)
     *
     * dp[5] = Max(5일의 이익 + 3일까지의 이익, 5일까지의 이익)
     *
     * dp[5] = Max(5일의 이익 + 4일까지의 이익, 5일까지의 이익)
     *
     *
     */
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 퇴사전까지의 기간

        //N+10 을 해준 이유는 마지막날 + 5일일 때 배열 에러가 날 수 있으므로 넉넉히 잡아준다.
        int[] t = new int[N+10];
        int[] p = new int[N+10];
        int[] dp = new int[N+10];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
//            dp[i] = p[i];
        }

//        // dp[n] = n일때까지 얻은 수익
//        for (int i = 2; i <= N; i++) {
//            for (int j = 1; j < i; j++) {
//                dp[j] = Math.max(p[i] + dp[j], dp[i]);
//                if (i - j >= t[j]) { //퇴사날 전까지 끝낼 수 있는 상담일 경우
//
//                }
//            }
//        }
//        int max = 0;
//
//        for (int i = 1; i <= N; i++) {
//            if (i + t[i] <= N + 1) {
//                if (max < dp[i]) {
//                    max = dp[i];
//                }
//            }
//        }


        int max = 0;

        for (int i = 1; i <=N+1; i++) {
            //이전까지의 최대 수입을 비교해서 최대 수입을 현재에도 저장해준다.
            //이전에 최대수입이 났을 수 있으므로
            //ex) 3,7,(5 예상) 이라고 하면 5의 값은 7로 바꿔주는게 최대수입을 얻는데 맞다.
            dp[i] = Math.max(dp[i], max);
            //이전에 저장된 최대수익 vs 이번 움직임으로 생긴 최대 수익
//            System.out.println(t[i] + i);
            dp[t[i]+i] = Math.max(dp[t[i]+i],p[i]+dp[i]);
            //출력될 최대 수입
            max = Math.max(max, dp[i]);

        }
        System.out.println(max);

    }

//    static void solution(TP[] timePay) {
//        int sum = 0;
//
//        int i = 0;
//        while(i < timePay.length) {
//            int j = i;
//            while(j < timePay.length) {
//                System.out.println(j + " " + (j + timePay[j].day));
//
//                if (j + timePay[j].day < timePay.length) {
//                    sum += timePay[j].pay;
//                    j += timePay[j].day;
//                }
//                MAX = Math.max(MAX, sum);
//                j++;
//            }
////            if (i + timePay[i].day < timePay.length) {
////                sum += timePay[i].pay;
////                i += timePay[i].day;
////            }
//            i++;
//
//        }
//        System.out.println(MAX);
//    }

}
