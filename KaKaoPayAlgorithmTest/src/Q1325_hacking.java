import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q1325_hacking {

    public static int N, M;
    public static ArrayList<Integer> map[];
    public static boolean[] visited;
    public static int[] res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //총 컴퓨터 수
        N = Integer.parseInt(st.nextToken());
        //신뢰관계 수
        M = Integer.parseInt(st.nextToken());

        map = new ArrayList[N + 1];

        res = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            map[i] = new ArrayList();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
        }


        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            DFS(i);
        }

        int max = 0;

        for (int i = 1; i <= N; i++) {
            if (max < res[i])
                max = res[i];
        }

        for (int i = 1; i <= N; i++) {
            if (res[i] == max) {
                System.out.print(i + " ");
            }
        }

    }
    static void DFS(int k) {
        visited[k] = true;

        for (int vv : map[k]) {
            if (!visited[vv]) {
                DFS(vv);
                res[vv]++;
            }
        }
    }
}
