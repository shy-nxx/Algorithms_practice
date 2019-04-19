package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Snake_samsung_bfs {
    /**
     * 입력값 (구현 안됨)
     *
     * 10
     * 4
     * 1 2
     * 1 3
     * 1 4
     * 1 5
     * 4
     * 8 D
     * 10 D
     * 11 D
     * 13 L
     *
     * 출력값
     * 21
     */
    static int N, L, K;
    static char[] direction;
    static int[][] matrix;

    //북동남서
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    //1은 사과/ 벽은 2
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        matrix = new int[N+2][N+2];

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken()); //사과 개수

        for (int i = 0; i < N+2; i++) {
            for (int j = 0; j < N+2; j++) {
                if (i == 0 || j == 0 || i == N+1 || j == N+1)
                    matrix[i][j] = 2;
            }
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            matrix[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1; //사과가 있는 곳은 1로 표시

        }
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken()); //방향 전환 개수

        direction = new char[100001];

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());

            direction[Integer.parseInt(st.nextToken())] = st.nextToken().charAt(0);
        }

        BFS(1,1);

    }
    static void BFS(int i, int j) {
        Queue<Position> q = new LinkedList<>();

        Position cP = new Position(i, j);
        q.add(cP);

        int dir = 1;
        int seconds = 0;

        matrix[cP.x][cP.y] = 2; //벽으로 만들어줌

        while(true) {
            ++seconds;
            cP.x += dx[dir];
            cP.y += dy[dir];

            if (matrix[cP.x][cP.y] == 2) {
                break;
            }

            if (matrix[cP.x][cP.y] == 0) {
                Position retail = q.poll();
                matrix[retail.x][retail.y] = 0;
            }

            q.add(cP);
            matrix[cP.x][cP.y] = 2;

            if (direction[seconds] == 'D' || direction[seconds] == 'L')
                dir = changeDirection(dir, direction[seconds]);

        }
        System.out.println(seconds);

    }

    static int changeDirection(int dir, char inst ) {
        int next_dir;

        if (inst == 'D') {
            next_dir = (dir == 3) ? 0 : ++dir;
        }
        else {
            next_dir = (dir == 0) ? 3 : --dir;
        }
        return next_dir;
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
