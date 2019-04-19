package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_9465_sticker {
    /**
     * 상근이의 여동생 상냥이는 문방구에서 스티커 2n개를 구매했다. 스티커는 그림 (a)와 같이 2행 n열로 배치되어 있다. 상냥이는 스티커를 이용해 책상을 꾸미려고 한다.
     * <p>
     * 상냥이가 구매한 스티커의 품질은 매우 좋지 않다. 스티커 한 장을 떼면, 그 스티커와 변을 공유하는 스티커는 모두 찢어져서 사용할 수 없게 된다. 즉, 뗀 스티커의 왼쪽, 오른쪽, 위, 아래에 있는 스티커는 사용할 수 없게 된다.
     * 모든 스티커를 붙일 수 없게된 상냥이는 각 스티커에 점수를 매기고, 점수의 합이 최대가 되게 스티커를 떼어내려고 한다. 먼저, 그림 (b)와 같이 각 스티커에 점수를 매겼다. 상냥이가 뗄 수 있는 스티커의 점수의 최댓값을 구하는 프로그램을 작성하시오. 즉, 2n개의 스티커 중에서 점수의 합이 최대가 되면서 서로 변을 공유 하지 않는 스티커 집합을 구해야 한다.
     * <p>
     * 위의 그림의 경우에 점수가 50, 50, 100, 60인 스티커를 고르면, 점수는 260이 되고 이 것이 최대 점수이다. 가장 높은 점수를 가지는 두 스티커 (100과 70)은 변을 공유하기 때문에, 동시에 뗄 수 없다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken()); //테스트 케이스 개수

        for (int k = 0; k < T; k++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            int[][] stickers = new int[2][100010];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j =2; j <= N+1; j++) {
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            /**
             * 각 포인트를 비교해줘야 할 요소는 3가지이다. 그 요소는 아래 그림과 같다.
             * 즉 스티커를 선택 안했을 경우는 이전의 값과 스티커를 선택했을 때 대각선의 값
             * 그리고 마지막으로 스티커를 선택 할 경우 이전 이전의 대각선 값을 비교해야 한다.
             *
             */
            int[][] dp = new int[2][100010]; //각 max값을 저장하는 배열

            int max = 0;

            for (int i = 2; i <= N+1; i++) {
                dp[0][i] = Math.max(Math.max(dp[0][i-1] , dp[1][i-1] + stickers[0][i]), dp[1][i-2] + stickers[0][i]);
                dp[1][i] = Math.max(Math.max(dp[1][i-1] , dp[0][i-1] + stickers[1][i]), dp[0][i-2] + stickers[1][i]);
            }

            if (dp[0][N+1] > dp[1][N+1]) System.out.println(dp[0][N+1]);
            else{
                System.out.println(dp[1][N+1]);
            }
        }
    }
}