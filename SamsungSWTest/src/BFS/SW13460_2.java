package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW13460_2 {
    static char[][] matrix;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][][][] check;
    static final int MAX = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        matrix = new char[N][M];

        Position R = new Position();
        Position B = new Position();

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                matrix[i][j] = s.charAt(j);

                if (s.charAt(j) == 'R') {
                    matrix[i][j] = '.';
                    R = new Position(i, j);
                }
                if (s.charAt(j) == 'B') {
                    matrix[i][j] = '.';
                    B = new Position(i, j);
                }
            }
        }

        check = new boolean[MAX][MAX][MAX][MAX];

        for (int i = 0; i < MAX; i++)
            for(int j = 0; j< MAX; j++)
                for (int k = 0; k < MAX; k++)
                    Arrays.fill(check[i][j][k], false);


        check[R.x][R.y][B.x][B.y] = true;

        BFS(R, B);

    }

    static void BFS(Position R, Position B) {
        Queue<QData> q = new LinkedList<>();

        q.add(new QData(R, B, 0));

        while (!q.isEmpty()) {
            QData now = q.poll();

            R = new Position(now.R.x, now.R.y);
            B = new Position(now.B.x, now.B.y);

            int cnt = now.cnt + 1;

            if (cnt > 10) {
                continue;
            }

            for (int i = 0; i < dx.length; i++) {
                Position nR = new Position(R.x, R.y);
                Position nB = new Position(B.x, B.y);

                while (matrix[nR.x + dx[i]][nR.y + dy[i]] != '#') {
                    nR.x += dx[i];
                    nR.y += dy[i];

                    if (matrix[nR.x][nR.y] == '0') {
                        System.out.println(cnt);
                        System.exit(0);
                    }
                }

                while(matrix[nB.x + dx[i]][nB.y + dy[i]] != '#') {
                    nB.x += dx[i];
                    nB.y += dy[i];

                    if (matrix[nB.x][nB.y] == '0') break;
                }

                if (matrix[nR.x][nR.y] == '0') {
                    System.out.println(cnt);
                    System.exit(0);
                }

                if (matrix[nB.x][nB.y] == '0') break;

                //R과 B가 겹친 경우
                if (nR.x == nB.x && nR.y == nB.y) {
                    if (dx[i] != 0) { //북/남으로 진행되고 있을 경우 (1 or -1)
                        if (R.x * dx[i] > B.x * dx[i]) nB.x -= dx[i];
                        else nR.x -= dx[i];
                    }
                    else {
                        if (R.y * dy[i] > B.y *  dy[i]) nB.y -= dy[i];
                        else nR.y -= dy[i];
                    }
                }

                if (!check[nR.x][nR.y][nB.x][nB.y]) {
                    q.add(new QData(nR, nB, cnt));
                    check[nR.x][nR.y][nB.x][nB.y] = true;
                }

            }
        }
        System.out.println(-1);
    }

    static class Position {
        int x; int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position() {
            x = 0;
            y = 0;
        }
    }

    static class QData{
        Position R;
        Position B;
        int cnt;

        public QData(Position R, Position B, int cnt) {
            this.R = R;
            this.B = B;
            this.cnt = cnt;
        }
    }
}
