import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Q2667_2 {
    public static int N;
    public static int[][] matrix;
    public static boolean[][] visited;
    public static int counts;
    public static ArrayList<Integer> ascends = new ArrayList<>();

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        matrix = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            for (int j =0; j < N; j++) {
                matrix[i][j] = s.charAt(j) - 48;
                visited[i][j] = false;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j =0; j < N; j++) {
                if (matrix[i][j] == 1)
                    BFS(new DOT(i, j));
            }
        }
        Collections.sort(ascends);

        System.out.println(ascends.size());
        for (int i =0; i < ascends.size(); i++) {
            System.out.println(ascends.get(i));
        }
    }
    static void BFS(DOT d) {
        Queue<DOT> q = new LinkedList<>();
        counts = 0;

        if (!visited[d.x][d.y]) {
            q.add(d);
            visited[d.x][d.y] = true;

            while (!q.isEmpty()) {
                DOT k = q.poll();
                counts++;
                for (int i = 0; i < dx.length; i++) {
                    int nextX = k.x + dx[i];
                    int nextY = k.y + dy[i];

                    if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;
                    if (matrix[nextX][nextY] == 0) continue;
                    if (!visited[nextX][nextY]) {
                        visited[nextX][nextY] = true;
                        q.add(new DOT(nextX, nextY));
                    }
                }
            }
            ascends.add(counts);
        }


    }
}
