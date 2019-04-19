package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW14499 {
    static int[][] matrix;
    static int[] directions;
    static int[] dice = new int[7];
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //행의 수
        int M = Integer.parseInt(st.nextToken()); //열의 수

        matrix = new int[N][M];

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(st.nextToken()); //명령의 개수

        directions = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            directions[i] = Integer.parseInt(st.nextToken()); //방향 동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4로 주어진다.
        }


        /*주사위의 전개도를 입력
        1이 정면일 때 윗면은 2 or  /아랫면은 5 or / 왼쪽은 3 or / 오른쪽은 4 or
        2가 정면이 때 윗면은 6 or /아랫면은 1 or / 왼쪽은 3 or / 오른쪽은 4 or
        3이 정면일 때 윗면은 1 or /아랫면은 6 or / 왼쪽은 5 or / 오른쪽은 2 or
        4이 정면일 때 윗면은 1 or /아랫면은 6 or / 왼쪽은 1 or / 오른쪽은 5 or
        5이 정면일 때 윗면은 1 or /아랫면은 6 or / 왼쪽은 4 or / 오른쪽은 3 or
        6이 정면일 때 윗면은 5 or /아랫면은 2 or / 왼쪽은 4 or / 오른쪽은 3 or
        윗면과 아랫면은 서로 swap 가능/ 왼쪽과 오른쪽도 서로 swap

        */

        /*
        4 2 0 0 8
        0 2
        3 4
        5 6
        7 8
        4 4 4 1 3 3 3 2
         */


        solution(x, y, N, M);


    }

    static void solution(int x, int y, int N, int M){

        Position nP = new Position(x,y);
        /* 주사위를 굴렸을 때, 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다.
        0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.

        4(아래) -> 윗면이 정면으로 / 정면이 아랫면으로 / 뒷면이 윗면으로 (윗면의 윗면)
        3(위) -> 정면이 윗면으로 / 아랫면이 정면으로 / 뒷면이 아랫면으로
        2(왼쪽) -> 정면 그대로 / 오른쪽이 윗면 / 왼쪽이 아랫면
        1(오른쪽) -> 정면 그대로 / 왼쪽이 윗면 / 오른쪽이 아랫면
         */

        for (int i = 0; i < directions.length; i++) { //명령의 개수 만큼 주사위를 굴림

            nP.x += dx[directions[i]-1];
            nP.y += dy[directions[i]-1];

            if (nP.x < 0 || nP.x >= N || nP.y < 0 || nP.y >= M )
                continue;

            changeDice(directions[i]);

            if (matrix[nP.x][nP.y] != 0) {
                //주사위의 바닥면의 수가 복사됨
                dice[6] = matrix[nP.x][nP.y];
                matrix[nP.x][nP.y] = 0;
            }
            else {
                //주사위의 수가 칸으로 복사됨
                matrix[nP.x][nP.y] = dice[6];
            }


            System.out.println("x = " + nP.x + " y= " + nP.y + " " + dice[1]);
        }

    }

    public static void changeDice(int d) {
        int[] temp = dice.clone();
        // 6 밑면, 1 윗면
        // 동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4
        if (d == 1) {
            dice[1] = temp[4];
            dice[3] = temp[1];
            dice[4] = temp[6];
            dice[6] = temp[3];
        } else if (d == 2) {
            dice[1] = temp[3];
            dice[3] = temp[6];
            dice[4] = temp[1];
            dice[6] = temp[4];
        } else if (d == 3) {
            dice[1] = temp[5];
            dice[2] = temp[1];
            dice[5] = temp[6];
            dice[6] = temp[2];
        } else {
            dice[1] = temp[2];
            dice[2] = temp[6];
            dice[5] = temp[1];
            dice[6] = temp[5];
        }
    }
    public static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

}
