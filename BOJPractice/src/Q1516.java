import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1516 {
    /**
     * BOJ 그래프 위상정렬-게임개발
     */

    static int N;
    static ArrayList<Integer> sequence[];
    static int[] time;
    static int[] indegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        sequence = new ArrayList[N+1];
        indegree = new int[N+1];
        time = new int[N+1];

        int[] result = new int[N+1];

        for (int i = 1; i <= N; i++) {
            sequence[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            int temp = Integer.parseInt(st.nextToken());

            while (temp != -1) {
                indegree[i]++;
                sequence[temp].add(i);
                temp = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++){
            if (indegree[i] == 0){
                q.add(i);
                result[i] = time[i];
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int i = 0; i < sequence[now].size(); i++) {
                int next = sequence[now].get(i);

                indegree[next]--;

                result[next] = Math.max(result[next], result[now] + time[next]);

                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        for (int i = 1; i <= N; i++){
            System.out.println(result[i]);
        }
    }

}
