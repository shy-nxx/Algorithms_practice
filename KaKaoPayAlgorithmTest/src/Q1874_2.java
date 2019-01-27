import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1874_2 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");

        int n = Integer.parseInt(br.readLine());

        int temp, max = 0, top = 0;
        int[] stack = new int[n];

        while (n-- > 0) {
            temp = Integer.parseInt(br.readLine());

            if (max < temp) {
                for (int i = max + 1; i <= temp; i++) {
                    stack[top++] = i;
                    sb.append("+\n");
                }
                max = temp;
            } else if (stack[top -1] != temp){
                System.out.println("\nNO");
                return;
            }
            top--;
            sb.append("-\n");
        }

        System.out.println(sb.toString());

    }
}
