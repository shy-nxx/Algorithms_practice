package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DFS_2842_postman {
    /**
     * 상덕이는 언덕 위에 있는 마을의 우체국에 직업을 얻었다. 마을은 N×N 행렬로 나타낼 수 있다.
     * 행렬로 나뉘어진 각 지역은 우체국은 'P', 집은 'K', 목초지는 '.' 중 하나로 나타낼 수 있다. 또, 각 지역의 고도도 알고 있다.
     * <p>
     * 매일 아침 상덕이는 마을의 모든 집에 우편을 배달해야 한다. 배달은 마을에 하나밖에 없는 우체국 'P'가 있는 곳에서 시작한다.
     * 상덕이는 현재 있는 칸과 수평, 수직, 대각선으로 인접한 칸으로 이동할 수 있다. 마지막 편지를 배달하고 난 이후에는 다시 우체국으로 돌아와야 한다.
     * <p>
     * 상덕이는 이렇게 매일 아침 배달을 하는 것이 얼마나 힘든지 궁금해졌다. 상덕이가 배달하면서 방문한 칸 중 가장 높은 곳과 낮은 곳의 고도 차이를 피로도라고 하자.
     * 이때, 가장 작은 피로도로 모든 집에 배달을 하려면 어떻게 해야 하는지 구하는 프로그램을 작성하시오.
     */

    static int N;
    static char[][] map;
    static int[][] high;


    static Position post_p;
    static ArrayList<Position> houseList = new ArrayList<>();

    static int[] dx = {0,0,-1,-1,1,-1,1,-1};
    static int[] dy = {1,-1,0,0,1,-1,-1,1};

    static int[][] dp = new int[51][51];
    static int[][] cost = new int[51][51];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        map = new char[51][51];

        for (int i = 0; i < N; i++ ){
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        high = new int[N][N];
        for (int i = 0; i < N; i++ ){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                high[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 'P') {
                    post_p = new Position(i, j);
                }

                if (map[i][j] == 'K') {
                    houseList.add(new Position(i, j));
                }
            }
        }


        //집이 위치한 칸의, 최소 높이
        int min = getMin(high);
        //집이 위치한 칸의, 최대높이
        int max = getMax(high);

        System.out.println(max + " " + min);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = Integer.MAX_VALUE;

                //범위 안에 없다면 가중치는 값 - 범위와의 오차만큼
                if (high[i][j] < min) {
                    cost[i][j] = min - high[i][j];
                }
                else if (high[i][j] > max) {
                    cost[i][j] = high[i][j] - max;
                }
                else {//범위 안에 있다면 가중치는 0
                    cost[i][j] = 0;
                }
//                System.out.println(i + " " + j + " " + cost[i][j]);
            }
        }

        dijkstra(post_p.x, post_p.y);

        int result = max - min + getMaxK();

        System.out.println(result);
    }

    static int getMaxK() {
        int max = 0;
        for (Position p : houseList) {

            max = (max < dp[p.x][p.y]) ? dp[p.x][p.y] : max ;
        }
        return max;
    }

    //K지점들 중 가장 고도가 낮은 값을 구한다
    static int getMin(int[][] high ) {
        int temp = Integer.MAX_VALUE;

        for (Position p : houseList) {
            temp = Math.min(temp, high[p.x][p.y]);
        }
        return temp;
    }

    //K지점들 중 가장 고도가 낮은 값을 구한다
    static int getMax(int[][] high ) {
        int temp = 0;

        for (Position p : houseList) {
            temp = Math.max(temp, high[p.x][p.y]);
        }
        return temp;
    }
    /**
     * 처음과 끝이 우체국이어야 한다. -> 근데 같은 좌표면 어떻게 해하지?
     *
     */

    static void dijkstra(int x, int y) {
        PriorityQueue<Position> pq = new PriorityQueue<>();

        dp[x][y] = cost[x][y];
        pq.offer(new Position(x,y, dp[x][y]));

        while (!pq.isEmpty()) {
            Position p = pq.poll();

//            System.out.println(dp[p.x][p.y]);
//            if (dp[p.x][p.y] >= p.weight) {
                int cx = p.x;
                int cy = p.y;

                for (int i = 0; i < dx.length; i++) {
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= N ) continue;

                    //dp[][] = 정수 최대값으로 초기화되어있음
                    if (dp[nx][ny] > dp[cx][cy] + cost[nx][ny]) {
                        dp[nx][ny] = dp[cx][cy] + cost[nx][ny];
                        pq.offer(new Position(nx, ny, dp[nx][ny]));
                    }
                }
//            }

        }
    }
//    static void bfs(Position start, Position end, int min, int max) {
//        Queue<Position> q = new LinkedList<>();
//        q.add(start);
//
//
//        while (!q.isEmpty()) {
//            Position p = q.poll();
//
//            int x = p.x;
//            int y = p.y;
//            int high = p.high;
//
//            for (int i = 0; i < dx.length; i++) {
//                int nx = p.x + dx[i];
//                int ny = p.y + dy[i];
//
//                if (nx < 0 || nx >= N || ny < 0 || ny >= N ) continue;
//
//                System.out.println(nx + " " + ny);
//                if (nx == post_p.x && ny == post_p.y) {
//                    System.out.println(max - min);
//                    return;
//                }
//
//                min = Math.min(min, p.high);
//                max = Math.max(max, p.high);
//
//
//
//            }
//
//        }
//
//    }

    static class Position implements Comparable<Position> {
        int x, y;
        int weight;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(Position o) {
            return (this.weight >= o.weight) ? 1 : -1;
        }

    }
}
