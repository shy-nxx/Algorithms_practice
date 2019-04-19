package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Longest_sequence_DP {
    /**
     * 풀이 : 수열로 이루어진 배열이 주어진다. 현재의 원소의 앞에 위치한 원소보다 자신이 크다면 큰 수만큼 DP 배열의 값을 저장한다.
     * 개중 최대값을 뽑으면 가장 긴 수열의 길이다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //정수의 개수

        int[] nums = new int[1001];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[1001];

        int max = 0;
        for (int i = 1; i <= N; i++) {
            int past_max = 0;
            for (int j = 0; j < i; j++) { //과거의 배열
                if (nums[i] > nums[j]) {
                    if (past_max < dp[j]) past_max = dp[j];
                }
            }
            dp[i] = past_max + 1;

            if (max < dp[i]) max = dp[i];

        }

        System.out.println(max);

    }
}