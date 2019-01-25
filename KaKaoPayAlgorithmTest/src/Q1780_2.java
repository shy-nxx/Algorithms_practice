import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1780_2 {
    public static int[][] paper_blanks;
    public static int[] count = new int[3];

   public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());

       int n = Integer.parseInt(st.nextToken());

       paper_blanks = new int[n][n];

       for (int i = 0; i < paper_blanks.length; i++) {
           st = new StringTokenizer(br.readLine());
           for (int j = 0; j < paper_blanks.length; j++) {
               paper_blanks[i][j] = Integer.parseInt(st.nextToken());
           }
       }
       divide(0,0, n);

       for (int i = 0; i < count.length; i++)
           System.out.println(count[i]);
   }
   static void divide(int row, int col, int n) {
       if (check(row, col, n)) {
           count[paper_blanks[row][col]+1]++;
       } else {
           int s = n/3;
           for (int i = 0; i < 3; i++) {
               for (int j = 0; j < 3; j++) {
                   divide(row + s * i, col + s * j,s);
               }
           }
       }
   }
   static boolean check(int row, int col, int n) {
       int standard = paper_blanks[row][col];
       for (int i = row; i < row + n; i++) {
           for (int j = col; j < col + n; j++) {
               if (standard != paper_blanks[i][j])
                   return false;
           }
       }
       return true;
   }

}
