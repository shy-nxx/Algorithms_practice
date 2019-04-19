import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 5 //N
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 0 0 0 1 0
 * 2 2 1 1 0
 * 0 0 0 0 0
 * 4 0 //시작점
 * 2 0 //도착점
 */
public class Swimming {
    public static int[][] matrix;
    public static boolean[][] visitied;
    public static int[] col_rate = {-1,1,0,0};
    public static int[] row_rate = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

//        int t = Integer.parseInt(st.nextToken()); // 테스트 케이스 개수

        int t = 1;

        for (int i = 0; i < t; i++) {
//            st = new StringTokenizer(br.readLine()); // 수영자의 크기 N*N
            int n = Integer.parseInt(st.nextToken());

            visitied = new boolean[n][n];

            matrix = new int[n][n];

            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < n; k++) {
                    matrix[j][k] = Integer.parseInt(st.nextToken());
                }

            }

            //시작점 받기
            st = new StringTokenizer(br.readLine());
            int col_st = Integer.parseInt(st.nextToken());
            int row_st = Integer.parseInt(st.nextToken());

            //도착점 받기
            st = new StringTokenizer(br.readLine());
            int col_end = Integer.parseInt(st.nextToken());
            int row_end = Integer.parseInt(st.nextToken());

            bfs(col_st,row_st, col_end, row_end, n);

        }
    }

    public static void bfs(int col_st, int row_st, int col_end, int row_end, int N) {
        Queue<Point> q = new LinkedList<>();

        q.add(new Point(col_st, row_st));

        visitied[col_st][row_st] = true;

        int seconds = 1;
        int min = 100;

        while(!q.isEmpty()) {

            Point p = q.poll();

            System.out.println(p.col + " " + p.row);

            visitied[p.col][p.row] =true;

            for (int i = 0; i < col_rate.length; i++) {
                int next_col = p.col + col_rate[i];
                int next_row = p.row + row_rate[i];

//                if (next_col == col_st && next_row == row_st) seconds = 0;

                if (next_col < 0 || next_col >= N || next_row < 0 || next_row >= N) continue;

                if (matrix[next_col][next_row] == 1) continue; //장애물

                if (matrix[next_col][next_row] == 2) { //소용돌이
                    if (visitied[next_col][next_row]) { //이미 지난 소용돌이는 그냥 지나감
                        seconds +=1;
                    }
                    else {
                        seconds += 2;
                    }
                }
                else {
                    seconds += 1;
                }
//                System.out.println(next_col + " " + next_row);

                if (next_col == col_end && next_row == row_end)  {
                    min = Math.min(min, seconds);
//                    System.out.println(seconds);
                    seconds = 0;
                    break;
                }

                if (!visitied[next_col][next_row]) {
                    visitied[next_col][next_row] = true;
                    q.add(new Point(next_col, next_row));
                }
            }
            System.out.println(min);
        }
        System.out.println(Math.min(min, seconds));
    }

    public static class Point {
        int col;
        int row;

        public Point(int col, int row) {
            this.col = col;
            this.row = row;
        }
    }
}
