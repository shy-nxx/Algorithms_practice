package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DFS_2617_find_biz {
    /**
     * 모양은 같으나, 무게가 모두 다른 N개의 구슬이 있다. N은 홀수이며, 구슬에는 번호가 1,2,...,N으로 붙어 있다. 이 구슬 중에서 무게가 전체의 중간인 (무게 순서로 (N+1)/2번째) 구슬을 찾기 위해서 아 래와 같은 일을 하려 한다.
     * <p>
     * 우리에게 주어진 것은 양팔 저울이다. 한 쌍의 구슬을 골라서 양팔 저울의 양쪽에 하나씩 올려 보면 어느 쪽이 무거운 가를 알 수 있다. 이렇게 M개의 쌍을 골라서 각각 양팔 저울에 올려서 어느 것이 무거운 가를 모두 알아냈다. 이 결과를 이용하여 무게가 중간이 될 가능성이 전혀 없는 구슬들은 먼저 제외한다.
     * <p>
     * 예를 들어, N=5이고, M=4 쌍의 구슬에 대해서 어느 쪽이 무거운가를 알아낸 결과가 아래에 있다.
     * <p>
     * 구슬 2번이 구슬 1번보다 무겁다.
     * 구슬 4번이 구슬 3번보다 무겁다.
     * 구슬 5번이 구슬 1번보다 무겁다.
     * 구슬 4번이 구슬 2번보다 무겁다.
     * 위와 같이 네 개의 결과만을 알고 있으면, 무게가 중간인 구슬을 정확하게 찾을 수는 없지만, 1번 구슬과 4번 구슬은 무게가 중간인 구슬이 절대 될 수 없다는 것은 확실히 알 수 있다. 1번 구슬보다 무거운 것이 2, 4, 5번 구슬이고, 4번 보다 가벼운 것이 1, 2, 3번이다. 따라서 답은 2개이다.
     * <p>
     * M 개의 쌍에 대한 결과를 보고 무게가 중간인 구슬이 될 수 없는 구슬의 개수를 구하는 프로그램을 작성하시오.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); //쌍의 개수

        int[][] bizz = new int[101][101];

        int half = (N/2) + 1;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bizz[a][b] = 1; //앞 구슬 > 뒷 구슬
            bizz[b][a] = -1; //앞 구슬 < 뒷 구슬
        }

        //중간이 될 수 없는 구슬의 개수

        for (int a = 1; a <= N; a++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    //i < a , i<a && a < j -> i < j
                    //a < i, a < i && j < a -> i > j
                    //i는 a보다 크지 않고 ,
                    if (bizz[i][a] != 0 && bizz[i][a] == bizz[a][j]) {
                        bizz[i][j] = bizz[i][a];
                    }
                }
            }
        }

        int[] big = new int[101];
        int[] small = new int[101];

        for (int i = 1; i <= N; i++ ) {
            for (int j = 1; j <= N; j++) {
                if (i==j) {
                    big[i]++;
                    small[i]++;
                }
                if (bizz[i][j] == 1) {
                    big[i]++;
                }
                if (bizz[i][j] == -1) {
                    small[i]++;
                }
            }
        }

        int ans = 0;

        for (int i = 1; i <= N; i++ ) {
            if (big[i] > half) ans++;
            if (small[i] > half) ans++;
        }


        System.out.println(ans);
    }
}