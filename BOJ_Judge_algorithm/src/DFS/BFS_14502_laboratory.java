package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BFS_14502_laboratory {
    /**
     * 인체에 치명적인 바이러스를 연구하던 연구소에서 바이러스가 유출되었다. 다행히 바이러스는 아직 퍼지지 않았고, 바이러스의 확산을 막기 위해서 연구소에 벽을 세우려고 한다.
     * <p>
     * 연구소는 크기가 N×M인 직사각형으로 나타낼 수 있으며, 직사각형은 1×1 크기의 정사각형으로 나누어져 있다. 연구소는 빈 칸, 벽으로 이루어져 있으며, 벽은 칸 하나를 가득 차지한다.
     * <p>
     * 일부 칸은 바이러스가 존재하며, 이 바이러스는 상하좌우로 인접한 빈 칸으로 모두 퍼져나갈 수 있다. 새로 세울 수 있는 벽의 개수는 3개이며, 꼭 3개를 세워야 한다.
     * <p>
     * 예를 들어, 아래와 같이 연구소가 생긴 경우를 살펴보자.
     * <p>
     * 2 0 0 0 1 1 0
     * 0 0 1 0 1 2 0
     * 0 1 1 0 1 0 0
     * 0 1 0 0 0 0 0
     * 0 0 0 0 0 1 1
     * 0 1 0 0 0 0 0
     * 0 1 0 0 0 0 0
     * <p>
     * 이때, 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 곳이다. 아무런 벽을 세우지 않는다면, 바이러스는 모든 빈 칸으로 퍼져나갈 수 있다.
     * <p>
     * 2행 1열, 1행 2열, 4행 6열에 벽을 세운다면 지도의 모양은 아래와 같아지게 된다.
     * <p>
     * 2 1 0 0 1 1 0
     * 1 0 1 0 1 2 0
     * 0 1 1 0 1 0 0
     * 0 1 0 0 0 1 0
     * 0 0 0 0 0 1 1
     * 0 1 0 0 0 0 0
     * 0 1 0 0 0 0 0
     * <p>
     * 바이러스가 퍼진 뒤의 모습은 아래와 같아진다.
     * <p>
     * 2 1 0 0 1 1 2
     * 1 0 1 0 1 2 2
     * 0 1 1 0 1 2 2
     * 0 1 0 0 0 1 2
     * 0 0 0 0 0 1 1
     * 0 1 0 0 0 0 0
     * 0 1 0 0 0 0 0
     * <p>
     * 벽을 3개 세운 뒤, 바이러스가 퍼질 수 없는 곳을 안전 영역이라고 한다. 위의 지도에서 안전 영역의 크기는 27이다.
     * <p>
     * 연구소의 지도가 주어졌을 때 얻을 수 있는 안전 영역 크기의 최댓값을 구하는 프로그램을 작성하시오.
     */

    static int[][] map, copyed_map;
    static boolean[][] visitied;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static ArrayList<Pos> virus;
    static int N, M;

    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //세로
        M = Integer.parseInt(st.nextToken()); //가로

        map = new int[N+1][M+1];
        copyed_map = new int[N+1][M+1];
        virus = new ArrayList<>();


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virus.add(new Pos(i, j));
                }
            }
        }

        dfs(0, 0);

        System.out.println(max);
    }

    static void dfs(int start, int count) {
        if (count == 3) { //최대 개수는 3
            //바이러스 퍼뜨리기
            copy_map();

            for (Pos p : virus)
                spread_virus(p.x, p.y);

            max = Math.max(max, find_safe_area());
            return;
        }

        for (int i = start; i < N*M; i++) {
            int x = i / M;
            int y = i % M;

            if (map[x][y] == 0) {
                map[x][y] = 1;
                dfs(i+1, count+1);
                map[x][y] = 0;

            }
        }
    }

    static void spread_virus(int x, int y) {
            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M ) continue;

                if (copyed_map[nx][ny] == 1) continue;

                if (copyed_map[nx][ny] == 0) {
                    copyed_map[nx][ny] = 2;
                    spread_virus(nx, ny);
                }
            }
    }

    static void copy_map() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyed_map[i][j] = map[i][j];
            }
        }
    }

    static int find_safe_area() {
        int safe_area= 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyed_map[i][j] == 0)
                    safe_area++;
            }
        }
        return safe_area;
    }
    static class Pos {
        int x;
        int y;

        public Pos (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
