package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DP_1149_RGB {
    /**
     *
     * RGB거리에 사는 사람들은 집을 빨강, 초록, 파랑중에 하나로 칠하려고 한다. 또한, 그들은 모든 이웃은 같은 색으로 칠할 수 없다는 규칙도 정했다. 집 i의 이웃은 집 i-1과 집 i+1이다.
     *
     * 각 집을 빨강으로 칠할 때 드는 비용, 초록으로 칠할 때 드는 비용, 파랑으로 드는 비용이 주어질 때, 모든 집을 칠할 때 드는 비용의 최솟값을 구하는 프로그램을 작성하시오.
     *
     * 2차원 배열 -> 1차원 배열 --> 	k = from[0].length * i + j 로 표현 가능하다.
     *
     * list[빨강] = MIN(list[초록] , list[파랑]) + 빨강 비용 이 됩니다.
     *
     */
    static final int R = 0;
    static final int G = 1;
    static final int B = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //계단의 개수

        int[][] paints = new int[3][N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            paints[R][i] = Math.min(paints[G][i-1], paints[B][i-1]) + r;
            paints[G][i] = Math.min(paints[R][i-1], paints[B][i-1]) + g;
            paints[B][i] = Math.min(paints[R][i-1], paints[G][i-1]) + b;
        }

        int result = 0;

        result = Math.min(paints[R][N], Math.min(paints[G][N], paints[B][N]));


        System.out.println(result);

//        int[] arrays = new int[N*N];
//
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                arrays[N*i+j] = paints[i][j];
//            }
//        }


    }
}