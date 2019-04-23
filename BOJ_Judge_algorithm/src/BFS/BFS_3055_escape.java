package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_3055_escape {
    /**
     * 사악한 암흑의 군주 이민혁은 드디어 마법 구슬을 손에 넣었고, 그 능력을 실험해보기 위해 근처의 티떱숲에 홍수를 일으키려고 한다. 이 숲에는 고슴도치가 한 마리 살고 있다. 고슴도치는 제일 친한 친구인 비버의 굴로 가능한 빨리 도망가 홍수를 피하려고 한다.
     * <p>
     * 티떱숲의 지도는 R행 C열로 이루어져 있다. 비어있는 곳은 '.'로 표시되어 있고, 물이 차있는 지역은 '*', 돌은 'X'로 표시되어 있다. 비버의 굴은 'D'로, 고슴도치의 위치는 'S'로 나타내어져 있다.
     * <p>
     * 매 분마다 고슴도치는 현재 있는 칸과 인접한 네 칸 중 하나로 이동할 수 있다. (위, 아래, 오른쪽, 왼쪽) 물도 매 분마다 비어있는 칸으로 확장한다. 물이 있는 칸과 인접해있는 비어있는 칸(적어도 한 변을 공유)은 물이 차게 된다. 물과 고슴도치는 돌을 통과할 수 없다. 또, 고슴도치는 물로 차있는 구역으로 이동할 수 없고, 물도 비버의 소굴로 이동할 수 없다.
     * <p>
     * 티떱숲의 지도가 주어졌을 때, 고슴도치가 안전하게 비버의 굴로 이동하기 위해 필요한 최소 시간을 구하는 프로그램을 작성하시오.
     * <p>
     * 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. 즉, 다음 시간에 물이 찰 예정인 칸으로 고슴도치는 이동할 수 없다. 이동할 수 있으면 고슴도치가 물에 빠지기 때문이다.
     */

    static int R, C;
    static char[][] map;
    static boolean[][][][] visited;

    static int[] x_move = {0,0,-1,1};
    static int[] y_move = {-1,1,0,0};

    static final int MAX = 51;

    static Queue<Pos> hedgehogQ = new LinkedList<>();
    static Queue<Pos> waterQ = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map= new char[R][C];

        for (int i = 0; i< R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);

                if (map[i][j] == 'S') {
                    hedgehogQ.add(new Pos(i, j));
                }

                if (map[i][j] == '*') {
                    waterQ.add(new Pos(i, j));
                }
            }

        }

        int result = 0;

        while(true){
            ++result;

            if (hedgehogQ.size() == 0) {
                System.out.println("KAKTUS");
                return;
            }

            extendWater();

            if (extendHedgehog()) {
                System.out.println(result);
                return;
            }

        }

    }

    static void extendWater() {

        int n = waterQ.size();

        for (int j = 0; j < n; j++) {
            Pos p = waterQ.poll();

            int x = p.x;
            int y = p.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + x_move[i];
                int ny = y + y_move[i];

                if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;

                if (map[nx][ny] == '.') {
                    map[nx][ny] = '*';
                    waterQ.add(new Pos(nx, ny));
                }

            }

        }

    }

    static boolean extendHedgehog() {

        int n = hedgehogQ.size();

        for (int j = 0; j < n; j++) {
           Pos p = hedgehogQ.poll();

           int x = p.x;
           int y = p.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + x_move[i];
                int ny = y + y_move[i];

                if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;

                if (map[nx][ny] == 'D') {
                    hedgehogQ.add(new Pos(nx, ny));
                    return true;
                }
                if (map[nx][ny] == '.') {
                    map[nx][ny] = 'S';
                    hedgehogQ.add(new Pos(nx, ny));
                }
            }

        }
        return false;

    }
    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x= x;
            this.y = y;

        }
    }

    static class Qdata {
        Pos s;
        Pos w;
        int time;

        public Qdata(Pos s, Pos w, int time) {
            this.s = s;
            this.w = w;
            this.time = time;
        }
    }
}