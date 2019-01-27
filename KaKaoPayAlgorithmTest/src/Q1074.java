import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1074 {
    //백준 알고리즘 분할정복 1074 - Z

    /*
        입력값
         N r c
         2 3 1
        출력값
        11
     */
    public static int N; //2^N * 2^N
    public static int r;
    public static int c;
    public static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int unitSize = (int) Math.pow(2, N);
        solve(unitSize,0,0 );
    }
    static void solve(int size, int row, int col) {
        if(row==r && col==c) {
            System.out.println(count);
            System.exit(0);
        }
        if(size==1) {
            count++;
            return;
        }
        if(!(row <= r && r < size + row && col <= c && c < size + col)) {
            count += size*size;
            return; //이 분단엔 없으니 들르는 수 더해주고 빠져나가기
        }

        solve(size/2,row,col);
        solve(size/2,row,col+size/2);
        solve(size/2,row+size/2,col);
        solve(size/2,row+size/2,col+size/2);

    }
//    static void solve(int row, int col, int n) {
//        if (check(row,col,n)) {
//            System.out.print(count);
//        }
//        else {
//            int s = n/2;
//            for (int i = 0; i < 2; i++) {
//                for (int j = 0; j < 2; j++) {
//                    solve(row + s * i, col + s * j, s);
//                }
//            }
//        }
//    }
//    static boolean check(int row, int col, int n) {
//
//        for (int i = row; i < row + n; i++) {
//            for (int j = col; j < col + n; j++) {
//
//                if (i == r && j == c) {
//                    return true;
//                }
//                count++;
//            }
//        }
//        return false;
//    }

}
