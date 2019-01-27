import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q7576_2 {
    public static int N, M;
    public static int[][] matrix;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        for (int i = 0; i < N; i++ ) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //입력 끝

        BFS();
    }
    static void BFS() {
        Queue<DOT> q = new LinkedList<>();

        //익은 토마토의 좌표를 넣음
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 1) {
                    q.add(new DOT(i,j));
                }
            }
        }

        while(!q.isEmpty()) {
            DOT d = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = d.x + dx[i];
                int nextY = d.y + dy[i];

                //최대좌표를 넘어섰을 경우
                if (nextX < 0 || nextX >= N || nextY < 0|| nextY >= M) continue;

                //0이 아니면 빈 상자이거나 이미 익었을 경우이기 때문에 다음 좌표로 넘긴다.
                if (matrix[nextX][nextY] != 0) continue;

                matrix[nextX][nextY] = matrix[d.x][d.y] + 1;

                q.add(new DOT(nextX, nextY));

            }

        }
        int max = 0;
        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 0) {
                    System.out.println("-1");
                    return;
                }
                max = Math.max(max, matrix[i][j]);
            }

        }
        System.out.println(max-1);

    }
}
//
//class DOT {
//    int x;
//    int y;
//
//    public DOT(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//}