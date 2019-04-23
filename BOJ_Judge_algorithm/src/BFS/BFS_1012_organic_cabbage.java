package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_1012_organic_cabbage {
    /**
     * 차세대 영농인 한나는 강원도 고랭지에서 유기농 배추를 재배하기로 하였다. 농약을 쓰지 않고 배추를 재배하려면 배추를 해충으로부터 보호하는 것이 중요하기 때문에, 한나는 해충 방지에 효과적인 배추흰지렁이를 구입하기로 결심한다. 이 지렁이는 배추근처에 서식하며 해충을 잡아 먹음으로써 배추를 보호한다. 특히, 어떤 배추에 배추흰지렁이가 한 마리라도 살고 있으면 이 지렁이는 인접한 다른 배추로 이동할 수 있어, 그 배추들 역시 해충으로부터 보호받을 수 있다.
     * <p>
     * (한 배추의 상하좌우 네 방향에 다른 배추가 위치한 경우에 서로 인접해있다고 간주한다)
     * <p>
     * 한나가 배추를 재배하는 땅은 고르지 못해서 배추를 군데군데 심어놓았다. 배추들이 모여있는 곳에는 배추흰지렁이가 한 마리만 있으면 되므로 서로 인접해있는 배추들이 몇 군데에 퍼져있는지 조사하면 총 몇 마리의 지렁이가 필요한지 알 수 있다.
     * <p>
     * 예를 들어 배추밭이 아래와 같이 구성되어 있으면 최소 5마리의 배추흰지렁이가 필요하다.
     * <p>
     * (0은 배추가 심어져 있지 않은 땅이고, 1은 배추가 심어져 있는 땅을 나타낸다.)
     */

    static int M, N, K;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int k = 0; k < T; k++) {
            st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            visited = new boolean[50][50];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());

                map[x][y] = 1; //배추가 심어저있다.
            }

            int count = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1) {
                        BFS(i, j);
                        count++;
                    }
                }
            }

            System.out.println(count);
        }
    }

    static void BFS(int x, int y) {

        Queue<Pos> q = new LinkedList<>();

        q.add(new Pos(x,y));

        while (!q.isEmpty()) {
            Pos now = q.poll(); //배추가 심어진 자리 중 하나를 빼냄

            int cx = now.x;
            int cy = now.y;

            /**
             * 인접한 아이들은 지렁이 한 마리만 필요
             * 인접하지 않은 아이들 간에 지렁이 한 마리씩 추가한다.
             *
             */

            for (int i = 0; i < dx.length; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;


                if (map[nx][ny] == 0)
                   continue;
                else{
                    map[nx][ny] = 0;
                }

                q.add(new Pos(nx, ny));
            }
        }
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