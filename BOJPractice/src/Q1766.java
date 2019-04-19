import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1766 {
    /**
     * BOJ graph indegree sort -문제집
     */
    static int N, M;
    static ArrayList<Integer> sequence[];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] indegree = new int[N+1];
        StringBuilder sb = new StringBuilder();

        sequence = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            sequence[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sequence[start].add(end);
            indegree[end]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            sb.append(now + " ");
            for (int i = 0; i < sequence[now].size(); i++) {
                int next = sequence[now].get(i);

                indegree[next]--;

                if (indegree[next]==0) {

                    q.add(next);
                }
            }
        }
        System.out.println(sb.toString());
    }
}