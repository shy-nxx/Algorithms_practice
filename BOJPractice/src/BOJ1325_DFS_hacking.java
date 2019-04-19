import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1325_DFS_hacking {

    public static int N, M;
    public static ArrayList<Integer> rely[];
    public static boolean[] visited;
    public static int[] res = new int[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        rely = new ArrayList[N+1];

        for (int i = 1; i <= N; i++) {
            rely[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            rely[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N+1];
            DFS(i);
        }

        int max = 0;

        for (int  i =1; i <= N; i++) {
            System.out.println(res[i]);

            if (max < res[i]) {
                max = res[i];
            }
        }

        for (int  i = 1; i <= N; i++) {
            if (max == res[i]) {
                System.out.print(i + " ");
            }
        }
    }
    static void DFS(int k) {
        visited[k] = true;

        for (int vv : rely[k]) {
            if (!visited[vv]) {
                res[vv]++;
                DFS(vv);
            }
        }
    }
}
