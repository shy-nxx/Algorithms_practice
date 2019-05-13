import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW14890 {
    /**
     * 길을 지나오면서 연속된 수에 대해서는 카운터를 증가시킨다.
     * 연속되지 않는 수를 만난다면, 높은 칸인지 낮은 칸인지 확인한다.
     * 높은 칸이라면, 쌓아온 카운터를 통해 경사로를 배치할 수 있는 지 확인한다.
     * 낮은 칸이라면, L만큼 지나가면서 경사로를 배치할 수 있는 지 연속 유무를 확인한다.
     *
     */
    static int N, L;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1,0}; //오른쪽, 아래로만 이동 (일직선 이동)
    static int[] dy = {0,1};
    static int[] height;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken()); // 경사로의 길이

        map = new int[N*2][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = N; i < N*2; i++ ) {
            for (int j = 0; j < N; j++) {
                map[i][j] = map[j][i-N];
            }
        }

//        for (int i = 0; i < N*2; i++) {
//
//            for (int j = 0; j < N; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
        /**
         * 6 2
         * 3 3 3 3 3 3 -> ok
         * 2 3 3 3 3 3 -> not ok
         * 2 2 2 3 2 3 -> not ok (높이가 1차이가 아님)
         * 1 1 1 2 2 2 -> ok
         * 1 1 1 3 3 1 -> not ok
         * 1 1 2 3 3 2 -> not ok
         * ok
         *
         */

        int count = N*2; //원래 n제곱의 길이 존재함


        for (int i = 0; i < N*2; i++) {
            int target = map[i][0];

            height = new int[11];
            height[target] = 1;
            int j = 1;

            while (j < N) {
                int next = map[i][j];
                if (!validHeight(target, next)) {
                    // 높이 차가 1보다 크면 경사로 배치 불가능
                    --count;
                    break;
                }
                // 높은 칸을 만났을 경우 => 지나온 길을 통해 경사로 배치 판단 가능.
                // 낮은 칸을 만났을 경우 => 낮은 칸부터 길을 지나가면서 경사로를 배치할 수 있는 지 판단해야함.


                if (target != next) {
                    if (target < next) {
                        //높은 칸을 만났을 경우
                        if (!high(target, next)) {
                            --count;
                            break;
                        }
                    }
                    else {
                        if (!low(i, j, target, next)) {
                            --count;
                            break;
                        }
                        j += L -1; //낮은 칸이라면, L만큼 지나가면서 경사로를 배치할 수 있는 지 연속 유무를 확인한다.
                    }
                    target = next;

                }
                else {
                    // 같은 높이의 칸을 뜻하기 때문에, L의 길이를 갖는 경사로 배치 판단을 위한 카운터 증가
                    height[target]++;
                }
                j++;
            }
        }

        System.out.println(count);
    }

    static boolean validHeight(int target, int next) {
        if (Math.abs(target - next) > 1) {
            return false;
        }
        return true;
    }

    static boolean high(int target, int next) {
        if (height[target] < L) {
            return false; //경사로 건설 X
        }

        height[target] = 0;
        height[next] = 1;

        return true;
    }

    static boolean low(int i, int j, int target, int next) {
        // 길이가 L인 경사로를 놓을 수 있는지만 판단하면 되기에 l 만큼 돌린다.

        for (int k = 0; k < L; k++) {
            if (j + k == N) {
                break;
            }
            if (map[i][j+k] == next)
                height[next]++;
        }
        if (height[next] < L) {
            return false;
        }
        height[next] -= L;
        return true;

    }
}