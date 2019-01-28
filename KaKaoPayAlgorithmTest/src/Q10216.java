import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q10216 {
    /**백준알고리즘 그래프 (BFS, DFS) 10216 - Count Circle Groups
     * 입력값
     * 2
     * 2
     * 0 0 1
     * 1 0 1
     * 3
     * 0 0 1
     * 2 0 1
     * 10 0 5
     *
     * 출력값
     * 1
     * 2
     */
    public static int T, N;
    public static ArrayList<Enermy> matrix = new ArrayList<>();
    public static int [] R;
    public static boolean[] visited;
    public static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        //테스트 케이스 개수
        T = Integer.parseInt(st.nextToken());

        for (int k = 0; k < T; k++) {
            matrix = new ArrayList<>();
            result = 0;
            st = new StringTokenizer(br.readLine());
            //적군의 수
            N = Integer.parseInt(st.nextToken());

            //각 좌표의 반지름
            R = new int[N];

            visited = new boolean[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                matrix.add(new Enermy(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            for (int i = 0; i < matrix.size(); i++) {
                if (!visited[i]) {
                    BFS(i);
                    result++;
                }
            }
            sb.append(result + "\n");
        }
        System.out.println(sb.toString());

    }
    static void BFS(int k) {
        Queue<Enermy> q = new LinkedList<>();
        q.add(matrix.get(k));
        visited[k] = true;

        while (!q.isEmpty()) {
            Enermy e = q.poll();

            for(int i = 0; i < matrix.size(); i++) {
                if (!visited[i] && isConnected(matrix.get(i), e)) {
                    q.add(e);
                    visited[i] = true;
                }
            }
        }

    }
    static boolean isConnected(Enermy e1, Enermy e2) {
        return Math.pow(e1.getR()+ e2.getR(), 2) >=
                Math.pow(e2.getX() - e1.getX(), 2) +
                        Math.pow(e2.getY() - e1.getY(), 2);
    }
    static class Enermy {
        private int x, y, r;

        public Enermy(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getR() {
            return r;
        }
    }

}
