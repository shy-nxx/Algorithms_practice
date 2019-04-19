import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1890 {
    /**
     * BOJ graph -jump -> BFS 사용버전
     */

    static int N;
    static int[][] matrix;
    static boolean[][] visited;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        BFS(0,0);

        System.out.println(result);
    }
    static void BFS(int row, int col) {
        Queue<DOT> q = new LinkedList<>();

        q.add(new DOT(row, col));

        while (!q.isEmpty()) {
            DOT d = q.poll();
            int pointX = d.x;
            int pointY = d.y;

            int[] dx = {matrix[pointX][pointY], 0};
            int[] dy = {0, matrix[pointX][pointY]};

            for (int i = 0; i < dx.length; i++) {
                int nextX = pointX + dx[i];
                int nextY = pointY + dy[i];

                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;
                if (matrix[nextX][nextY] == 0 ) result++;

                else q.add(new DOT(nextX, nextY));

            }

        }
    }
    static class DOT {
        int x;
        int y;

        public DOT(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
