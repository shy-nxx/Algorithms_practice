package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Operation_dfs {
    static int N;
    static int[] nums;
    static int[] operation = new int[4];
    //덧셈(+), 뺄셈(-), 곱셈(×), 나눗셈(÷)
    static ArrayList<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //숫자의 개수

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            operation[i] = Integer.parseInt(st.nextToken());

        }

        DFS(1,nums[0]);

    }
    static void DFS(int index, int sum) {
        if (index == N) {
            result.add(sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operation[i] != 0) {
                operation[i]--;
                DFS(index+1, calculation(sum, nums[index], i));
                operation[i]++;
            }
        }
    }

    static int calculation(int sum, int num, int op) {
        if (op == 0) {
            return sum + num;
        }
        else if (op == 1) {
            return sum - num;
        }
        else if (op == 2)
            return sum * num;
        else
            return sum / num;
    }
}
