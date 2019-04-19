package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Make_one_DP {
    /**
     * X가 3으로 나누어 떨어지면, 3으로 나눈다.
     * X가 2로 나누어 떨어지면, 2로 나눈다.
     * 1을 뺀다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int count = 0;
        int temp = 0;

        while(N > 1) {
            if (N % 3 == 0)  {
                temp = N / 3;
                count++;
            }
            if (N % 2 == 0) {
                temp = N / 2;
                count++;
            }
            temp--;
            count++;
        }


    }
}
