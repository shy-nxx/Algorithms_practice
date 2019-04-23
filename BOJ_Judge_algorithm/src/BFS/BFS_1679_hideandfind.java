package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_1679_hideandfind {
    /**
     * 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
     * <p>
     * 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.
     *
     * 순간이동은 배수로 이동/ 걷는 경우에는 앞뒤로만 이동
     *
     * 수빈이가 5-10-9-18-17 순으로 가면 4초만에 동생을 찾을 수 있다.
     *
     * 단순하게 DFS와 BFS 중 어떤 것을 선택해야할 경우에 대해 잘 보여주는 문제라고 생각한다.
     *
     * 본인은 처음에는 DFS를 통해 접근했다.
     *
     * DFS를 통해 깊게 탐색할 경우, DFS의 단점인 트리의 깊이가 길 경우에 나오는 문제점들이 고스란히 보여졌다.
     *
     * 그 결과, 스택오버플로우를 경험하게 되어 BFS로 바꾸게 되었다.
     *
     * 물론, DFS로도 풀 수는 있지만, 이러한 문제는 BFS를 이용하는 것이 더욱 효율적이다.
     *
     *
     *
     * BFS의 탐색 특성상 모든 분기점을 큐를 이용하여 레벨순으로 탐색하기 때문에, 목표 지점이 도달하는 순간이 최소 시간이 된다.
     *
     *
     *
     * 그렇기에, 큐에 삽입할 때, x-1, x+1, x*2 세가지 경우와 그 시점에 대한 시간을 함께 넣어주면서, BFS를 탐색하면 된다.
     *
     * 시간의 경우는 배열을 이용하는 방법과, class를 이용하는 방법이 있다. (본인은 class)
     *
     *
     * */

    static int N, K;
    static boolean[] visited;

    static int[] dx=  {-1,1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 수빈이 가 있는 위치
        K = Integer.parseInt(st.nextToken()); //동생이 있는 위치

        visited = new boolean[100001];

        System.out.println(BFS());

    }
    static int BFS() {
        Queue<Pos> q = new LinkedList<>();

        q.add(new Pos(N, 0));
        visited[N] = true;

        while (!q.isEmpty()) {
            Pos now = q.poll(); // 현재 수빈이의 위치
            int pos_now = now.x;
            int pos_time = now.seconds;

//            System.out.println(pos_time);

            if (pos_now == K) {
                return pos_time;
            }

            for (int i = 0; i < 3; i++) {
                int nx;

                if (dx[i] != 0) {
                    nx = pos_now + dx[i];
                }
                else {
                    nx = pos_now * 2;
                }

                if (nx >= 0 && nx <= 100000) {
                    if (!visited[nx]) {
                        visited[nx] = true;
                        q.add(new Pos(nx, pos_time+1));
                    }
                }

            }
        }
        return 0;

    }

    static class Pos {
        int x;
        int seconds;

        public Pos(int x, int seconds) {
            this.x = x;
            this.seconds = seconds;

        }
    }
}