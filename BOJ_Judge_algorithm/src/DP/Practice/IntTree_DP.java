package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IntTree_DP {
    /**
     * 풀이
     *
     *            (1,1)
     *       (2,1)    (2,2)
     *     (3,1)        (3,3)
     *    (4,1) (4,2) (4,3) (4,4)
     * (5,1) (5,2) (5,3) (5,4) (5,5)
     *
     * -세가지 케이스로 나눠야 한다.
     * (i, j)
     * -> j==1
     * tree[i][j] = tree[i-1][j] + tree[i][j]
     *
     * -> j == i
     * tree[i][j] = tree[i-1][j-1] + tree[i][j]
     *
     * -> else (ex, (5,4) -> (4,3) (4,4) 중 큰 값
     * tree[i][j] = max(tree[i-1][j-1], tree[i-1][j]) + tree[i][j]
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //삼각형의 크기

        int[][] tree = new int[501][501];

        int sum = 0;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                tree[i][j] = Integer.parseInt(st.nextToken());

                if (j == 1) {
                    tree[i][j] += tree[i-1][j];
                }
                else if (j == i) {
                    tree[i][j] += tree[i-1][j-1];
                }
                else {
                    tree[i][j] = Math.max(tree[i-1][j-1], tree[i-1][j]) + tree[i][j];
                }

                if (tree[i][j] > sum) sum = tree[i][j];
            }
        }

        System.out.println(sum);

    }
}