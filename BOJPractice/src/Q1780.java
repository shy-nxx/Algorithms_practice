import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1780 {
    /**
     * BOJ DNC -종이
     */

    static int N;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[] count;
    static int[][] matrix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        matrix = new int[N][N];

        count = new int[3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        divide(0,0, N);

        for (int i = 0; i < count.length; i++) {
            System.out.println(count[i]);
        }
    }

    static void divide(int row, int col, int n) {
        if (check(row, col,n)) {
            count[matrix[row][col]+1]++;
        }
        else {
            int s = n/3;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    divide(row+s*i, col+s*j, s);
                }
            }
        }
    }

    static boolean check(int row, int col,int n) {
        int standard = matrix[row][col];

        for (int i = row; i < row +n; i++) {
            for (int j = col; j < col + n ; j++) {
                if (standard != matrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

}
