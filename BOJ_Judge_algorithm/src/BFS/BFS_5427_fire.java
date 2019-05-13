package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_5427_fire {
    /**
     * 상근이는 빈 공간과 벽으로 이루어진 건물에 갇혀있다. 건물의 일부에는 불이 났고, 상근이는 출구를 향해 뛰고 있다.
     * <p>
     * 매 초마다, 불은 동서남북 방향으로 인접한 빈 공간으로 퍼져나간다. 벽에는 불이 붙지 않는다. 상근이는 동서남북 인접한 칸으로 이동할 수 있으며, 1초가 걸린다. 상근이는 벽을 통과할 수 없고, 불이 옮겨진 칸 또는 이제 불이 붙으려는 칸으로 이동할 수 없다. 상근이가 있는 칸에 불이 옮겨옴과 동시에 다른 칸으로 이동할 수 있다.
     * <p>
     * 빌딩의 지도가 주어졌을 때, 얼마나 빨리 빌딩을 탈출할 수 있는지 구하는 프로그램을 작성하시오.
     */

    static char[][] map;

    static boolean[][] visited;

    static Queue<Position> fireQ = new LinkedList<>();
    static Queue<Position> personQ = new LinkedList<>();

    static int min = 0;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int w, h;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int k = 0; k < T; k++) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken()); //가로
            h = Integer.parseInt(st.nextToken()); //세로

            map = new char[1001][1001];
            visited = new boolean[1001][1001];

            for (int i = 0; i < h; i++) {
                String s = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = s.charAt(j);

                    if (map[i][j] == '*') { //fire
                        fireQ.add(new Position(i, j));
                    }

                    if (map[i][j] == '@') {
                        personQ.add(new Position(i, j));
                    }
                }
            }

            int seconds = 0;

            while(true) {
                ++seconds;

                if (personQ.size() == 0) {
                    System.out.println("IMPOSSIBLE");
                    break;
                }

                extendFire();

//                불 번지는 모양 프린트
//                for (int i = 0; i < h; i++) {
//                    for (int j = 0; j < w; j++) {
//                        System.out.print(map[i][j] + " ");
//                    }
//                    System.out.println();
//                }

                if (moveSanggin()) {
                    System.out.println(seconds);
                    break;
                }

            }

        }
    }
    static void extendFire() {
        int n = fireQ.size();

        for (int i = 0; i < n; i++) {
            Position p = fireQ.poll();

            int x = p.x;
            int y = p.y;

            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];

                if (nx < 0 || nx >= h || ny < 0 || ny >= w ) continue;

                if (map[nx][ny] == '.') {
                    map[nx][ny] = '*';
                    fireQ.add(new Position(nx, ny));
                }
            }
        }
    }

    static boolean moveSanggin() {

        int n = personQ.size();

        for (int i = 0; i < n; i++) {
            Position p = personQ.poll();

            int x = p.x;
            int y = p.y;

            if ((x < 0 || x >= h )|| (y < 0 || y >= w) ) {
                return true;
            }

            visited[x][y] = true;

            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];

                if ((nx < 0 || nx >= h) || (ny < 0 || ny >= w) ) {
                    return true;
                }

                if (nx >= 0 && nx < h && ny >= 0 && ny < w) {
                    if (map[nx][ny] == '.' && !visited[nx][ny]) {
                        map[nx][ny] = '@';
                        visited[nx][ny] = true;
                        personQ.add(new Position(nx, ny));
                    }
                }

            }
        }
        return false;
    }

    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}