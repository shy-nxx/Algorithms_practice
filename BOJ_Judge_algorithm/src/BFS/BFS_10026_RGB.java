package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_10026_RGB {
    /**
     * 적록색약은 빨간색과 초록색의 차이를 거의 느끼지 못한다. 따라서, 적록색약인 사람이 보는 그림은 아닌 사람이 보는 그림과는 좀 다를 수 있다.
     * <p>
     * 크기가 N×N인 그리드의 각 칸에 R(빨강), G(초록), B(파랑) 중 하나를 색칠한 그림이 있다. 그림은 몇 개의 구역으로 나뉘어져 있는데, 구역은 같은 색으로 이루어져 있다. 또, 같은 색상이 상하좌우로 인접해 있는 경우에 두 글자는 같은 구역에 속한다. (색상의 차이를 거의 느끼지 못하는 경우도 같은 색상이라 한다)
     * <p>
     * 예를 들어, 그림이 아래와 같은 경우에
     * <p>
     * RRRBB
     * GGBBB
     * BBBRR
     * BBRRR
     * RRRRR
     * 적록색약이 아닌 사람이 봤을 때 구역의 수는 총 4개이다. (빨강 2, 파랑 1, 초록 1) 하지만, 적록색약인 사람은 구역을 3개 볼 수 있다. (빨강-초록 2, 파랑 1)
     * <p>
     * 그림이 입력으로 주어졌을 때, 적록색약인 사람이 봤을 때와 아닌 사람이 봤을 때 구역의 수를 구하는 프로그램을 작성하시오.
     */
    static int N;
    static char[][] rgb;
    static boolean[][] visited;

    static int[] result;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    static final int R = 0;
    static final int G = 1;
    static final int B = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //정점
        rgb = new char[N][N];
        visited = new boolean[N][N];
        result = new int[N];

        for (int i=0; i < N; i++) {
            String s = br.readLine();

            for (int j = 0; j < N; j++) {
                rgb[i][j] = s.charAt(i);
            }
        }

        int non_rgb = bfs();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (rgb[i][j] == 'R') {
                    rgb[i][j] = 'G';
                }
            }
        }

        visited = new boolean[N][N];
        int is_rgb = bfs();

        System.out.println(non_rgb + " " + is_rgb);
    }

    /**
     * 적녹색약 / 적녹색약x
     * -> RGB나눈 거 하나 / RG B 나눈거
     * 적록색약인 것은 R을 G로 바꾸고 bfs를 돌린다.
     *
     */
    static int bfs() {
        Queue<Pos> q = new LinkedList<>();

        int count = 0;

        for (int k = 0; k < N; k++) {
            for (int j = 0; j < N; j++) {
                if (visited[k][j]) continue;

                q.add(new Pos(k, j));

                visited[k][j] = true;

                while (!q.isEmpty()) {
                    Pos p = q.poll();

                    int cx = p.x;
                    int cy = p.y;

                    for (int i = 0; i < dx.length; i++) {
                        int nx = cx + dx[i];
                        int ny = cy + dy[i];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || rgb[k][j] != rgb[nx][ny]) continue;

                        visited[nx][ny] = true;
                        q.add(new Pos(nx, ny));
                    }
                }
                count++;
            }
        }
        return count;
    }
    static class Pos {
        int x, y;

        public Pos (int x, int y) {
            this.x = x;
            this.y = y;

        }
    }
}