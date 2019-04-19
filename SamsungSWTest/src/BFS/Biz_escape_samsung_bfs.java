package BFS;

import javax.crypto.spec.PSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Biz_escape_samsung_bfs {
    static int N, M;
    static char[][] matrix;
    static boolean[][][][] visited;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};

    static int MAX = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new char[N][M];
        visited = new boolean[MAX][MAX][MAX][MAX];

        int r_x = 0, r_y = 0, b_x = 0, b_y = 0;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                matrix[i][j] = s.charAt(i);
                if (matrix[i][j] == 'R') {
                    matrix[i][j] = '.';
                    r_x = i;
                    r_y = j;
                }
                if (matrix[i][j] == 'B') {
                    matrix[i][j] = '.';
                    b_x = i;
                    b_y = j;
                }
            }
        }

        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                for (int k = 0; k < MAX; k++)
                    Arrays.fill(visited[i][j][k], false);
            }
        }
//        visited[R.x][R.y][B.x][B.y] = true;

        Position R = new Position(r_x, r_y);
        Position B = new Position(b_x, b_y);
        BFS(R, B);

    }

    static void BFS(Position R, Position B) {
        Queue<Biz> q = new LinkedList<>();

        q.add(new Biz(R, B, 0));

        while(!q.isEmpty()) {
            Biz b = q.poll();

            Position cR = b.R;
            Position cB = b.B;

            int cnt = b.cnt + 1;

            if (cnt > 10) {
                continue;
            }

            for (int i = 0; i < dx.length; i++) {
                Position nR = new Position(cR.x, cR.y);
                Position nB = new Position(cB.x, cB.y);

                System.out.println(nR.x + dx[i]  + " " + nR.y + dy[i]);

                while(matrix[nR.x + dx[i]][nR.y + dy[i]] != '#') {
                    nR.x += dx[i];
                    nR.y += dy[i];

                    if (matrix[nR.x][nR.y] =='0') {
                        System.out.println(cnt);
                        System.exit(0);
                    }
                }
                while(matrix[nB.x + dx[i]][nB.y + dy[i]] != '#') {
                    nB.x += dx[i];
                    nB.y += dy[i];

                    if (matrix[nB.x][nB.y] =='0') {
                        continue;
                    }
                }

                if (matrix[nR.x][nR.y] =='0') {
                    System.out.println(cnt);
                    System.exit(0);
                }
                if (matrix[nB.x][nB.y] =='0') {
                    continue;
                }

                //겹치는 경우

                if (nR.x == nB.x && nR.y == nB.y) {
                    if (dx[i] != 0) {
                        System.out.println(R.x + " " + B.x);
                        if (R.x * dx[i] > B.x * dx[i]) nB.x -= dx[i];
                        else {
                            nR.x -= dx[i];
                        }
                    }
                    else {
                        System.out.println(R.y + " " + B.y);
                        if (R.y * dy[i] > B.y * dy[i]) nB.y -= dy[i];
                        else {
                            nR.y -= dy[i];
                        }
                    }

                }

                if (visited[nR.x][nR.y][nB.x][nB.y]) continue;

                q.add(new Biz(nR, nB, cnt));
                visited[nR.x][nR.y][nB.x][nB.y] = true;
            }
        }
        System.out.println(-1);


    }
    public static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public Position() {
            x = 0;
            y = 0;
        }
    }
    public static class Biz {
        Position R;
        Position B;
        int cnt;

        public Biz(Position R, Position B, int cnt) {
            this.R = R;
            this.B = B;
            this.cnt = cnt;
        }

    }
}
