import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1753_shortest {

    //시간 초과...
    
    public static int V, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int root = Integer.parseInt(st.nextToken());
        int[] distance = new int[V+1];

        Arrays.fill(distance, Integer.MAX_VALUE);
        boolean[] visitied = new boolean[V+1];

        ArrayList<EdgeNValue> edges[] = new ArrayList[V+1];

        for (int i = 1; i <=V; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            edges[Integer.parseInt(st.nextToken())].add(new EdgeNValue(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        PriorityQueue<Integer> q = new PriorityQueue<>();

        q.add(root);

        distance[root] = 0;

        while (!q.isEmpty()) {
            int now = q.poll();

            visitied[now] = true;

            for (int i = 0; i < edges[now].size(); i++) {
                int next = edges[now].get(i).end;
                int value = edges[now].get(i).value;

                if (distance[next] > distance[now] + value) {
                    distance[next] = Math.min(distance[next], distance[now] + value);
                    q.add(next);
                }
            }
        }
        for (int i = 1; i <= V; i++) {
            if (visitied[i])
                System.out.println(distance[i]);
            else{
                System.out.println("INF");
            }
        }
    }
    static class EdgeNValue {
        int end;
        int value;

        public EdgeNValue(int end, int value) {
            this.end = end;
            this.value = value;
        }
    }
}
