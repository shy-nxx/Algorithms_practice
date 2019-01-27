import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q7569 {
    //백준알고리즘 그래프 (DFS, BFS) 7569 - 토마토2
    public static int N, M, H;
    public static int[][][] matrix;

    static int dx[] = {-1, 0, +1, 0, 0, 0};
    static int dy[] = {0, +1, 0, -1, 0, 0};
    static int dz[] = {0, 0, 0, 0,+1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        matrix = new int[H][N][M];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    matrix[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        BFS();

    }
    static void BFS() {
        Queue<ThreeDOT> q = new LinkedList<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (matrix[i][j][k] == 1)
                        q.add(new ThreeDOT(j, k, i));
                }
            }
        }

        while(!q.isEmpty()) {
            ThreeDOT dot = q.poll();

            for (int i = 0; i < dx.length; i++) {
                int nextX = dot.x + dx[i];
                int nextY = dot.y + dy[i];
                int nextZ = dot.z + dz[i];

//                System.out.println(nextZ + " " + nextX + " " + nextY);
                if (nextZ >= H || nextZ < 0 || nextX < 0 || nextY < 0 || nextX >= N || nextY >= M ) continue;

                if (matrix[nextZ][nextX][nextY] != 0) continue;

                matrix[nextZ][nextX][nextY] = matrix[dot.z][dot.x][dot.y] + 1;

                q.add(new ThreeDOT(nextX, nextY, nextZ));
            }

        }
        int max = 0;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (matrix[i][j][k] == 0) {
                        System.out.println("-1");
                        return;
                    }
                    max = Math.max(max, matrix[i][j][k]);
                }
            }
        }
        System.out.println(max-1);
    }
}
class ThreeDOT {
    int x;
    int y;
    int z;

    public ThreeDOT(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
