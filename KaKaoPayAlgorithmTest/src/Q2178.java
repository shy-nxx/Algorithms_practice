import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2178 {
    //백준알고리즘 그래프 (BFS,DFS) 2178 - 미로탐색

    /**
     * 입력값
     * 4 6
     * 101111
     * 101010
     * 101011
     * 111011
     *
     * 출력값
     * 15
     */
    public static int N, M;
    public static int[][] matrix;
    public static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                matrix[i][j] = s.charAt(j) - 48;
                visited[i][j] = false;
            }
        }

        visited[0][0] = true;
        BFS(0,0);

        System.out.println(matrix[N-1][M-1]);
    }

    static void BFS(int x, int y) {
        Queue<DOT> q = new LinkedList<>();
        q.add(new DOT(x, y));

        while (!q.isEmpty()) {
            DOT d = q.poll();

            for (int i = 0; i < dx.length; i++) {
                int nextX = d.x + dx[i];
                int nextY = d.y + dy[i];

                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;

                if (matrix[nextX][nextY] == 0) continue;

                if (visited[nextX][nextY]) continue;

                visited[nextX][nextY] = true;
                matrix[nextX][nextY] = matrix[d.x][d.y] + 1;
                q.add(new DOT(nextX, nextY));
            }
//            print(matrix, N, M);
//            System.out.println();
        }
    }

    //농장을 전체 보여주는 함수
    public static void print(int[][] arr, int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }


}
