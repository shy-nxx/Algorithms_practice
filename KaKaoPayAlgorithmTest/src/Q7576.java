import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q7576 {
    //백준알고리즘 그래프(DFS, BFS) 7576 - 토마토

    public static int[][] matrix;
    static class Graph {
        private int V; // 노드의 개수
        private LinkedList<Integer> adj[]; // 인접 리스트

        /**
         * 생성자
         */
        Graph(int v) {
            V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; ++i) // 인접 리스트 초기화
                adj[i] = new LinkedList();
        }

        void addEdge(int v, int w) {
            adj[v].add(w);
        }

        /**
         * s를 시작 노드으로 한 BFS로 탐색하면서 탐색한 노드들을 출력
         */
        void BFS(int s) {
            // 노드의 방문 여부 판단 (초깃값: false)
            boolean visited[] = new boolean[V];
            // BFS 구현을 위한 큐(Queue) 생성
            LinkedList<Integer> queue = new LinkedList<Integer>();

            // 현재 노드를 방문한 것으로 표시하고 큐에 삽입(enqueue)
            visited[s] = true;
            queue.add(s);

            // 큐(Queue)가 빌 때까지 반복
            while (queue.size() != 0) {
                // 방문한 노드를 큐에서 추출(dequeue)하고 값을 출력
                s = queue.poll();
                System.out.print(s + " ");

                Iterator<Integer> i = adj[s].listIterator();
                while (i.hasNext()) {
                    int n = i.next();

                    // 방문하지 않은 노드면 방문한 것으로 표시하고 큐에 삽입(enqueue)
                    if (!visited[n]) {
                        visited[n] = true;
                        queue.add(n);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //상자의 가로
        int m = Integer.parseInt(st.nextToken());
        //상자의 세로
        int n = Integer.parseInt(st.nextToken());

//        matrix = new int[N][M];
//        for (int i =0; i < N; i++) {
//
//            for (int j= 0; j < M; j++) {
//                matrix[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }

        Graph g = new Graph(n * m);
        int index= 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            while(st.hasMoreTokens())
                g.addEdge(index++, Integer.parseInt(st.nextToken()));
        }
//        g.addEdge(0, 1);
//        g.addEdge(0, 2);
//        g.addEdge(1, 2);
//        g.addEdge(2, 0);
//        g.addEdge(2, 3);
//        g.addEdge(3, 3);

        g.BFS(n*m-1); /* 주어진 노드를 시작 노드로 BFS 탐색 */



    }
}