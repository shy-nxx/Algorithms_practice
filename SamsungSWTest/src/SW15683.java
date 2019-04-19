import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW15683 {
    /**
     * 0은 빈 칸, 6은 벽, 1~5는 CCTV의 번호
     * N개의 줄에는 사무실 각 칸의 정보가 주어진다. 0은 빈 칸, 6은 벽, 1~5는 CCTV를 나타내고, 문제에서 설명한 CCTV의 종류이다.
     * 사각 지대의 최소 크기를 출력한다.
     */
    static int N, M;
    static int[][] matrix;
    static boolean[][] visited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int[] cctvs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        visited = new boolean[N][M];


        Pos p = new Pos();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if (matrix[i][j] != 0 || matrix[i][j] != 6) {
                    p.x = i;
                    p.y = j;
                    p.value = matrix[i][j];
                }
            }
        }

        BFS(p);

        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 0)
                    count++;

                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }

        System.out.println(count);
    }


    public static void BFS(Pos p) {
        Queue<Pos> q = new LinkedList<>();

        q.add(p);

        visited[p.x][p.y] = true;

        while(!q.isEmpty()) {
            Pos cP = q.poll();

            /*1번 카메라 : 동쪽 방향 -> 90도 회전 가능 -> 한 방향으로 이은 다음에 break 후에 회전해서 다시 BFS?
            2번 카메라 : 양 옆
            3번 카메라 : 직각
            4번 카메라 : 3방향
            5번 카메라 : 모든 방향 (4분면)
            1번 -> 동쪽이 기본 설정
             */

            int value = cP.value;
            int dir = 0;
            int nDir = dir;

            int x = cP.x;
            int y = cP.y;

            int nX = x;
            int nY = y;

            for (int i = 0; i < dx.length; i++){
                nX = x + dx[nDir];
                nY = y + dy[nDir];
                System.out.println(nX + " " + nY + " " + nDir);

                if (nX < 0 || nX >= N || nY < 0 || nY >= M) continue;

                if (matrix[nX][nY] == 6) {
                    nDir = turnDirection(value, nDir);
                    continue;
                }

                if (matrix[nX][nY] == 0 && !visited[nX][nY]) {
                    visited[nX][nY] = true;
                    q.add(new Pos(nX, nY, value));
                    matrix[nX][nY] = -1;
                    System.out.print(matrix[nX][nY]);
                }
            }
        }

    }
    public static int turnDirection(int value, int dir) {
        //동 서 북 남

        if (dir == 0)
            return 2;
        else if (dir == 1)
            return 3;
        else if (dir == 2)
            return 1;
        else
            return 0;

    }
    public static class Pos {
        int x;
        int y;
        int value;

        public Pos(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;

        }
        public Pos() {
            this.x = 0;
            this.y = 0;
            this.value =1;

        }
    }
}