import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1992_2 {
    public static int N;
    public static int[][] matrix;
    public static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        matrix = new int[N][N];
        char[] temp = new char[N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                temp[j] = input.charAt(j);
                matrix[i][j] = temp[j] - 48;
            }
        }

        divide(0,0,N);

    }
    static void divide(int row, int col, int n) {
        if (check(row, col, n)) {
            System.out.print(M);
        }
        else {
            System.out.print("(");
            int s = n/2;

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    divide(row + s * i, col + s * j, s);
                }
            }

            System.out.print(")");
        }
    }
    static boolean check(int row, int col, int n) {
        int standard = matrix[row][col];

        for (int i = row; i < row + n; i++) {
            for (int j = col; j < col + n; j++) {
                if (standard != matrix[i][j])
                    return false;
            }
        }
        M = standard;
        return true;
    }

}
