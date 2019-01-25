import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q6549 {
    public static int[] histogram;
    public static int N;
    //백준알고리즘 분할정복 6549 가장 큰 직사각형
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       while (true) {
            // Input
            String input = br.readLine();

            if (input.equals("0")) return;

            StringTokenizer st = new StringTokenizer(input);

            N = Integer.parseInt(st.nextToken());

            histogram = new int[N];
            for (int i = 0; i < N; i++) histogram[i] = Integer.parseInt(st.nextToken());

            solve();
//            // Solve
//            SegmentTree segmentTree = new SegmentTree(histogram, N);
//
//            System.out.println(queryMaxArea(histogram, segmentTree, N, 0, N - 1));

        }

    }
    public static void solve() {

        Stack<Integer> stack = new Stack<Integer>();
        int ans = 0;

        stack.push(0);

        for (int i = 1; i < N; i++) {

            while(!stack.isEmpty() && histogram[stack.peek()] > histogram[i]) {
                int height = histogram[stack.peek()];

                int width = i - stack.peek() - 1;
                stack.pop();

                ans = Math.max(ans, width * height);
            }
            stack.push(i);
        }

        System.out.println(ans);
    }


}
