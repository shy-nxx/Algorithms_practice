import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q6549_replay {
    public static int N;
    public static void main(String[] args) {
        sc.init();

        while(true) {
            N = sc.nextInt();

            if (N == 0) return;

            solve();
        }

    }

    static void solve() {
        int[] array = new int[N + 2];

        Stack<Integer> st = new Stack<>();

        int result = 0;

        st.push(0);

        for (int i = 1; i<= N; i++) {
            array[i] = sc.nextInt();
        }

        for (int i = 1; i <= N + 1; i++) {
            while(!st.empty() && array[st.peek()] > array[i]) {
                int height = array[st.peek()];
                st.pop();
                int width = i - st.peek() -1;

                result = Math.max(result, width * height);
            }
            st.push(i);
        }
        System.out.println(result);
    }

    static class sc {
        private static BufferedReader br;
        private static StringTokenizer st;

        static void init() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        static String readLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
            }
            return null;
        }

        static String readLineReplace() {
            try {
                return br.readLine().replaceAll("\\s+", "");
            } catch (IOException e) {
            }
            return null;
        }

        static String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                }
            }
            return st.nextToken();
        }

        static long nextLong() {
            return Long.parseLong(next());
        }

        static int nextInt() {
            return Integer.parseInt(next());
        }

        static double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
