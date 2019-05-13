package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class SWEA_5644 {

    static int M, A, C, P;
    //0 -> 이동 X / 1-> 상 (북) / 2 -> 우(동) / 3 -> 하 (남) / 4 -> 좌 (서)
    static int[] dx = {0,0,1,0,-1};
    static int[] dy = {0,-1,0,1,0};

    static int[][] directions;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        StringBuffer sf = new StringBuffer();

        for (int k = 0; k < T; k++) {
            sf.append("#" + (k+1) + " ");
            st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken()); //이동시간
            A = Integer.parseInt(st.nextToken()); //BC의 개수

            directions = new int[2][M+1];
            dp = new boolean[2][A];

            for (int j = 0; j < 2; j++) {

                st = new StringTokenizer(br.readLine());
                for (int i = 1; i <= M; i++) {
                    int dir = Integer.parseInt(st.nextToken());
                    directions[j][i] = dir;
                }
            }

            BC[] bcs = new BC[A];
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                bcs[i] = new BC(x,y,c,p);
            }

            /**
             * 현재 좌표와 bc와의 거리가c이하-> 맨해튼 거리처럼 x1-x2 + y1-y2 이면 BC의 거리에 해당됨
             * 바이러스 문제처럼 퍼지게 하고 DFS로 사람 이동시키고 만약 같은 곳에서 만나면 P를 나누고 더 이득인 것으로 취하게 한다.
             * A는 1,1에서 출발하고 B는 10,10에서 출발
             *
             * path[2][A] 배열을 만들어서 매 시간마다 사용자 A 와 B 가 접근할 수 있는 모든 BC를 체크 한 후,
             * getMax 라는 함수를 통해 A와 B가 선택 가능한 BC의 모든 조합을 조사해 그중에서 최대 배터리 충전량을 구하는 식으로 문제를 해결했다.
             */

            int ax =1, ay = 1;
            int bx =10, by = 10;

            int sum = 0;

            for (int i = 0; i <= M; i++) {
                //일단 방향에 맞게이동 시킴
                int nax = ax + dx[directions[0][i]];
                int nay = ay + dy[directions[0][i]];

                int nbx = bx +  dx[directions[1][i]];
                int nby = by +  dy[directions[1][i]];

                Arrays.fill(dp[0], false);
                Arrays.fill(dp[1], false);


                for (int j = 0; j < A; j++) {
                    if (checkRange(nax, nay, bcs[j])) {
                        dp[0][j] = true;
                    }
                    if (checkRange(nbx, nby, bcs[j])) {
                        dp[1][j] = true;
                    }
                }

                sum += getMax(bcs);
                ax = nax;
                ay = nay;
                bx = nbx;
                by = nby;

            }
            sf.append(sum + "\n");
        }

        System.out.println(sf.toString());
    }

    static int getMax(BC[] bcs) {
        int max = 0;

        for (int i = 0; i < A; i++) {
            for (int j = 0; j < A; j++) {
                int temp = 0;
                if (dp[0][i]) { //i번째 충전소의 범위에 있을 경우
                    if (dp[1][j]) { //ㄷㅏ른 사용자와 겹칠 경우
                        temp = i == j ?  bcs[j].p : bcs[i].p + bcs[j].p;
                    }
                    else {
                        temp = bcs[i].p;
                    }
                }
                else {
                    if (dp[1][j]) {
                        temp = bcs[j].p;
                    }
                }
                max = Math.max(max, temp);
            }
        }
        return max;
    }
    static boolean checkRange(int x, int y, BC bcs) {
//        if (x < 1 || x > 10 || y < 1 || y > 10) return false;

        int range = Math.abs(x- bcs.x) + Math.abs(y - bcs.y);
        if (range <= bcs.c) return true;
        return false;
    }

    static class BC {
        int x,y;
        int c;
        int p;
        public BC(int x, int y, int c, int p) {
            super();
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;
        }


    }
}
