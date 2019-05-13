package d5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_7534 {
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        String s = "";
        for (int k = 0; k < T; k++) {
            StringBuffer sf = new StringBuffer();

            sf.append("#" + (k+1) + " ");

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            int[] seq = new int[N+1];

            boolean[] visited = new boolean[N+1];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                seq[i] = Integer.parseInt(st.nextToken());
            }

            Stack<Integer> q =  new Stack<>();

            int index = 0;

            for (int i = 1; i <= seq[index]; i++ ){
                q.push(i);
                sf.append("+");

                visited[i] = true;
            }


            boolean flag = true;
            while(!q.isEmpty()) {
                int now = q.pop();
                sf.append("-");
                index++;

                if (now < seq[index]) {
                    for (int i = 1; i <= seq[index]; i++) {
                        if (!visited[i]) {
                            q.push(i);
                            sf.append("+");
                        }
                    }
                }

                if (index == seq.length-1) {
                    int n = q.size();
                    if (n != 0) {
                        flag = false;
                        break;
                    }
                }
            }
            if (!flag) {
                sf.delete(0, sf.length());
                sf.append("#" + (k+1) + " NO");

            }
            s += sf.toString() + "\n";
        }
        System.out.print(s);

    }
}
