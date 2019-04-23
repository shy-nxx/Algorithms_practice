package BFS.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class RGB_BFS {
    /**
     * 적록색약 X -> RGB 모두 구분해서 서로 같지 않으면 통과하고 같으면 visited를 true로 해서 표시한 후 queue에 넣는다. queue가 다 비워지면 count를 늘려서 집합의 개수를 계산한다.
     * 적록색약 -> R을 G로 바꾸고 BFS
     */

    static int N;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static char[][] rgb;
    static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //정점

        rgb = new char[N][N];

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j< N; j++) {
//
//            }
//        }
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j< N; j++) {
                rgb[i][j] = s.charAt(i);
            }
        }

        visited = new boolean[N][N];

        int non_rgb = bfs();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j< N; j++) {
                if (rgb[i][j] == 'R')
                    rgb[i][j] = 'G';
            }
        }

        visited = new boolean[N][N];
        int is_rgb = bfs();

        System.out.println(non_rgb + " " + is_rgb);

    }
    static int bfs() {
        Queue<Pos> q = new LinkedList<>();

        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j< N; j++) {
                if (visited[i][j]) continue;

                q.add(new Pos(i, j));

                visited[i][j] = true;

                while (!q.isEmpty())  {
                    Pos p = q.poll();

                    int cx = p.x;
                    int cy = p.y;

                    for (int k = 0; k < 4; k++) {
                        int nx = cx + dx[k];
                        int ny = cy + dy[k];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || rgb[i][j] != rgb[nx][ny]) continue;

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