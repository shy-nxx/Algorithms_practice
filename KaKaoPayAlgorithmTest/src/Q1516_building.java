import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1516_building {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //세워야 하는 건물의 수

        ArrayList<Integer> sequence[] = new ArrayList[N+1];
        int[] time = new int[N + 1];
        int[] result = new int[N+1];
        int[] indegree = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            sequence[i] = new ArrayList<>();
        }

        for (int i = 1; i<= N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());

            int temp = Integer.parseInt(st.nextToken());

            while (temp != -1) {
                indegree[i]++;
                sequence[temp].add(i); //temp = 먼저 지어져야 하는 건물의 번호
                temp = Integer.parseInt(st.nextToken());
            }
        }
        //입력 끝

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
                result[i] = time[i];
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int i = 0; i < sequence[now].size(); i++) {
                int next = sequence[now].get(i);
                indegree[next]--;

                //건물을 지을때 먼저 지어야 하는 건물이 세워지는데 걸리는 시간을 계산
                result[next] = Math.max(result[next], result[now] + time[next]);

                if (indegree[next] == 0) {
                    q.add(next);
                }

            }
        }

        for(int i = 1; i <= N; i++) {
            System.out.println(result[i]);
        }

    }
}
