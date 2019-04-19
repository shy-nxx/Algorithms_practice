package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW14503_2 {
    static int N, M, count = 0;
    static int[][] matrix;
    static boolean[][] visited;
    static Pos start;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    //d가 0인 경우에는 북쪽을, 1인 경우에는 동쪽을, 2인 경우에는 남쪽을, 3인 경우에는 서쪽을 바라보고 있는 것이다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken()); //현재 방향

        matrix = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                matrix[i][j] = Integer.parseInt(st.nextToken());
        }

        start = new Pos(R,C,D);
        visited[R][C] = true;

        BFS();
        System.out.println(count + 1);


    }

    public static void BFS() {
        Queue<Pos> q = new LinkedList<>();

        q.add(start);

        while(!q.isEmpty()) {
            Pos p = q.poll();

            int dir = p.d;
            int x = p.x;
            int y = p.y;

            int nDir = dir; //왼쪽 방향에 청소할 공간이 있으면 그 방향으로 전환해야한다.
            int nX = 0;
            int nY = 0;

            boolean flag = false; //사방이 다 벽이거나 청소를 끝낸 구간이라면 후진을 해야한다.

            for (int i = 0; i < dx.length; i++) {
                nDir = turnDirection(nDir);
                nX = x + dx[nDir];
                nY = y + dy[nDir];

                if (nX < 0 || nX >= N || nY < 0 || nY >= M) continue;

                if (matrix[nX][nY] == 0 && !visited[nX][nY]) {
                    visited[nX][nY] = true;
                    q.add(new Pos(nX,nY,nDir));
                    ++count;
                    flag = true; //청소할 구간이 있었음
                    break; //한칸 청소 후에 다시 왼쪽 방향을 탐색해야함.
                }

            }

            if (!flag) {
                nDir = backDirection(dir);

                nX = x + dx[nDir];
                nY = y + dy[nDir];

                if (nX < 0 || nX >= N || nY < 0 || nY >= M) continue;

                if (matrix[nX][nY] == 0) {
                    q.add(new Pos(nX,nY,dir));
                }
            }
        }

    }

    static int turnDirection(int dir) {
        //북 -> 서 / 남 -> 동 / 동 -> 북 / 서-> 남
        //d가 0인 경우에는 북쪽을, 1인 경우에는 동쪽을, 2인 경우에는 남쪽을, 3인 경우에는 서쪽을 바라보고 있는 것이다.
        if (dir == 0)
            return 3;
        else if (dir ==1)
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

    public static class Pos {
        int x;
        int y;
        int d;

        public Pos (int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;

        }
    }

}