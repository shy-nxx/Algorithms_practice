package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_2206_moveThroughWalls {
    /**
     * N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다. 당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다. 최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.
     * <p>
     * 만약에 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다.
     * <p>
     * 맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.
     */

    static int N, M;

    static int[][] map;
    static boolean[][][] visitied;
    static int result = 0;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    static final int YES = 1;
    static final int NO = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - 48;
            }
        }

        visitied = new boolean[N][M][2];

        if (BFS(0,0)) {
            System.out.println(result);
            return;
        }
        else {
            System.out.println("-1");
            return;
        }
    }

    /**
     * 벽을 부수는 경우와 부수지 않고 이동하는 경우를 나눠서 진행하면 된다.
     */

    static boolean BFS(int x, int y) {
        Queue<Pos> q = new LinkedList<>();

        q.add(new Pos(x, y, NO));
        visitied[x][y][YES] = true;
        visitied[x][y][NO] = true;

        int temp = 0;

        while (!q.isEmpty()) {
            ++temp;

            int n = q.size();

            for (int i = 0; i < n; i++)  {
                Pos p = q.poll();

                int cx = p.x;
                int cy = p.y;

                if (cx == N-1 && cy == M-1) {
                    result = temp;
                    return true;
                }

                for (int j = 0; j < 4; j++) {
                    int nx = cx + dx[j];
                    int ny = cy + dy[j];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                    if (map[nx][ny] == 1) {
                        //일단 벽을 하나 부수었는지 확인한다.
                        if (p.brokenWall < YES && !visitied[nx][ny][YES]) {
                            visitied[nx][ny][YES] = true;
                            q.add(new Pos(nx, ny, YES));
                        }
                    }
                    else {
                       if (!visitied[nx][ny][p.brokenWall]){
                            q.add(new Pos(nx, ny, p.brokenWall));
                            visitied[nx][ny][p.brokenWall] = true;
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

        public Pos (int x, int y, int brokenWall){
            this.x = x;
            this.y = y;
            this.brokenWall = brokenWall;
        }
    }
}