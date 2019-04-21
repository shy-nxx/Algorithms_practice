package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCS_DP {
    /**
     * 최장 공통 부분 수열 :
     * LCS -> 0 ( x.length = 0 || y.length == 0)
     * -> lsc(i - 1 , j-1) + 1 (x[i] == y[j])
     * -> max (lcs(i-1, j), lcs(i, j-1) (x[i] != y[j])
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String f = br.readLine();
        String s = br.readLine();

        char[] X= new char[f.length()];
        char[] Y = new char[f.length()];

        for (int i = 0; i < f.length(); i++) {
            X[i] = f.charAt(i);
            Y[i] = s.charAt(i);

        }

        int[][] lcs = new int[1001][1001];

        lcs[0][0] = 0;
        int max = 0;

        for (int i = 1; i <= X.length; i++) {
            for (int j = 1; j <= Y.length; j++) {
                if (X[i-1] == Y[j-1]) {
                    lcs[i][j] = lcs[i-1][j-1] +1;
                }
                else {
                    lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
                }
                max = Math.max(max, lcs[i][j]);
            }
        }

        System.out.println(max);

    }
}