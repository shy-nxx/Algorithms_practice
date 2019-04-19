package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW13458 {
    /**
     * 시험감독
     * 총 감독관은 한 명, 부감독은 여러명 가능
     * 감독관의 최소 수를 구하라
     *
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 시험장의 개수

        int[] applies = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i =0 ; i < N; i++) {
            applies[i] = Integer.parseInt(st.nextToken()); //응시생 수
        }

        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken()); //총감독관이 감독할 수 있는 학생의 수

        int C = Integer.parseInt(st.nextToken()); //부감독관이 감독할 수 있는 학생의 수

        solution(N, applies, B, C);
    }

    static void solution(int N, int[] applies, int B, int C) {
        int total = 0;

        for (int i = 0; i < N; i++) {
            applies[i] -= B; //일단 총감독관 수를 빼줌
            total += 1;

            while (applies[i] > 0) {
                applies[i] -= C;
                total += 1;
            }
        }

        System.out.println(total);

    }
}
