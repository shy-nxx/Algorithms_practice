import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1753 {
    /**
     * BOJ graph shortest path
     */

    static int V, E;
    static ArrayList<Edge> edges[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int root = Integer.parseInt(st.nextToken());

        edges = new ArrayList[V+1];

        for (int i = 1; i <=V; i++) {
            edges[i] = new ArrayList<>();
        }

        boolean[] visited = new boolean[V+1];
        int[] distance = new int[V+1];

        Arrays.fill(distance, Integer.MAX_VALUE);

        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            edges[Integer.parseInt(st.nextToken())].add(new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        PriorityQueue<Integer> q = new PriorityQueue<>();

        q.add(root);

        distance[root] = 0;

        while (!q.isEmpty()) {
            int now = q.poll();

            visited[now] = true;

            for (int i = 0; i < edges[now].size(); i++) {
                int next = edges[now].get(i).end;
                int value = edges[now].get(i).value;

                distance[next] = Math.min(distance[next], distance[now] + value);
                q.add(next);

            }
        }

        for (int i = 1; i <= V; i++) {
            if (visited[i])
                System.out.println(distance[i]);
            else{
                System.out.println("INF");
            }
        }


    }
    static class Edge {
        int end;
        int value;

        public Edge(int end, int value) {
            this.end = end;
            this.value = value;
        }
    }
}