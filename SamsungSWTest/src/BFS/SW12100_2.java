package BFS;

import sun.awt.image.ImageWatched;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW12100_2 {
    static int[][] matrix;
    static char[] directions;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //보드의 크기
        matrix = new int[N+2][N+2];

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken()); //사과의 개수

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            matrix[x][y] = 1; //사과가 있는 위치는 1
        }

        for (int i = 0; i < N +2; i++) {
            for (int j = 0; j < N+2; j++) {
                if (i == 0 || i == N+1 || j == 0 || j == N+1) {
                    matrix[i][j] = 2; //벽은 2
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken()); //방향전환의 개수

        directions = new char[100001];

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());

            directions[Integer.parseInt(st.nextToken())] = st.nextToken().charAt(0);
        }

        BFS(1,1); //(1,1)부터 시작
    }

    static void BFS(int x_st, int y_st) {
        Queue<Position> q = new LinkedList<>();

        q.add(new Position(x_st, y_st));

        matrix[x_st][y_st] = 2; //벽으로 만들어서 부딪히면 게임 종료하도록 만든다.

        Position cP = new Position(x_st, y_st);

        int dir = 1; //direction (북 = 0, 동 = 1, 남 = 2, 서 = 3) --기본이 동쪽

        int total_sec = 0;

        while (true) {
            ++total_sec;

            cP.x += dx[dir];
            cP.y += dy[dir];

            if (matrix[cP.x][cP.y] == 2)
                break;

            if (matrix[cP.x][cP.y] == 0) {
                Position retail = q.poll(); //사과가 없으면 꼬리를 큐에서 제거
                matrix[retail.x][retail.y] = 0;
            }

            q.add(new Position(cP.x, cP.y));
            matrix[cP.x][cP.y] = 2; //머리가 있는 부분을 2로 해줌 (나중에 꼬리 꺼낼때 다시 0으로 변환해줌)

            //방향전환
            if (directions[total_sec] == 'D' || directions[total_sec] == 'L') {
                dir = changeDir(dir, directions[total_sec]);
            }
        }
        System.out.println(total_sec);
    }

    static int changeDir(int cur_dir, char instruct) {
        int next_dir;

        if (instruct == 'D') { //오른쪽 90도
            next_dir = (cur_dir == 3) ? 0 : ++cur_dir;
        }
        else {
            next_dir = (cur_dir == 0) ? 3 : --cur_dir;
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
