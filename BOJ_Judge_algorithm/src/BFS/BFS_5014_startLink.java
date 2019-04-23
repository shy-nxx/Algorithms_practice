package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_5014_startLink {
    /**
     * 강호는 코딩 교육을 하는 스타트업 스타트링크에 지원했다. 오늘은 강호의 면접날이다. 하지만, 늦잠을 잔 강호는 스타트링크가 있는 건물에 늦게 도착하고 말았다.
     * <p>
     * 스타트링크는 총 F층으로 이루어진 고층 건물에 사무실이 있고, 스타트링크가 있는 곳의 위치는 G층이다. 강호가 지금 있는 곳은 S층이고, 이제 엘리베이터를 타고 G층으로 이동하려고 한다.
     * <p>
     * 보통 엘리베이터에는 어떤 층으로 이동할 수 있는 버튼이 있지만, 강호가 탄 엘리베이터는 버튼이 2개밖에 없다. U버튼은 위로 U층을 가는 버튼, D버튼은 아래로 D층을 가는 버튼이다. (만약, U층 위, 또는 D층 아래에 해당하는 층이 없을 때는, 엘리베이터는 움직이지 않는다)
     * <p>
     * 강호가 G층에 도착하려면, 버튼을 적어도 몇 번 눌러야 하는지 구하는 프로그램을 작성하시오. 만약, 엘리베이터를 이용해서 G층에 갈 수 없다면, "use the stairs"를 출력한다.
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

        System.out.println(bfs(move));
//        System.out.println("use the upstairs");
    }

    static String bfs(int[] move) {
        Queue<Pos> q = new LinkedList<>();

        q.add(new Pos(S, 0));

        visited[S] = true;

        while (!q.isEmpty()) {
            Pos now_p = q.poll();

            int x = now_p.now;
            int times = now_p.times;

            if (x == G) {
                return String.valueOf(times);
            }

            for (int i = 0; i < 2; i++) {
                int nx = x + move[i];

                if (nx <= 0 || nx >= 1000001) continue;

                if (!visited[nx]) {
                    q.add(new Pos(nx, times + 1));
                    visited[nx] = true;
                }

            }
        }
        return "use the upstairs";
    }

    static class Pos {
        int now;
        int times;

        public Pos (int now, int times) {
            this.now = now;
            this.times = times;
        }
    }
}
