package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGB_Street_DP {
    /**
     * 풀이 : paint[빨] = min(paint[초], paint[파]) + 빨
     *
     */

    static final int R = 0;
    static final int G = 1;
    static final int B = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] paints = new int[3][N+1];

        for (int i = 1; i<= N; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            paints[R][i] = Math.min(paints[G][i-1], paints[B][i-1]) + r;
            paints[G][i] = Math.min(paints[R][i-1], paints[B][i-1]) + g;
            paints[B][i] = Math.min(paints[R][i-1], paints[G][i-1]) + b;

        }

        int result = Math.min(paints[R][N], Math.min(paints[G][N], paints[B][N]));

        System.out.println(result);
    }
}