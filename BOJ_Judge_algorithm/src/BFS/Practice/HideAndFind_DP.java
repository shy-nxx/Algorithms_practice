package BFS.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HideAndFind_DP {
    /**
     * BFS의 탐색 특성상 모든 분기점을 큐를 이용하여 레벨순으로 탐색하기 때문에, 목표 지점이 도달하는 순간이 최소 시간이 된다.
     * i-1, i+1, i*2 모든 경우를 고려하여 스택에 넣고 그 시점에 대한 시간을 함께 넣어주어 K에 도달하는 순간의 시간릉 리턴한다.
     */

    static int N, K;
    static boolean[] visited;
    static int[] dx = {-1,1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[100001];

        System.out.println(bfs(N));


    }

    static int bfs(int n) {
        Queue<Pos> q = new LinkedList<>();

        q.add(new Pos(n, 0));

        visited[n] = true;

        while (!q.isEmpty()) {
            Pos now = q.poll();

            int now_p = now.pos;
            int now_t = now.time;

            if (now_p == K) return now_t;

            for (int i = 0; i< dx.length; i++) {
                int next;

                if (dx[i] != 0) {
                    next = now_p + dx[i];
                }
                else {
                    next = now_p * 2;
                }

                if (next >= 0 && next <= 100000) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.add(new Pos(next, now_t + 1));
                    }
                }
            }
        }
        return 0;
    }

    static class Pos {
        int pos;
        int time;

        public Pos (int pos, int time) {
            this.pos = pos;
            this.time = time;

        }
    }
}