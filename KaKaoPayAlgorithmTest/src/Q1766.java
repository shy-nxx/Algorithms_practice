import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1766 {
    /**
     * 백준 알고리즘 위상정렬 1766 - 문제집
     * 입력값
     * 4 2
     * 4 2
     * 3 1
     *
     * 출력값
     * 3 1 4 2
     */
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //문제의 수
        int M = Integer.parseInt(st.nextToken()); //문제와의 난이도 관계 A->B

        int[] indegree = new int[N+1];
        ArrayList<Integer> sequence[] = new ArrayList[N+1];

        for (int i = 1; i <= N; i++) {
            sequence[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            sequence[from].add(to);
            indegree[to]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i = 1; i <= N; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }

        while (!q.isEmpty()) {
            int now = q.poll();

            System.out.print(now + " ");

            for (int i = 0; i < sequence[now].size(); i++) {
                int next = sequence[now].get(i);
                indegree[next]--;

                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }




    }
}
