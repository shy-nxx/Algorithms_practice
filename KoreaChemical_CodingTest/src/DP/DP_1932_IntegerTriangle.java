package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_1932_IntegerTriangle {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] matrix = new int[N+1][N+1];

//        for (int i = 0; i < N; i++){
//            for (int j = 0; j <= i; j++) {
//               System.out.print(matrix[i][j]);
//            }
//            System.out.println();
//        }

        /**
         * 선택할 수 있는 방향
         * (0,0) + (1,0) + (2,0) + (3,0) ...
         * (0,0) + (1,1) + (2,2) + (3,3) ...
         * 그 외에는 max(i-1, j-1 / i-1, j)
         *
         */
        int sum = 0;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if (j == 1) {
                    matrix[i][j] += matrix[i-1][j];
                }
                else if (j == i) {
                    matrix[i][j] += matrix[i-1][j-1];
                }
                else {
                    matrix[i][j] = Math.max(matrix[i-1][j-1] + matrix[i][j], matrix[i-1][j] + matrix[i][j]);
                }

                sum = Math.max(sum, matrix[i][j]);

            }
        }
        System.out.println(sum);

    }
}