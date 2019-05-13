
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SWEA_5658 {
    static int N, K;
    static String s;

    static LinkedList<Integer> list = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T =  Integer.parseInt(st.nextToken());

        StringBuffer sf = new StringBuffer();
        for (int k=0; k < T; k++) {
            sf.append("#" + (k+1) + " ");
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken()); //N개의 16진수 숫자
            K = Integer.parseInt(st.nextToken()); //K번째 큰 수를 찾아라

            list = new LinkedList<>();

            s = br.readLine();

            //3개씩 끊어서 읽어야 한다.
            for (int i = 0; i < N/4; i++) { //N/4번 후에는 처음과 같은 상태가 된다.
                findPWD();
                rotate();
            }

            Collections.sort(list);

            String temp = Integer.toString(list.get(list.size()-K));
            int ans = Integer.parseInt(temp, 10);
            sf.append(ans + "\n");

        }
        System.out.println(sf.toString());
    }
    static void findPWD() {
        int[] side = new int[4];
        int k = N/4;

        int idx = 0;
        for (int i = 0; i < N; i += k) {
            String temp = s.substring(i, i+k);
            side[idx++] = Integer.parseInt(temp, 16);

        }

        for (int i = 0; i < 4; i++) {
            if (!list.contains(side[i])) {
                list.add(side[i]);
            }
        }
    }

    static void rotate() {
        String last = s.substring(N-1);
        s = last + s.substring(0, N-1);
    }
}
