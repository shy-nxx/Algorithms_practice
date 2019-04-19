import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW7333 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken()); //테스트 케이스 개수

        for (int i = 0; i < t; i++ ) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            int[] weights = new int[N];

            for (int j = 0; j < weights.length; j++) {
                st = new StringTokenizer(br.readLine());
                weights[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(weights);
            solution(weights, N);
        }

    }
    public static void solution(int[] weights, int n) {
        int total = 0;
        int times = 0;

        for (int i = 0; i < n; i++) {

            if (weights[i] >= 50) {
                times++;
                weights[i] = 0;
            }

        }

        for (int i = 0; i < n-times; i++) {
            Arrays.sort(weights);
//            System.out.println(weights[n-1]);
            if (weights[n-1] < 50) {
                total += weights[n-1];
            }
        }
        int div = total / 50;
//        System.out.println(div);
        times += div;
        System.out.println(times);
    }

}
