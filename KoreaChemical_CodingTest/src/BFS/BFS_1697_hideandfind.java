package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_1697_hideandfind {
    static int N, K;
    static int[] dx = {1,-1,0};
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        K = Integer.parseInt(st.nextToken());

        visited = new boolean[100001];
        bfs();
    }
    static void bfs() {
        Queue<Position> q = new LinkedList<>();

        q.add(new Position(N, 0));

        visited[N] = true;
        while (!q.isEmpty()) {
            Position now = q.poll();

            int x = now.x;
            int seconds = now.seconds;

            if (x == K) {
                System.out.println(seconds);
                return;
            }

            for (int i = 0; i < dx.length; i++) {
                int nx = x;

                if (dx[i] != 0) {
                    nx = x + dx[i];
                }
                else{
                    nx = x * 2;
                }

                System.out.println(nx);

                if (nx >= 0 && nx <= 100000) {
                    if (!visited[nx]) {
                        visited[nx] = true;
                        q.add(new Position(nx, seconds+1));
                    }
                }
            }

        }
    }
    static class Position {
        int x, seconds;

        public Position(int x, int seconds) {
            this.x = x;
            this.seconds = seconds;
        }
    }
}