import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1074 {
    /**
     * 분할정복 Z
     */

    public static int N, R, C;
    public static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int unitSize = (int)Math.pow(2, N); //2^N * 2^N 사이즈의 2차원 배열

        solve(0,0, unitSize);
    }
    static void solve(int row, int col, int size) {
        if (row == R && col == C ) {
            System.out.println(count);
            System.exit(0);
        }

        if (size == 1 ) {
            count++;
            return;
        }

        if (!(row <= R && R < size + row  && col <= C && C < size + col)) {
            //이 분면에 없음
            count += size * size;
            return;
        }

        solve(row, col, size/2);
        solve(row, col+size/2, size/2);
        solve(row+size/2, col, size/2);
        solve(row+size/2, col+size/2, size/2);

    }
}
