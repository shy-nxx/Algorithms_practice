package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_11051_binomial_coefficient {
    /**
     * 자연수 N과 정수K 가 주어졌을 때 이항 계수
     * (n k)를 10,007로 나눈 나머지를 구하는 프로그램을 작성하시오.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int result = fact(N) / (fact(K) * fact(N-K)) % 10007;


        System.out.println(result % 10007);
    }
    static int fact(int n){
        int res, i;

        if (n == 1)
            return n;
        else{
            res = n;
            for (i = n - 1; i > 0; i--)
                res *= i;
            return res;
        }
    }
}