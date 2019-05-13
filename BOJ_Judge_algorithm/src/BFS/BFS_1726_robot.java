package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_1726_robot {
    /**
     * 많은 공장에서 로봇이 이용되고 있다. 우리 월드 공장의 로봇은 바라보는 방향으로 궤도를 따라 움직이며,
     * 움직이는 방향은 동, 서, 남, 북 가운데 하나이다. 로봇의 이동을 제어하는 명령어는 다음과 같이 두 가지이다.
     * <p>
     * 명령 1. Go k
     * - k는 1, 2 또는 3일 수 있다. 현재 향하고 있는 방향으로 k칸 만큼 움직인다.
     * <p>
     * 명령 2. Turn dir
     * - dir은 left 또는 right 이며, 각각 왼쪽 또는 오른쪽으로 90° 회전한다.
     * <p>
     * 공장 내 궤도가 설치되어 있는 상태가 아래와 같이 0과 1로 이루어진 직사각형 모양으로 로봇에게 입력된다.
     * 0은 궤도가 깔려 있어 로봇이 갈 수 있는 지점이고, 1은 궤도가 없어 로봇이 갈 수 없는 지점이다. 로봇이 (4, 2) 지점에서 남쪽을 향하고 있을 때,
     * 이 로봇을 (2, 4) 지점에서 동쪽으로 향하도록 이동시키는 것은 아래와 같이 9번의 명령으로 가능하다.
     * <p>
     * <p>
     * <p>
     * 로봇의 현재 위치와 바라보는 방향이 주어졌을 때, 로봇을 원하는 위치로 이동시키고, 원하는 방향으로 바라보도록 하는데 최소 몇 번의 명령이 필요한지 구하는 프로그램을 작성하시오.
     * <p>
     * 입력
     * 첫째 줄에 공장 내 궤도 설치 상태를 나타내는 직사각형의 세로 길이 M과 가로 길이 N이 빈칸을 사이에 두고 주어진다.
     * 이때 M과 N은 둘 다 100이하의 자연수이다. 이어 M줄에 걸쳐 한 줄에 N개씩 각 지점의 궤도 설치 상태를 나타내는 숫자 0 또는 1이 빈칸을 사이에 두고 주어진다.
     * 다음 줄에는 로봇의 출발 지점의 위치 (행과 열의 번호)와 바라보는 방향이 빈칸을 사이에 두고 주어진다.
     * 마지막 줄에는 로봇의 도착 지점의 위치 (행과 열의 번호)와 바라보는 방향이 빈칸을 사이에 두고 주어진다.
     * 방향은 동쪽이 1, 서쪽이 2, 남쪽이 3, 북쪽이 4로 주어진다. 출발지점에서 도착지점까지는 항상 이동이 가능하다.
     */

    static int M, N;
    static int[][] map;
    static boolean[][][] visited; //방향까지
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    //동서남북

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N= Integer.parseInt(st.nextToken());

        map= new int[101][101];
        visited = new boolean[101][101][5];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);

        st = new StringTokenizer(br.readLine());
        Point end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);

        visited[start.x][start.y][start.dir] = true;

        bfs(start, end);

    }

    static void bfs(Point start, Point end) {
        Queue<Point> q = new LinkedList<>();

        q.add(start);

        while (!q.isEmpty()) {
            Point p = q.poll();

            int x = p.x;
            int y = p.y;
            int dir = p.dir;
            int count = p.count;

            if (x == end.x && y == end.y && dir == end.dir) {
                System.out.println(count);
                return;
            }

           for (int i = 1; i <= 3; i++) { //Go 1~3
                int nx = (dx[dir-1] * i) + x;
                int ny = (dy[dir-1] * i) + y;

               if (nx > 0 && nx <= M && ny > 0 && ny <= N) {
                   if (map[nx][ny] == 0) {
                       if (!visited[nx][ny][dir]) {
                           visited[nx][ny][dir] = true;
                           System.out.println(nx + " " + ny + " " + dir + " " + count);
                           q.add(new Point(nx, ny, dir, count + 1));
                       }
                   }
                   else {
                       break;
                   }
               }
           }

           //회전하기
            for (int i = 1; i <= 4; i++) {
                if (dir != i && !visited[x][y][i]) {
                    int add = 1;
                    if (dir == 1) { //동 (현재 방향)
                        if (i == 2) { //서
                            ++add;
                        }
                    } else if (dir == 2) { //서
                        if (i == 1) ++add;
                    } else if (dir == 3) { //남
                        if (i == 4) ++add;
                    } else {
                        if (i == 3) ++add;
                    }
                    //서로 반대 방향일 때는 2번회전해야한다.

                    visited[x][y][i] = true;
                    q.add(new Point(x, y, i, count + add));
                }
            }

        }
    }
//    static void dfs(Point start, Point end, int count) {
//        if (start.x == end.x && start.y == end.y && start.dir == end.dir) {
//            System.out.println((count < ans) ? count : ans);
//            return;
//        }
//
//        visited[start.x][start.y] = true;
//
//        System.out.println(start.x + " " + start.y + " " + start.dir);
//        start.x += dx[start.dir];
//        start.y += dy[start.dir];
//
//        if (start.x < 0 || start.x > M || start.y < 0 || start.y > N) {
//            start.dir = changeDir(start.dir);
//        }
//
//        if (start.x >= 0 && start.x <= M && start.y >= 0 && start.y <= N && !visited[start.x ][start.y]) {
//            for (Point wall : walls) {
//                if (start.x == wall.x && start.y == wall.y) {
//                    start.dir = changeDir(start.dir);
//                }
//
//            }
//
//                visited[start.x][start.y] = true;
//                dfs(start, end, count+1);
//
//        }
//
//    }

//    static int changeDir(int cur_dir) {
//        //왼쪽 오른쪽으로 방향 전환 가능 -> 근데 어떤 방향으로 전환하는 게 좋은 건가?
//        int next_dir;
//
//        next_dir = (cur_dir == 3) ? 0 : ++cur_dir;
//        next_dir = (cur_dir == 0) ? 3 : --cur_dir;
//        return next_dir;
//
//    }
    static class Point {
        int x, y;
        int dir;
        int count;

        public Point(int x, int y, int dir, int count) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.count = count;
        }

    }


}