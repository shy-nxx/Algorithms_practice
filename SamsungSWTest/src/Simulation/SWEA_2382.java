package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class SWEA_2382 {
    static int N, M, K;

    static int[][] map;
    static Info[] infos = new Info[1001];
    static boolean isIntegrated[] = new boolean[1001];

    static final int up = 1;
    static final int down = 2;
    static final int left = 3;
    static final int right = 4;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        StringBuffer sf = new StringBuffer();

        for (int z = 0; z < T; z++) {

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //구역 셀의 개수 (5 ≤ N ≤ 100)
            M = Integer.parseInt(st.nextToken()); //격리 시간  (1 ≤ M ≤ 1,000)
            K = Integer.parseInt(st.nextToken()); //미생물 군집의 개수 (5 ≤ K ≤ 1,000)
            //각 군집 내 미생물 수는 1 이상 10,000 이하의 정수이다.
            //(상: 1, 하: 2, 좌: 3, 우: 4)
            //동일한 셀에 같은 미생물 수를 갖는 두 군집이 모이는 경우는 발생하지 않는다.
            //  각 군집의 정보는 세로 위치, 가로 위치, 미생물 수, 이동 방향 순으

            map = new int[101][101];
            infos = new Info[1001];
            isIntegrated = new boolean[1001];

            for (int i = 0; i < K; i++ ) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                infos[i] = new Info(x, y, cnt, dir);
                map[infos[i].x][infos[i].y]++; //해당 좌표에 미생물이 개수를 표시
            }
            infos[K] = new Info(0,0,0,0);

            for (int i = 1; i <= M; i++) {
                dfs();
            }


            sf.append("#" + (z+1) + " " + getCount() + "\n");
        }
        System.out.println(sf.toString());
    }
    /**
     * ① 최초 각 미생물 군집의 위치와 군집 내 미생물의 수, 이동 방향이 주어진다. 약품이 칠해진 부분에는 미생물이 배치되어 있지 않다. 이동방향은 상, 하, 좌, 우 네 방향 중 하나이다.

     ② 각 군집들은 1시간마다 이동방향에 있는 다음 셀로 이동한다.

     ③ 미생물 군집이 이동 후 약품이 칠해진 셀에 도착하면 군집 내 미생물의 절반이 죽고, 이동방향이 반대로 바뀐다.
     미생물 수가 홀수인 경우 반으로 나누어 떨어지지 않으므로, 다음과 같이 정의한다.
     살아남은 미생물 수 = 원래 미생물 수를 2로 나눈 후 소수점 이하를 버림 한 값
     따라서 군집에 미생물이 한 마리 있는 경우 살아남은 미생물 수가 0이 되기 때문에, 군집이 사라지게 된다,

     ④ 이동 후 두 개 이상의 군집이 한 셀에 모이는 경우 군집들이 합쳐지게 된다.
     합쳐 진 군집의 미생물 수는 군집들의 미생물 수의 합이며, 이동 방향은 군집들 중 미생물 수가 가장 많은 군집의 이동방향이 된다.
     합쳐지는 군집의 미생물 수가 같은 경우는 주어지지 않으므로 고려하지 않아도 된다.

     */
    static int getCount() {
        int count = 0;
        for (int i = 0; i < K; i++) {
            if (!isIntegrated[i]) count += infos[i].cnt;
        }
        return count;
    }

    static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < K; i++) {
            System.out.print(isIntegrated[i] + " " + infos[i].cnt + " ");
        }
        System.out.println();
    }
    static void dfs() {
        //해당하는 방향으로 움직임 -> 다같이 움직여야 해서 행동 단위로 끊음
        for (int i = 0; i < K; i++) {
            if (!isIntegrated[i]) {
                if (infos[i].dir == up) { //상
                    map[infos[i].x][infos[i].y]--;
                    infos[i].x--;
                    map[infos[i].x][infos[i].y]++;
                }
                else if (infos[i].dir == down) { //하
                    map[infos[i].x][infos[i].y]--;
                    infos[i].x++;
                    map[infos[i].x][infos[i].y]++;
                }
                else if (infos[i].dir == left) { //좌
                    map[infos[i].x][infos[i].y]--;
                    infos[i].y--;
                    map[infos[i].x][infos[i].y]++;
                }
                else {//우
                    map[infos[i].x][infos[i].y]--;
                    infos[i].y++;
                    map[infos[i].x][infos[i].y]++;
                }
            }
        }
        //같은 좌표에 미생물이 여러개 일 경우 통합함
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 1) {
                    //통합해야함
                    integrate(i, j);
                }
            }

        }

        //약물에 있는 영역에 갈 경우
        for (int i = 0; i < K; i++) {
            if (!isIntegrated[i]) {
                if (infos[i].x == 0 || infos[i].y == 0 || infos[i].x == N-1 || infos[i].y == N-1) {
                    infos[i].cnt /= 2;
                    if (infos[i].dir == 1) { //
                        infos[i].dir = 2;
                    }
                    else if (infos[i].dir == 2) { //
                        infos[i].dir = 1;
                    }
                    else if (infos[i].dir == 3) { //
                        infos[i].dir = 4;
                    }
                    else {//
                        infos[i].dir = 3;
                    }
                }
            }
        }

    }
    static void integrate(int x, int y) {
        int maxCnt = -1, maxIndex = K; //최대 1000까지 존재할 수 있음 N이 최대가 1000이라서
        int cnt = 0;

        for (int i = 0; i < K; i++) { //근데 두 개 이상 모여있을 수도 있지 않은가? -> 처리해야함
            if (!isIntegrated[i]) {
                if (infos[i].x == x && infos[i].y == y) {
                    cnt += infos[i].cnt;

                    if (infos[i].cnt > maxCnt) {
                        isIntegrated[maxIndex] = true;
                        maxCnt = infos[i].cnt;
                        maxIndex = i;
                    }

                    else {
                        isIntegrated[i] = true;
                    }
                }
            }
        }

        infos[maxIndex].cnt = cnt;
    }

    static class Info {
        int x,y;
        int cnt;
        int dir;

        public Info(int x, int y, int cnt, int dir) {
            super();
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.dir = dir;
        }


    }
}
