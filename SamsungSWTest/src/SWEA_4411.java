

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SWEA_4411 {
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int k = 0; k < T; k++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            long K = Long.parseLong(st.nextToken());

            LinkedList<Integer> list = new LinkedList<>();

            for (int i = 1; i <= N; i++) {
                list.add(i);
            }

            long index = 0;
            while(!list.isEmpty()) {
                for (long i = 1; i<= K; i++) {
                    list.add(list.pollFirst());
                }
                index = list.pollFirst();


            }
            System.out.println(index);
        }
    }
}
