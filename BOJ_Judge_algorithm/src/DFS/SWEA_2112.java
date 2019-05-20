package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2112 {
    static int D, W, K;
    static int[][] map;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        StringBuffer sf = new StringBuffer();

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());


            map = new int[D][W];

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken()); //0-> A / 1->B
                }
            }

            min = Integer.MAX_VALUE;

            sf.append("#" + tc + " ");
            if (K == 1 ) {
               sf.append(0 + "\n");
               continue;
            }
            else {
                dfs(0,0, map);
            }
            sf.append(min + "\n");
        }

        System.out.println(sf.toString());
    }
    static void dfs(int cur, int cnt, int[][] cur_map) {
        if(cnt >= min) return;
        if(cur == D) return;

        if (isThereK(cur_map)) {
            min = Math.min(min, cnt);
            return;
        }

        int[][] c_map = new int[D][W];

        for (int i = 0; i < D; i++) {
            for (int j = 0; j < W; j++) {
                c_map[i][j] = cur_map[i][j];

            }
        }

        dfs(cur+1, cnt, cur_map);
        for (int i = 0; i < W; i++) {
            c_map[cur][i] = 0;

        }
        dfs(cur+1, cnt+1, c_map);
        for (int i = 0; i < W; i++) {
            c_map[cur][i] = 1;

        }
        dfs(cur+1, cnt+1, c_map);

    }

    static boolean isThereK(int[][] cur_map) {
        boolean flag = false;

        for (int i = 0; i < W; i++) {
            for (int j = 0; j < D-K+1; j++) {
                for (int k = 1; k < K; k++) {
                    if (cur_map[j][i] != cur_map[j+k][i]) {
                        flag = false;
                        break;
                    }
                    else {
                        flag = true;
                    }

                }
                if (flag) break; //이미 충족해서 다른 행의 값을 더 볼 필요가 없다.
            }
            if (!flag) break; //하나의 열이라도 충족되지 않으면 그냥 멈추고 false 리턴
        }

        if (flag) return true;
        else return false;

    }
}
