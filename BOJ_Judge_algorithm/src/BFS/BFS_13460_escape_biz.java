package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_13460_escape_biz {
    /**
     * 스타트링크에서 판매하는 어린이용 장난감 중에서 가장 인기가 많은 제품은 구슬 탈출이다. 구슬 탈출은 직사각형 보드에 빨간 구슬과 파란 구슬을 하나씩 넣은 다음, 빨간 구슬을 구멍을 통해 빼내는 게임이다.
     * <p>
     * 보드의 세로 크기는 N, 가로 크기는 M이고, 편의상 1×1크기의 칸으로 나누어져 있다. 가장 바깥 행과 열은 모두 막혀져 있고, 보드에는 구멍이 하나 있다. 빨간 구슬과 파란 구슬의 크기는 보드에서 1×1크기의 칸을 가득 채우는 사이즈이고, 각각 하나씩 들어가 있다. 게임의 목표는 빨간 구슬을 구멍을 통해서 빼내는 것이다. 이때, 파란 구슬이 구멍에 들어가면 안된다.
     * <p>
     * 이때, 구슬을 손으로 건드릴 수는 없고, 중력을 이용해서 이리 저리 굴려야 한다. 왼쪽으로 기울이기, 오른쪽으로 기울이기, 위쪽으로 기울이기, 아래쪽으로 기울이기와 같은 네 가지 동작이 가능하다.
     * <p>
     * 각각의 동작에서 공은 동시에 움직인다. 빨간 구슬이 구멍에 빠지면 성공이지만, 파란 구슬이 구멍에 빠지면 실패이다. 빨간 구슬과 파란 구슬이 동시에 구멍에 빠져도 실패이다. 빨간 구슬과 파란 구슬은 동시에 같은 칸에 있을 수 없다. 또, 빨간 구슬과 파란 구슬의 크기는 한 칸을 모두 차지한다. 기울이는 동작을 그만하는 것은 더 이상 구슬이 움직이지 않을 때 까지이다.
     * <p>
     * 보드의 상태가 주어졌을 때, 최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 구하는 프로그램을 작성하시오.
     */

    static int N, M;
    static char[][] map;
    static boolean[][] visitied;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    static Biz R, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //정점
        M = Integer.parseInt(st.nextToken()); //정점

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                System.out.print(c);
                if (map[i][j] == 'R') {
                    map[i][j] = '.';
                    R = new Biz(i, j);
                    System.out.println(i + " " + j);
                }
                if (map[i][j] == 'B') {
                    map[i][j] = '.';
                    B = new Biz(i, j);
                }
            }
            System.out.println();
        }

        System.out.println(R.x + " " + R.y);
        bfs(0);

        System.out.println(-1);
    }

    static void bfs(int count) {
       Queue<Qdata> q = new LinkedList<>();

       q.add(new Qdata(R, B, count));

       while (!q.isEmpty()) {
           Qdata now = q.poll();

           Biz r_data = now.R;
           Biz b_data = now.B;
           int result = now.count + 1;

           if (result > 10) {
               break;
           }

           int c_rx = r_data.x;
           int c_ry = r_data.y;

           int c_bx = b_data.y;
           int c_by = b_data.y;

           for (int i = 0; i < dx.length; i++) {
               Biz cur_red = new Biz(c_rx, c_ry);
               Biz cur_blue = new Biz(c_bx, c_by);

               while(map[cur_red.x + dx[i]][cur_red.y + dy[i]] != '#') {
                   cur_red.x += dx[i];
                   cur_red.y += dy[i];

                   if (map[cur_red.x][cur_red.y] == '0') {
                       System.out.println(count);
                       System.exit(0);
                   }
               }

               while(map[cur_blue.x + dx[i]][cur_blue.y + dy[i]] != '#') {
                   cur_blue.x += dx[i];
                   cur_blue.y += dy[i];

                   if (map[cur_blue.x][cur_blue.y] == '0') {
                       continue;
                   }
               }

               if (map[cur_red.x][cur_red.y] == '0') {
                   System.out.println(count);
                   System.exit(0);
               }
               if (map[cur_blue.x][cur_blue.y] == '0') {
                   continue;
               }

               if (cur_blue.x == cur_red.x && cur_blue.y == cur_blue.y) {
                   if (c_rx * dx[i] > c_bx * dx[i]) cur_blue.x -= dx[i];

                   else {
                       cur_red.x -= dx[i];
                   }
                   if (c_ry * dy[i] > c_by * dy[i]) cur_blue.y -= dy[i];

                   else {
                       cur_red.y -= dy[i];

                   }
               }
           }


           System.out.println(-1);
       }
    }
    static class Biz {
        int x, y;

        public Biz(int x, int y) {
            this.x= x;
            this.y = y;
        }
    }

    static class Qdata {
        Biz R, B;
        int count;

        public Qdata(Biz R, Biz B, int count) {
            this.R = R;
            this.B = B;
            this.count = count;
        }

    }
}