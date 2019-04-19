package DFS.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW14684_bruteForce {
    /**
     * 배열에 1로 체크된 값은 해당 위치에서 오른쪽으로 사다리가 존재한다고 생각하는 것이다. 따라서 인접한 열에 1 이 있지 않도록 가능한 모든 경우를 체크한다.
     *
     * 이렇게 생각하고 실제 탐색할때는 내 위치와 내 왼쪽 위치를 탐색한다.
     *
     * 1. 내 위치에 1이 있다. 오른쪽 사다리로 가라.
     * 2. 내 왼쪽에 1이 있다. 왼쪽 사다리로 가라
     */
    static int N, M, H;
    static int[][] matrix;
    static boolean[][] visited;
    static int MIN = Integer.MAX_VALUE;
    static int[] dx = {1, 0, 0};
    static int[] dy = {0, -1, 1};
    static boolean ok;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        if (M == 0) {
            max = 0;
            System.out.println(max);
            System.exit(0);
        }
        matrix = new int[H+2][N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            matrix[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;

        }

        for (int i = 0; i < 3; i++) { //사다리는 세개까지만 가능
            max = i;
            solve(1,1,0);
            if (ok) break;
        }

        System.out.println((ok) ? max : -1);

    }
    public static void solve(int x, int y, int cnt) {
        if (ok) {
            return;
        }

        if (max == cnt) {
            if (check()) { //각자 자기 자리로 도착하는 지 확인
                ok = true;
            }
            return;
        }

        for (int i = (y < N ? x : x+1); i <= H; i++) {
            //y < N 이면 x || y >= N 이면 x+1 ?? (가로의 끝까지 가면 다음 칸으로 넘어가야 해서///?
            for (int j = 1; j < N; j++) {
                if (matrix[i][j] == 1 || matrix[i][j-1] == 1 || matrix[i][j+1] == 1) { //내자리 / 왼쪽자리 / 오른쪽 자리 탐색
                    continue; //이미 있으니까 백트래킹 필요없음
                }
                matrix[i][j] = 1;
                solve(i, j, cnt+1);
                matrix[i][j] = 0; //백트래킹

            }
        }
    }
    static boolean check() {
        for (int i = 1; i <= N; i++) {
            int j = 1; // 현재 세로선??
            int temp = i;

            while(j <= H+1) {
                if (matrix[j][temp] == 1) {
                    //오른쪽으로 이동
                    temp++;
                }
                else if (matrix[j][temp-1] == 1){
                    temp--;
                }
                j++;

            }
            if (i != temp) {
                return false;
            }

        }
        return true;
    }
}