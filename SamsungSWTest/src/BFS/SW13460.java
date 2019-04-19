package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW13460 {
    /**
     * 최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 출력한다. 만약, 10번 이하로 움직여서 빨간 구슬을 구멍을 통해 빼낼 수 없으면 -1을 출력한다.
     * 5 5
     * #####
     * #..B#
     * #.#.#
     * #RO.#
     * #####
     */
    static char[][] matrix;
    static boolean[][] visited;
    static int N, M;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};
    static final int MAX = 10;
    static boolean check[][][][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new char[N][M];

        int x_red = 0, y_red = 0, x_blue = 0, y_blue = 0;

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j< M; j++) {
                matrix[i][j] = s.charAt(j);
                if (matrix[i][j] == 'R') {
                    matrix[i][j] = '.';
                    x_red = i;
                    y_red = j;
                }
                if (matrix[i][j] == 'B') {
                    matrix[i][j] = '.';
                    x_blue = i;
                    y_blue = j;
                }

            }
        }

        Position R  = new Position(x_red, y_red);
        Position B  = new Position(x_blue, y_blue);

        check = new boolean[MAX][MAX][MAX][MAX];//(Rx, Ry, Bx, By) 탐색했는지 여부 저장
        for(int i=0;i<MAX;i++)
            for(int j=0;j<MAX;j++)
                for(int k=0;k<MAX;k++)
                    Arrays.fill(check[i][j][k], false);

        check[R.x][R.y][B.x][B.y] = true;

        BFS(R, B);

    }

    public static void BFS(Position R, Position B) {
        Queue<Qdata> q = new LinkedList<>();

        q.add(new Qdata(R, B, 0)); //시작 지점

        while(!q.isEmpty()) {
            Qdata now = q.poll();
            R = new Position(now.R.x, now.R.y);
            B = new Position(now.B.x, now.B.y);

            int cnt = now.cnt + 1;
//            System.out.println(R.x + " " + R.y + " cnt = " + cnt);

            if (cnt > 10) continue;//10번 넘어가면 안됨

            for (int i = 0; i < dx.length; i++) {
                Position nR = new Position(R.x, R.y);
                Position nB = new Position(B.x, B.y);

                while (matrix[nR.x + dx[i]][nR.y + dy[i]] != '#')
                {
                    nR.x += dx[i];
                    nR.y += dy[i];
                    if (matrix[nR.x][nR.y] == 'O') {
                        System.out.println(cnt);
                        System.exit(0);
                    }
                }
                while (matrix[nB.x + dx[i]][nB.y + dy[i]] != '#')
                {
                    nB.x += dx[i];
                    nB.y += dy[i];
                    if (matrix[nB.x][nB.y] == 'O') break;
                }

                if (matrix[nR.x][nR.y] == '0') {
                    System.out.println(cnt);
                    System.exit(0);
                }

                if (matrix[nB.x][nB.y] == '0') {
                    continue;
                }


                //겹친 경우
                if (nR.x == nB.x && nR.y == nB.y)
                {
                    if (dx[i] != 0)
                    {
                        if (R.x * dx[i] > B.x * dx[i]) nB.x -= dx[i];
                        else nR.x -= dx[i];
                    }
                    else
                    {
                        if (R.y * dy[i] > B.y * dy[i]) nB.y -= dy[i];
                        else nR.y -= dy[i];
                    }
                }

                //방문 안한 것만 큐에 push
                if (check[nR.x][nR.y][nB.x][nB.y] == true) continue;

                q.add(new Qdata(nR,nB, cnt));
                check[nR.x][nR.y][nB.x][nB.y] = true;

            }
        }

        System.out.println(-1);

    }

    private static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
    static class Qdata {
        Position R;
        Position B;
        int cnt;

        public Qdata(Position R, Position B, int cnt)
        {
            this.R = R;
            this.B = B;
            this.cnt = cnt;
        }
    }
}
