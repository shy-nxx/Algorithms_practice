package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class DP_1932_intTriangle {
    /**
     *         7
     *       3   8
     *     8   1   0
     *   2   7   4   4
     * 4   5   2   6   5
     *
     * 위 그림은 크기가 5인 정수 삼각형의 한 모습이다.
     * <p>
     * 맨 위층 7부터 시작해서 아래에 있는 수 중 하나를 선택하여 아래층으로 내려올 때, 이제까지 선택된 수의 합이 최대가 되는 경로를 구하는 프로그램을 작성하라. 아래층에 있는 수는 현재 층에서 선택된 수의 대각선 왼쪽 또는 대각선 오른쪽에 있는 것 중에서만 선택할 수 있다.
     * <p>
     * 삼각형의 크기는 1 이상 500 이하이다. 삼각형을 이루고 있는 각 수는 모두 정수이며, 범위는 0 이상 9999 이하이다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //삼각형의 크기

//        ArrayList<Integer> list = new ArrayList<>();
////
////        for (int i = 0; i < N; i++) {
////            st = new StringTokenizer(br.readLine());
////            for (int j = 0; j < i+1; j++)
////                list.add(Integer.parseInt(st.nextToken()));
////        }

/*        int[] tree = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            tree[i] = list.get(i);
        }*/

//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(tree[i]);
//        }
//
        /**
         * 인덱스 보존이 안됨 -> 최대값이 다르게 나온다.
         */
//        int[] dp= new int[tree.length/2];
//        dp[0] = tree[0];
//
//        //트리 왼쪽 자식은 i*2-1 오른쪽 자식은 i*2
//        for (int i = 1; i < tree.length/2; i++) {
//            dp[i] = Math.max(tree[i*2] + dp[i-1], tree[i*2+1] + dp[i-1]);
//            System.out.println(dp[i]);
//        }


        /**
         * (1,1) (2,1) (3,1) (4,1) (5,1) -> list[i-1][j] + list[i][j]; ( j==1)
         * (1,1) (2,2) (3,3) (4,4) (5,5) -> list[i-1][j-1] + list[i][j]
         * (3,2) (4,2) (4,3) (5,2) (5,3) (5,4) -> 부모 후보 중 큰 값을 구한다. (5,4)-> (4,3) or (4,4) -> list[i][j] = max(list[i-1][j-1] + list[i-1][j]) + list[i][j]
         */

        int[][] list = new int[500][500];

        int sum = 0;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                list[i][j] = Integer.parseInt(st.nextToken());
                if (j == 1) {
                    list[i][j] += list[i-1][j];
                }
                else if (i==j) {
                    list[i][j] += list[i-1][j-1];
                }
                else {
                    list[i][j] = Math.max(list[i-1][j-1], list[i-1][j]) + list[i][j];
                }

                if (list[i][j] > sum) {
                    sum = list[i][j];
                }
            }
        }

        System.out.println(sum);

    }
}