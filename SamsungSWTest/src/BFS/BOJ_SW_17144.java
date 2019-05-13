package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_SW_17144 {
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
                    list.add(new Air(i, j,flag)); //0 ->up / 1 ->  down
                    flag = 1;
                }
            }
        }

        //미세먼지 확산 -> 공기청정기가 있으면 확산 X
        //확산되는 양은 Ar,c/5이고 소수점은 버린다.
        //(r, c)에 남은 미세먼지의 양은 Ar,c - (Ar,c/5)×(확산된 방향의 개수) 이다.
        //공기청정기 작동 -> 위쪽 공기청정기의 바람은 반시계방향으로 순환하고, 아래쪽 공기청정기의 바람은 시계방향으로 순환한다.
        //바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동한다.

        for (int i = 1; i <= T; i++) {
            spread_dust();

            printMap(map);
            System.out.println();

            turn_on_airCondition();
        }


        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        int count = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) count+= map[i][j];
            }

        }
        System.out.println(count);

    }
    static void spread_dust() {
        int[][] c_map = new int[R][C];
        copyMap(c_map, map);

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != -1 && map[i][j] != 0) {
                    int index = 0;
                    int value = map[i][j]; //기존 값 보존
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
                    c_map[i][j] -= value / 5 * index;

                }
            }


        }
        copyMap(map, c_map); //미세먼지 퍼진 지도를 복사

    }
    static void turn_on_airCondition() {
        for (int i = 0; i < 2; i++) {
            Air a = list.get(i);

            if (a.upOrDown == 0) { //반시계 방향 -> 역순으로 회전하며 대입한다.
                int nx = a.y;
                int ny = a.x;

                //위에서 아래로
                for (int j = ny -1; j > 0; j--) {
                    map[j][nx] = map[j-1][nx];

                }

                //오른쪽에서 왼쪽으로
                for (int j = 0; j < C-1; j++) {
//                    System.out.println(map[0][j] + " " + map[0][j+1]);
                    map[0][j] = map[0][j+1];
                }

                //아래에서 위로
                for (int j = 0; j< ny; j++) {
                    System.out.println(map[j][C-1] + " " +map[j+1][C-1]);
                    map[j][C-1] = map[j+1][C-1];
                }

                //왼쪽에서 오른쪽으로
                for (int j = C-1; j > nx + 1; j--) {
                    map[ny][j] = map[ny][j-1];
                }
                map[ny][nx+1] = 0;
            }
            if (a.upOrDown == 1) {
                int nx = a.y;
                int ny = a.x;

                //아래로 당기기
                for (int j = ny +1; j < R; j++) {
                    map[j][nx] = map[j+1][nx];
                }

                //오른쪽
                for (int j = 0; j < C-1; j++) {
                    map[R-1][j] = map[R-1][j+1];
                }

                //^
                for (int j = R-1; j > ny-1; j--) {
                    map[j][C-1] = map[j-1][C-1];
                }

                //왼쪽
                for (int j = C-1; j > nx + 1; j--) {
                    map[ny][j] = map[ny][j-1];
                }
                map[ny][nx+1] = 0;
            }
        }
    }

    static void printMap(int[][] map ){
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }
    static void copyMap(int[][] c_map, int[][] prev_map) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                c_map[i][j] = prev_map[i][j];
            }
        }
    }
    static class Dust {
        int x, y;

        public Dust(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }


    }
    static class Air {
        int x, y;
        int upOrDown;

        public Air(int x, int y, int upOrDown) {
            this.x = x;
            this.y = y;
            this.upOrDown = upOrDown;
        }
    }
}
