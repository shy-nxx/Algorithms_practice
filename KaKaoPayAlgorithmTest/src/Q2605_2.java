import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2605_2 {
    public static int N, M;
    public static int[][] matrix;
    public static boolean[] visited;
    public static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());

        matrix = new int[N + 1][N + 1];
        visited = new boolean[N +1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            matrix[a][b] = matrix[b][a] = 1;

        }
        BFS(1);

    }
    static void BFS(int k) {
        Queue<Integer> q =  new LinkedList<>();

        q.add(k);

        while (!q.isEmpty()) {
            int x = q.poll();
            visited[x] = true;

            for (int i = 1; i <= N; i++) {
                if (matrix[x][i] == 1 && !visited[i]) {
                    count++;
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
        System.out.println(count);
    }

}
