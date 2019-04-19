package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SW14888 {
    /**
     * 연산자 끼워넣기
     *
     * DFS로 전체 탐색하면 된다.
     *
     * 2. 숫자 (여기서는 x로 가정) 는 +1씩 증가 시켜 인자로 넘겨주면 되고 그 때마다 전체 합인 sum 또한 인자로 넘겨주면 된다.
     *
     * 3. 탐색동안 끝까지 탐색 완료하면 그때의 sum 값을 List에 저장해준다.
     *
     * 4. 모든 탐색이 끝나고 List에 저장된 sum 값중에 최대 값과 최소 값을 출력해주면 된다.
     */
    static int N;
    static int[] nums;
    static int[] inst;
    static int MAX = 0;
    static int MIN = Integer.MAX_VALUE;
    static int[] temp;
    static int[] dp;

    static ArrayList<Integer> result = new ArrayList();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        nums = new int[N];

        inst = new int[4];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        //차례대로 덧셈(+)의 개수, 뺄셈(-)의 개수, 곱셈(×)의 개수, 나눗셈(÷)의 개수이다.
        for (int i = 0; i < 4; i++)
            inst[i] = Integer.parseInt(st.nextToken());

        DFS(1, nums[0]);
        Collections.sort(result);

        System.out.println(result.get(result.size()-1));
        System.out.println(result.get(0));

    }
    //x = 다음 숫자, sum = 합
    static void DFS(int x, int sum) {
        if (x == N) {
//            System.out.println(sum);
            result.add(sum);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (inst[i] != 0) {
                inst[i]--;
                DFS(x+1, calculation(sum, nums[x], i));
//                System.out.println( sum + " " + i + " " + nums[x] );
//                System.out.println(sum);
                inst[i]++;
            }


        }

    }
    static int calculation(int x, int y, int inst) {
        if (inst == 0)
            return x + y;
        else if (inst == 1)
            return x - y;
        else if (inst == 2)
            return x * y;
        else
            return x / y;
    }
}