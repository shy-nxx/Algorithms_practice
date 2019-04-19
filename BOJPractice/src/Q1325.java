import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1325 {
    /**
     * 백준알고리즘 그래프 -해킹
     */
    static int N, M;
    static ArrayList<Integer> rely[];
    static boolean[] visitied;
    static int[] res = new int[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //컴퓨터 수
        M = Integer.parseInt(st.nextToken()); //관계 수

        rely = new ArrayList[N+1];


        for (int i = 1; i <= N; i++) {
            rely[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            rely[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 1; i<= N; i++) {
            visitied = new boolean[N+1];
            DFS(i);
        }
        int max = 0;

        for (int i = 1; i <= N; i++) {
            if (max < res[i]){
                max = res[i];
            }
        }

        for (int i = 1; i <= N; i++) {
            if (max == res[i])
                System.out.print(i + " ");
        }

    }

    static void DFS(int k) {
        visitied[k] = true;
        for (int vv : rely[k]) {
            if (!visitied[vv]){
                res[vv]++;
                DFS(vv);
            }
        }
    }
}
