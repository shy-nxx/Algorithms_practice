import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q7576 {
    //백준알고리즘 그래프(DFS, BFS) 7576 - 토마토

    /**
     * 1. 토마토가 익은 점들을 큐에 넣어준다. (동시 다발적으로 일어나기 때문에 큐를 사용한 BFS)
     *
     * 2. 익은 토마토 상하좌우를 탐색하며 익지 않은 토마토가 있으면 그 위치를 큐에 넣어준다. (그 위치의 값은 최대 일수를 계산하기 위해 전 위치 +1 을 해준다.)
     *
     * 3. 큐가 빌때까지 계속해준다.
     *
     * 4. 전체 토마토들을 탐색하여  익지않은 토마토(0 값) 하나라도 있으면 -1를 출력한다.
     *
     * 5. 그 외는 최대 일수를 출력한다.
     */

    /*
    입력값
    6 4
    0 0 0 0 0 0
    0 0 0 0 0 0
    0 0 0 0 0 0
    0 0 0 0 0 1
    출력값
    8 -> 토마토가 모두 익는 최소 일수
     */
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static int N, M;
    public static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //상자의 가로
        M = Integer.parseInt(st.nextToken());
        //상자의 세로
        N = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        for (int i =0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j= 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        BFS();
    }
    public static void BFS() {
        Queue<DOT> q = new LinkedList<DOT>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 1)
                    //익은 토마토가 있는 모든 위치를 큐에 담는다.
                    q.add(new DOT(i, j));
            }
        }

        while (!q.isEmpty()) {
            //익은 토마토의 상하좌우는 다음에 익기 때문에 큐에 담아야한다.
            DOT dot = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = dot.x + dx[i];
                int nextY = dot.y + dy[i];

                //범위 밖 패스
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    continue;
                }
                //다음 위치가 익지 않은 토마토가 아니면 패스
                if (matrix[nextX][nextY] != 0) {
                    continue;
                }
                //최대 일수를 구하기 때문에 1로 바꾸는 것이 아니라 현재 일수 +1 을 해줘야한다.
                matrix[nextX][nextY] = matrix[dot.x][dot.y] + 1;
                q.add(new DOT(nextX, nextY));
            }
//            print(matrix, N, M); // 농장 전체 출력
            //System.out.println();
        }
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 0) {
                    //토마토가 모두 익지 못한 상황이라면 -1 을 출력한다.
                    System.out.println(-1);
                    return;
                }
                max = Math.max(max, matrix[i][j]);
            }
        }
        //그렇지 않다면 최대값을 출력한다.
        System.out.println(max - 1);

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
class DOT {
    int x;
    int y;

    DOT(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
