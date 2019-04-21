package DP.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jump_DP {
    /**
     * 각 칸에 적힌 숫자만큼 오른쪽 혹은 아래로 점프를 한다. 칸에 적힌 수가 0일 경우 (가장 오른쪽 칸) 더 이상 이동하지 않는다.
     * <p>
     * DP 로 현재 위치에서 오른쪽 ( x + map[x][y] < N / y + map[x][y] < N 일 경우를 모두 고려해서 해당되는 경우에 dp[이동한 칸][] += dp[i][j]로 값을 갱신해준다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] jump_map = new int[N][N];

        int[][] dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                jump_map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = 1;

        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++) {
                if ((i == N -1 && j == N-1 ) || dp[i][j] == 0) continue; //dp[0][0] 절대 지나갈 수 없는 경로
                int jump = jump_map[i][j];
                int nx = jump + i;
                int ny = jump + j;

                if (nx < N) {
                    dp[nx][j] += dp[i][j];
                }
                if (ny < N) {
                    dp[i][ny] += dp[i][j];
                }
            }
        }
        System.out.println(dp[N-1][N-1]);
    }
}