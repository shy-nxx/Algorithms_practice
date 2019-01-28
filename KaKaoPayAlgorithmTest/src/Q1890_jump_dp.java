import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1890_jump_dp {
    public static int N;
    public static int[][] matrix;
    public static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        matrix = new int[N + 1][N + 1];
        dp = new long[N + 1][N + 1];

        for (int i= 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //입력 끝

        dp[0][0] = 1;

        for (int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if (i == N-1 && j == N-1) continue;

                int next = matrix[i][j];

                if (i + next < N) {
                    dp[i + next][j] += dp[i][j];
                }
                if (j + next < N) {
                    dp[i][j + next] += dp[i][j];
                }

            }
        }
        System.out.println(dp[N-1][N-1]);
    }
}
