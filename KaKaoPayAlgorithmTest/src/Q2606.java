import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2606 {
    //백준알고리즘 그래프 (DFS, BFS) 2606 - 바이러스
    public static int N, M;
    public static int[][] matrix;
    public static boolean[] visited;
    public static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //컴퓨터 수
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); //연결 쌍

        matrix = new int[N + 1][N + 1];
        visited = new boolean[N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            matrix[x][y] = matrix[y][x] = 1;

        }

        BFS(1);
    }

    static void BFS(int k) {
        Queue<Integer> q = new LinkedList<>();
        q.add(k);

        while(!q.isEmpty()) {
            int x = q.poll();
            visited[x] = true;

            for (int i = 1; i <= N; i++) {
                if (matrix[x][i] == 1 && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                    count++;
                }
            }
        }
        System.out.println(count);


    }
}
