package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2098 {
    static int N;
    static int[][] dp = new int[16][1<<16];
    static int[][] W = new int[16][16];
    static int IMP = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //정수의 개수

        for (int i = 0;i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println( tsp(0,1));

    }
    static int tsp(int cur, int visited) {
        int ret = dp[cur][visited];

        if (ret != -1) {
            return ret;
        }
        //모든 마을을 방문함
        if (visited == (1<<N)-1) {
            if (W[cur][0] != 0) return W[cur][0];
            return IMP;

        }

        ret = IMP;

        for (int i = 0; i < N; i++) {
            //i번 마을을 이미 방문했거나 갈 수 없다.
            if ((visited & (1<<i)) < 0 || W[cur][i] == 0) continue;

            ret = Math.min(ret, tsp(i, visited | (1<<i))+ W[cur][i]);
        }
        return ret;
    }
}
