package BFS.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MovingThroughWalls_BFS {
    /**
     * 벽을 부쉈는지 안부수었는지 확인하기 위해 visited 배열을 3차원 배열로 선언한다.
     * <p>
     * 좌표를 저장하는 클래스에 해당 좌표에 벽이 부수어져있는지 확이하는 변수를 선언한다.
     * <p>
     * 큐가 빌때까지 큐의 사이즈만큼 반복하면서 최단거리를 구한다.
     */

    static int M , N;
    static int[][] map;
    static boolean[][][] visited;

    static final int YES = 1;
    static final int NO = 0;

    static int result = 0;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - 48;
            }
        }

        if (bfs(0,0)) {
            System.out.println(result);
            return;
        }
        else {
            System.out.println(-1);
            return;
        }
    }
    static boolean bfs(int x, int y) {
        Queue<Pos> q = new LinkedList<>();

        q.add(new Pos(x, y, NO));

        //처음은 무조건 벽이 아님
        visited[x][y][YES] = true;
        visited[x][y][NO] = true;

        int temp = 0;

        while(!q.isEmpty()) {
            ++temp;

            int n = q.size();

            for (int i = 0; i < n; i++) {
                Pos p = q.poll();

                int cx = p.x;
                int cy = p.y;

                if (p.x == N-1 && p.y == M-1) {
                    result = temp;
                    return true;
                }

                for (int j = 0; j < 4; j++) {
                    int nx = cx + dx[j];
                    int ny = cy + dy[j];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                    if (map[nx][ny] == 1) {
                        //벽이면 일단 벽을 부순게 있는지 확인한다.
                        if (!visited[nx][ny][YES] && p.brokenWall < YES) {
                            q.add(new Pos(nx, ny, YES));
                            visited[nx][ny][YES] = true;
                        }
                    }
                    else {
                        if (!visited[nx][ny][p.brokenWall]) {
                            q.add(new Pos(nx, ny, p.brokenWall));
                            visited[nx][ny][p.brokenWall] = true;
                        }
                    }
                }
            }
        }
        return false;

    }

    static class Pos {
        int x, y;
        int brokenWall;

        public Pos(int x, int y, int brokenWall) {
            this.x = x;
            this.y = y;
            this.brokenWall = brokenWall;
        }
    }
}
