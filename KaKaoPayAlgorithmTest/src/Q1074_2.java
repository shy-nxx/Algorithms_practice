import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1074_2 {
    public static int N;
    public static int r;
    public static int c;
    public static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int unitSize = (int)Math.pow(2, N);

        solve(unitSize, 0, 0);
    }
    static void solve(int size, int row, int col) {
        if (row == r && col == c) {
            System.out.print(count);
            System.exit(0);
        }
        if (size == 1) {
            count++;
            return;
        }

        if (!(row <= r && r < row + size && col <= c && c < col + size)) {
            count += size * size;
            return;
        }

        solve(size/2, row, col);
        solve(size/2, row, col+size/2);
        solve(size/2, row + size/2, col);
        solve(size/2, row + size/2, col + size/2);
    }
}
