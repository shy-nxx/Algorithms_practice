package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DFS_11403_find_path {
    static int N;

    static int[][] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        edges = new int[N][N];

        for (int i = 0; i< N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                edges[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] link = new int[N];
        int[][] result = new int[N][N];

        for (int i = 0; i < N; i++) {
            dfs(i, link);

            for (int j = 0; j < N; j++) {
                result[i][j] = link[j];
            }

            Arrays.fill(link, 0);
        }

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {
                System.out.print(result[i][j] + " ");
            }

            System.out.println();
        }

    }

    static void dfs(int x, int[] link) {
        for (int i = 0; i < N; i++) {
            if (edges[x][i] == 1 && link[i] == 0) {
                link[i] = 1;
                dfs(i, link);
            }
        }
    }
}