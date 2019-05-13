package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17144 {
    static int R, C, T;

    static int[][] map = new int[51][51];

    static int[] dx = {0,-1,0,1}; //동북서남 -> 반시계방향 / 시계방향 - 동남서북
    static int[] dy = {1,0,-1,0};

    static ArrayList<Air> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        int flag = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == -1) {
                    list.add(new Air(i, j, flag)); //0은 위 / 1은 아래
                    flag = 1;
                }
            }
        }

        //미세먼지 확산 -> 공기청정기가 있으면 확산 X
        //확산되는 양은 Ar,c/5이고 소수점은 버린다.
        //(r, c)에 남은 미세먼지의 양은 Ar,c - (Ar,c/5)×(확산된 방향의 개수) 이다.
        //공기청정기 작동 -> 위쪽 공기청정기의 바람은 반시계방향으로 순환하고, 아래쪽 공기청정기의 바람은 시계방향으로 순환한다.
        //바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동한다.

        for (int i = 0; i < T; i++) {
            spreadDust();
//            printMap(map);
            turnOnAirCond();
        }

        int result = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) result += map[i][j];
            }
        }
        System.out.println(result);

    }
    static void printMap(int[][] map ){
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static void spreadDust() {
        int[][] c_map = new int[R][C];
        copyMap(c_map, map); //기존 맵 보존

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != 0 || map[i][j] != -1  ) {
                    int index = 0;
                    int value = map[i][j]; //기존 값저장
                    int spread_dust = value / 5;

                    //미세먼지 확산시키기
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                        if (map[nx][ny] == -1) continue;

                        c_map[nx][ny] += spread_dust;
                        index++;

                    }
                    c_map[i][j] -= value/5 * index;
                }
            }
        }
        copyMap(map, c_map);
    }

    static void turnOnAirCond() {
        for (int i = 0; i < 2; i++) {
            Air a = list.get(i);

            if (a.upOrDown == 0) {
                int nx = a.y;
                int ny = a.x;

                //위아래
                for (int y = ny -1; y > 0; y--) {
                    map[y][nx] = map[y-1][nx];
                }
                //오른쪽->왼쪽
                for (int x = 0; x < C-1; x++) {
                    map[0][x] = map[0][x+1];
                }

                //밑-> 위
                for (int y = 0; y < ny; y++) {
                    map[y][C-1] = map[y+1][C-1];
                }

                //왼쪽 -> 오른쪽
                for (int x = C-1; x > nx+1; x-- ) {
                    map[ny][x] = map[ny][x-1];
                }
                map[ny][nx+1] = 0;
            }
            if (a.upOrDown == 1) {
                int nx = a.y;
                int ny = a.x;

                //위아래
                for (int y = ny +1; y < R; y++) {
                    map[y][nx] = map[y+1][nx];
                }
                //오른쪽->왼쪽
                for (int x = 0; x < C-1; x++) {
                    map[R-1][x] = map[R-1][x+1];
                }

                //밑-> 위
                for (int y = R-1; y > ny-1; y--) {
                    map[y][C-1] = map[y-1][C-1];
                }

                //왼쪽 -> 오른쪽
                for (int x = C-1; x > nx+1; x-- ) {
                    map[ny][x] = map[ny][x-1];
                }
                map[ny][nx+1] = 0;
            }

        }
    }
    static void copyMap(int[][] c_map, int[][] prev_map) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                c_map[i][j] = prev_map[i][j];
            }
        }
    }

    static class Air {
        int x, y;
        int upOrDown;

        public Air(int x, int y, int upOrDown) {
            super();
            this.x = x;
            this.y = y;
            this.upOrDown = upOrDown;
        }
    }
    static class Dust{
        int x,y;

        public Dust(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }


    }
}
