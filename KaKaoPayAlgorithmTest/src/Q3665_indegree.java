import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q3665_indegree {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken()); //테스트 케이스 개수

        System.out.println();
        for (int t = 0; t < T; t++) {
            StringBuilder sb = new StringBuilder();

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); //팀 수

            int[] sequence = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                sequence[i] = Integer.parseInt(st.nextToken()); //등수 입력
            }

            //map 설정
            boolean[][] map = new boolean[N +1][N +1];
            //위상정렬 인덱스
            int[] indegree = new int[N +1];

            for (int i = 1; i <= N; i++) {
                int a = sequence[i];

                for (int j = 1; j < i; j++) {
                    int b = sequence[j];

                    map[a][b] = true;
                    indegree[a]++;
                }
            }

            //바뀐 등수 입력받기
            int k = Integer.parseInt(br.readLine());

            for (int i = 1; i <= k; i++) {
                st = new StringTokenizer(br.readLine());
                int ch1 = Integer.parseInt(st.nextToken());
                int ch2 = Integer.parseInt(st.nextToken());

                if (map[ch1][ch2]) {
                    indegree[ch1]--;
                    indegree[ch2]++;
                }
                else {
                    indegree[ch2]--;
                    indegree[ch1]++;
                }

                map[ch1][ch2] = !map[ch1][ch2];
                map[ch2][ch1] = !map[ch2][ch1];
            }

            //위상정렬
            Queue<Integer> q = new LinkedList<>();

            for (int i = 1; i <= N; i++) {
                if (indegree[i] == 0)
                    q.add(i);
            }

            int loopCnt = 0 ;
            while (!q.isEmpty()) {
                if (q.size() >= 2) break;

                loopCnt++;
                int now = q.poll();
                sb.append(now + " ");

                for (int i = 1; i <= N; i++) {
                    if (map[i][now]) {
                        indegree[i]--;
                        if (indegree[i]==0) {
                           q.add(i);
                        }
                    }
                }
            }

            if (loopCnt == N) {
                System.out.print(sb.toString() + "\n");
            } else {
                System.out.print("IMPOSSIBLE");
            }

        }


    }
}
