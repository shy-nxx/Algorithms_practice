package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class SW14888_dfs {
    static int N;
    static int[] nums;
    static int[] ops;
    static ArrayList<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        nums = new int[N];
        ops = new int[4]; //차례대로 덧셈(+)의 개수, 뺄셈(-)의 개수, 곱셈(×)의 개수, 나눗셈(÷)의 개수이다.

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            ops[i] = Integer.parseInt(st.nextToken());
        }

        DFS(1, nums[0]); // 계산 시작 인덱스, sum
        Collections.sort(result);
        System.out.println(result.get(result.size()-1));
        System.out.println(result.get(0));

    }

    static void DFS(int x, int sum) {
        if (x == N) {
            result.add(sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (ops[i] != 0) {
                ops[i]--;
                DFS(x+1, calcuation(sum, nums[x], i));
                ops[i]++;
            }
        }
    }

    static int calcuation(int x, int y, int op) {
        if (op == 0)
            return x+y;
        else if (op == 1)
            return x-y;
        else if (op == 2)
            return x*y;
        else
            return x/y;
    }
}