import javax.swing.text.EditorKit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1753 {
    /**
     * 백준알고리즘 그래프 최단거리 1753- 최단경로
     * 입력값
     * 5 6
     * 1
     * 5 1 1
     * 1 2 2
     * 1 3 3
     * 2 3 4
     * 2 4 5
     * 3 4 6
     *
     * 출력값
     * 0
     * 2
     * 3
     * 7
     * INF
     */

    public static int V, E;
    public static boolean[] visited;
    public static ArrayList<Edge>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int root = Integer.parseInt(br.readLine()); //시작정점

        visited = new boolean[V+1];
        int distance[] = new int[V+1]; //가중치 저장
        Arrays.fill(distance, Integer.MAX_VALUE);
        //최대값으로 채워줘야 한다. INF

        edges = new ArrayList[V+1];
        for (int i = 1; i <= V; i++ )
            edges[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            edges[Integer.parseInt(st.nextToken())].add(new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        //우선순위 큐
        PriorityQueue<Integer> q =  new PriorityQueue<>();

        q.add(root);

        distance[root] = 0;
        while (!q.isEmpty()) {
            //다음에 방문할 노드
            int now = q.poll();

            visited[now] = true;

            for (int i = 0; i < edges[now].size(); i++) {
                int next = edges[now].get(i).end;
                int value = edges[now].get(i).value;

                // 이전에 있던 값이 더 크다면 굳이 다시 방문할 필요가 없다. 이미 그 전이 더 최단 경로이기 때문에
                if (distance[next] > distance[now] + value) {
                    distance[next] = Math.min(distance[next], value + distance[now]);
                    q.add(next);

                }
            }

        }
        // 출력
        for (int i = 1; i <= V; i++) {
            if (visited[i]) {
                System.out.println(distance[i]);
            } else {
                System.out.println("INF");
            }
        }


    }
}

class Edge {
    int end;
    int value;

    public Edge(int end, int value) {
        this.end = end;
        this.value = value;
    }
}