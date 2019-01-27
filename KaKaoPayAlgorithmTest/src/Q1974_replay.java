import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q1974_replay {
    /*
     입력값
        8
        4
        3
        6
        8
        7
        5
        2
        1
      출력값
        +
        +
        +
        +
        -
        -
        +
        +
        -
        +
        +
        -
        -
        -
        -
        -
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Stack stack = new Stack();
        int index = 0;

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n + 1];

        for (int i = 0; i < n; i++) {
            //수열의 출력 순서
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= n; i++) {
            stack.push(i);
            sb.append("+\n");

            while (!stack.empty() && (int) stack.peek() == arr[index]) {
                index ++;
                stack.pop();
                sb.append("-\n");
            }
        }
        if (!stack.empty()) {
            System.out.println("NO");
        }
        else {
            System.out.println(sb.toString());
        }
    }
}
