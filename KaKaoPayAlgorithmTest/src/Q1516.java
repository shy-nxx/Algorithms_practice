import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1516 {
    /**
     * 백준알고리즘 그래프 위상정렬 1516 -게임 개발
     * 입력값
     * 5
     * 10 -1
     * 10 1 -1
     * 4 1 -1
     * 4 3 1 -1
     * 3 3 -1
     *
     * 출력값
     * 10
     * 20
     * 14
     * 18
     * 17
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        ArrayList<Integer> sequence[] = new ArrayList[N + 1];
        int[] value = new int[N+1];
        int[] indegree = new int[N+1];
        int[] result = new int[N+1];

        for (int i = 1; i <= N; i++) {
            sequence[i] = new ArrayList<>();
        }

        int[] inputs = new int[N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            value[i] = Integer.parseInt(st.nextToken());
            int temp = Integer.parseInt(st.nextToken());

            while (temp != -1) {
                //indegree를 temp가 아닌 i 값으로 받아야한다.
                indegree[i]++;
                //리스트에 저장 될 temp 와 i 값도 바껴야 한다.
                sequence[temp].add(i); //temp -> 먼저 지어져야 하는 건물의 번호
                temp = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i<=N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
                result[i] = value[i]; //건물이 지어질 때까지의 시간
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int i = 0; i < sequence[now].size(); i++) {
                int next = sequence[now].get(i);
                indegree[next]--;

                //자신의 건물을 짓기전 이전에 가장 오랜 시간 걸린 값을 찾아야 한다. 그래야 자신의 건물을 올리지
                result[next] = Math.max(result[next], result[now] + value[next]);

                if (indegree[next] == 0) {
                    q.add(next);
                }
            }

        }

        for (int i = 1; i <= N; i++) {
            System.out.println(result[i]);
        }

    }
}
