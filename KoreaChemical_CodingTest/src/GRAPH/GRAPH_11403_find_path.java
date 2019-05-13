package GRAPH;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GRAPH_11403_find_path {
    static int N;
    static int[][] edges = new int[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                edges[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int a = 1; a <= N; a++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (edges[i][a] == 1 && edges[i][a] == edges[a][j] )
                        edges[i][j] = edges[i][a];
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(edges[i][j] + " ");
            }
            System.out.println();
        }


    }
}