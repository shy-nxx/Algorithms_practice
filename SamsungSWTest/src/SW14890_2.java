import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW14890_2 {
    static int N, L;
    static int[][] map;
    static int[] height;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken()); // 경사로의 길이

        map = new int[N*2][N]; //(i+.., j) / (i, j+...) 둘 다 살펴봐야 함

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = N; i < N*2; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = map[j][i-N];
            }
        }

        int count = 0;

        for (int i = 0; i < N*2; i++) {
            int target = map[i][0]; //처음 비교할 값

            height = new int[11];
            height[target] = 1;

            int j = 1;
            while(j < N) {

                int next = map[i][j];
                //크기 비교
                //높이가 1이상 차이 날 때
                if (!validHeight(target, next)) {
                    --count;
                    break;
                }

                if (target != next) {
                    if (target < next) {
                        //경사로를 세울지 말지 결정 -> 지나온 길을 통해 경사로 배치 판단 가능.
                        if (!high(target, next)) {
                            --count;
                            break;
                        }
                    }
                    else {
                        //낮은 칸부터 길을 지나가면서 경사로를 배치할 수 있는 지 판단해야함.(L만큼의 작은 블록들이 존재하는지 확인)
                        if (!low(i, j, target, next)) {
                            --count;
                            break;
                        }
                        j += L-1;
                    }
                    target = next;

                }
                else{
                    height[target]++;
                }
                j++;
            }
        }
    }
    public static boolean validHeight(int target, int next) {
        if (Math.abs(target-next) != 1)
            return false;

        return true;
    }

    public static boolean high(int target, int next) {
        if (height[target] < L)
            return false;

        height[target] = 0;
        height[next] = 1;
        return true;
    }

    public static boolean low(int i, int j, int target, int next) {
        //길을 지나가면서 경사로를 배치할 수 있는 지 판단해야함.(L만큼의 작은 블록들이 존재하는지 확인)
        for (int k = 0; k < L; k++) { //L만큼만 확인
            if (j + k == N) //영역을 벗어나면 안됨
                break;

            if (map[i][j+k] == next) {
                height[next]++;
            }
        }
        if (height[next] < L) {
            return false;
        }
        height[next] -= L;
        return true;
    }
}