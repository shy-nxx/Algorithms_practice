import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1890 {
    /**
     * 백준알고리즘 그래프 (DFS, BFS) 1890- 점프
     * 입력값
     * 4
     * 2 3 3 1
     * 1 2 1 3
     * 1 2 3 1
     * 3 1 1 0
     *
     * 출력값
     * 3
     */

    public static int N;
    public static int[][] matrix;
    public static boolean[][] visitied;
    public static int result = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        matrix = new int [N][N];
        visitied = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix [i][j] = Integer.parseInt(st.nextToken());
                visitied[i][j] = false;
            }
        }

        BFS(0,0);


    }

    static void BFS(int x, int y) {
        Queue<DOT> q = new LinkedList<>();

        q.add(new DOT(x, y));
        visitied[x][y] = true;

        while (!q.isEmpty()) {
            DOT d = q.poll();

            int pointX = d.x;
            int pointY = d.y;
            int dx[] = {matrix[pointX][pointY], 0};
            int dy[] = {0, matrix[pointX][pointY]};

            for (int i = 0; i < dx.length; i++) {
                int nextX = pointX + dx[i];
                int nextY = pointY + dy[i];

                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;
                if (matrix[nextX][nextY] == 0) result++;

                else  q.add(new DOT(nextX, nextY));

            }

        }
        System.out.println(result);
    }
}
