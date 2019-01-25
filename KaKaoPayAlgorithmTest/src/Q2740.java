import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2740 {
//백준알고리즘 분할정복 -2740 행렬 곱셈
    public static int [][] matrixA;
    public static int [][] matrixB;
    public static int N;
    public static int M;
    public static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        matrixA = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrixA[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        matrixB = new int[M][K];


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                matrixB[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//        for (int i = 0; i < M; i++) {
//            for (int j = 0; j < K; j++) {
//                System.out.print(matrixB[i][j]);
//            }
//            System.out.println();
//        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K; j++) {
                int result = 0;
                for (int k = 0; k < M; k++) {
                    result += matrixA[i][k] * matrixB[k][j];

                }
                System.out.print(result + " ");
            }
            System.out.println();

        }
    }
    static void matrix_multiplier() {

    }
}
