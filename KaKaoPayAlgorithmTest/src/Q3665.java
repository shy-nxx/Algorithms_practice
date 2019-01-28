import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q3665 {
    /**
     * 백준알고리즘 위상정렬 3665 - 최종순위
     * 입력값
     * 3
     * 5
     * 5 4 3 2 1
     * 2
     * 2 4
     * 3 4
     * 3
     * 2 3 1
     * 0
     * 4
     * 1 2 3 4
     * 3
     * 1 2
     * 3 4
     * 2 3
     *
     * 출력값
     * 5 3 2 4 1
     * 2 3 1
     * IMPOSSIBLE
     */
    public static int T, N, M;
    public static int rank[];
    public static int[][] matrix;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            rank = new int[N + 1];

            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                rank[j] = Integer.parseInt(st.nextToken());
            }
            //작년 등수 입력 끝

            // map 생성하기
            boolean map[][] = new boolean[N + 1][N + 1];
            int indegree[] = new int[N + 1];
            for (int j = 1; j <= N; j++) {
                int a = rank[j];

                for (int k = 1; k < j; k++) {
                    int b = rank[k];
                    map[a][b] = true;
                    indegree[a]++;
                }
            }

            // 순위 바꾸기
            int K = Integer.parseInt(br.readLine());
            for(int j=1; j<=K; j++) {
                st = new StringTokenizer(br.readLine());
                int ch1 = Integer.parseInt(st.nextToken());
                int ch2 = Integer.parseInt(st.nextToken());

                if(map[ch1][ch2]) {
                    indegree[ch1]--;
                    indegree[ch2]++;
                }else {
                    indegree[ch2]--;
                    indegree[ch1]++;
                }

                map[ch1][ch2] = !map[ch1][ch2];
                map[ch2][ch1] = !map[ch2][ch1];
            }

            // 위상정렬 시작
            Queue<Integer> q = new LinkedList<Integer>();
            for(int j=1; j<=N; j++) {
                if(indegree[j]==0) {
                    q.add(j);
                }
            }

            int loopCnt = 0 ;
            String resultStr = "";
            while(!q.isEmpty()) {
                // 큐 상태 체크 (순위 데이터이기 때문에 큐에 데이터가 2개 이상 동시에 존재할 수 없다.)
                if( q.size() >= 2 ) {
                    break;
                }

                loopCnt++;
                int now = q.poll();
                resultStr += now + " ";

                for(int j=1; j<=N; j++) {
                    if(map[j][now]) {
                        indegree[j]--;
                        if(indegree[j]==0) {
                            q.add(j);
                        }
                    }
                }
            }

            if(loopCnt == N) {
                System.out.println(resultStr);
            }else {
                System.out.println("IMPOSSIBLE");
            }

        }
    }
}
