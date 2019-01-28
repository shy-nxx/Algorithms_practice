import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2252 {
    /**
     * 백준알고리즘 그래프 위상정렬 2252 - 줄세우기
     * 입력값
     * 3 2
     * 1 3
     * 2 3
     *
     * 출력값
     * 1 2 3
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer> map[] = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            map[i] = new ArrayList<>();
        }

        int[] indegree = new int[N+1];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int small = Integer.parseInt(st.nextToken());
            int large = Integer.parseInt(st.nextToken());

            indegree[large]++;
            map[small].add(large);

        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i]==0) {
               q.add(i);
            }
        }

        int loopCnt = 0 ;

        while (!q.isEmpty()) {
            int now = q.poll();
            sb.append(now + " ");
            for (int i = 0; i < map[now].size(); i++) {
                int next = map[now].get(i);
                    indegree[next]--;
                    if (indegree[next] == 0) {
                        q.add(next);
                    }

            }
        }

        System.out.println(sb.toString());

    }
}
