package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_2644_blood_kinship {
    static int N,M;
    static int A, B;
    static ArrayList<Integer>[] list = new ArrayList[101];

    static int[] dp = new int[101];
    static boolean[] visited = new boolean[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //이때 앞에 나오는 번호 x는 뒤에 나오는 정수 y의 부모 번호를 나타낸다.

        N = Integer.parseInt(st.nextToken()); //사람의 수

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); //관계 수

        for (int i = 1; i <=N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++ ) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b); //부모 - 자식
            list[b].add(a); //자식 - 부모
        }

        System.out.println(bfs());


    }
    static int bfs() {
        Queue<Integer> q = new LinkedList<>();

        q.add(A);

        while (!q.isEmpty()) {
            int parent = q.poll();

            visited[parent] = true;
            for (int child : list[parent]) {
                if (!visited[child]) {
//                    System.out.println(parent + " " + child);
                    dp[child] = dp[parent] + 1;
                    q.add(child);
                }
            }
        }
        return dp[B] == 0 ? -1 : dp[B];
    }
}