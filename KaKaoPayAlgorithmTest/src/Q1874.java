import javafx.beans.property.IntegerProperty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q1874 {
    //백준 알고리즘 스택 Q1874 - 스택

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
    public static int N;
    public static int[] result;
    public static int[] resultIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int i, temp, max = 0, top = 0;
        int stack[] = new int[n];

        while (n-- > 0) {
            temp = Integer.parseInt(br.readLine());

            if (temp > max) {
                // 스택에 값이 없는 상태
                for (i = max+1; i <= temp; i++) {
                    stack[top++] = i;
                    sb.append("+\n");
                }
                max = temp;
            }else if (stack[top - 1] != temp) {
                System.out.print("NO");
                return;
            }

            top--;
            sb.append("-\n");

        }// end while

        System.out.println(sb);


    }
}
