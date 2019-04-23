package BFS.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class StartLink_BFS {
    /**
     *
     */

    static int F, S, G, U, D;

    static int[] move = {-1,1};
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        move[0] *= D;
        move[1] *= U;

        visited = new boolean[1000001];

        System.out.println(bfs());
    }

    static String bfs() {
        Queue<Pos> q = new LinkedList<>();

        q.add(new Pos(S, 0));

        visited[S] = true;

        while (!q.isEmpty()) {
            Pos p = q.poll();

            int x = p.position;
            int cnt = p.times;

            if (x == G) return String.valueOf(cnt);

            for (int i = 0; i< 2; i++) {
                int nx = x + move[i];

                if (nx <= 0 || nx > 1000000) continue;

                if (!visited[nx]) {
                    q.add(new Pos(nx, cnt+1));
                    visited[nx] = true;
                }
            }
        }
        return "use the stairs";
    }

    static class Pos {
        int position;
        int times;

        public Pos(int position, int times) {
            this.position = position;
            this.times = times;
        }
    }

}