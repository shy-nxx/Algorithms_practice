package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW14503 {
    /**
     * 로봇청소기
     *
     * 현재 위치를 청소한다.
     * 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
     * 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
     * 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
     * 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
     * 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
     *
     * d가 0인 경우에는 북쪽을, 1인 경우에는 동쪽을, 2인 경우에는 남쪽을, 3인 경우에는 서쪽
     *
     *
     * BFS를 응용하여 로봇이 작동 순서 그대로 이동하는 것을 구현했다.
     *
     * 로봇은 항상 왼쪽 방향만으로만 이동한다는 것을 활용해야한다.
     *
     * 그래서 코드를 보면, 큐의 사이즈는 항상 1 이하일 것이다.
     *
     * BFS 특성을 활용해 4번의 작동은 큐가 비었다는 것을 의미하기에, 신경쓰지 않았다.
     *
     *
     *
     * 작동 순서에 따른 회전과 후진을 위한 방향만을 잘 지정해주면 문제는 쉽게 해결된다.
     *
     *
     */

    static int N, M, count = 0;
    static int[][] matrix;
    static boolean[][] visited;
//    static int[] directions;
    static Position start;

    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //현 로봇청소기 위치 / 방향
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        //d가 0인 경우에는 북쪽을, 1인 경우에는 동쪽을, 2인 경우에는 남쪽을, 3인 경우에는 서쪽
        int dir = Integer.parseInt(st.nextToken());

        matrix = new int[50][50];
        visited = new boolean[50][50];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        start = new Position(r,c,dir);
        visited[r][c] = true;
        bfs();
        System.out.println(count+1);

    }

    static void bfs() {
        Queue<Position> q = new LinkedList<>();

        q.add(start);

        while(!q.isEmpty()) {
            Position p = q.poll();

            int dir = p.d;
            int x = p.x;
            int y = p.y;

            int nDir = dir;
            int nx = 0;
            int ny = 0;

            boolean flag = false;

            for (int i = 0; i < 4; i++) {
                nDir = turnDirection(nDir);
                nx = x + dx[nDir];
                ny = y + dy[nDir];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (matrix[nx][ny] == 0 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    ++count;
                    q.add(new Position(nx, ny, nDir));
                    flag = true;
                    break;
                }
            }
            //후진 -> 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
            if (!flag) {
                nDir = backDirection(dir);
                nx = x + dx[nDir];
                ny = y + dy[nDir];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (matrix[nx][ny] == 0 ) {
                    q.add(new Position(nx, ny, dir)); //바라보는 방향을 유지해야
                }
            }

        }

    }
    //d가 0인 경우에는 북쪽을, 1인 경우에는 동쪽을, 2인 경우에는 남쪽을, 3인 경우에는 서쪽
    static int turnDirection(int dir) {
        if (dir == 0)
            return 3;
        else if (dir == 1)
            return 0;
        else if (dir == 2)
            return 1;
        else
            return 2;

    }

    static int backDirection(int dir) {
        if (dir == 0)
            return 2;
        else if (dir == 1)
            return 3;
        else if (dir == 2)
            return 0;
        else
            return 1;
    }
    static class Position {
        int x;
        int y;
        int d;

        public Position(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}