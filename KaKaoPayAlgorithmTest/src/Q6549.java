import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q6549 {
    //백준알고리즘 분할정복 6549 가장 큰 직사각형
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       while (true) {
            // Input
            String input = br.readLine();

            if (input.equals("0")) return;

            StringTokenizer st = new StringTokenizer(input);

            int N = Integer.parseInt(st.nextToken());

            int[] histogram = new int[N];
            for (int i = 0; i < N; i++) histogram[i] = Integer.parseInt(st.nextToken());

//            // Solve
//            SegmentTree segmentTree = new SegmentTree(histogram, N);
//
//            System.out.println(queryMaxArea(histogram, segmentTree, N, 0, N - 1));

        }

    }
}
