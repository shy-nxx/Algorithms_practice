package DFS;

import java.io.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_2583_find_area_width {
    /**
     * 눈금의 간격이 1인 M×N(M,N≤100)크기의 모눈종이가 있다. 이 모눈종이 위에 눈금에 맞추어 K개의 직사각형을 그릴 때, 이들 K개의 직사각형의 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어진다.
     *
     * 예를 들어 M=5, N=7 인 모눈종이 위에 <그림 1>과 같이 직사각형 3개를 그렸다면, 그 나머지 영역은 <그림 2>와 같이 3개의 분리된 영역으로 나누어지게 된다.
     *
     * <그림 2>와 같이 분리된 세 영역의 넓이는 각각 1, 7, 13이 된다.
     *
     * M, N과 K 그리고 K개의 직사각형의 좌표가 주어질 때, K개의 직사각형 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어지는지,
     * 그리고 분리된 각 영역의 넓이가 얼마인지를 구하여 이를 출력하는 프로그램을 작성하시오.
     *
     */

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int M, N, K;

    static boolean[][] matrix;
    static boolean[][] visited;

    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static PriorityQueue<Integer> result = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //새로
        M = Integer.parseInt(st.nextToken()); //가로
        K = Integer.parseInt(st.nextToken()); //사각형 개

        matrix = new boolean[N][M];

        visited = new boolean[N][M];

        /**
         bfs 를 사용하지 않은 이유
         1. dfs 로 하면 영역별로 나누는게 쉽다. (재귀함수기 때문에 한 번 확인하는 영역은 끝을 봄)
         2. dfs 안에 bfs 를 넣을 수 있었는데 귀찮았다.
         PriorityQueue (max-heap) 를 사용한 이유
         1. 원래는 LinkedList 를 사용하려 했었는데 정렬을 해야되서 귀찮았다.
         2. 하나씩 앞에서 꺼내쓰는게 queue 랑 맞다고 생각했기 때문이다.

         결론적으로 풀이 방법은
         1. dfs 를 이용해서 모든 구역을 확인하고 각 영역별로 넓이를 큐에 담아줬다.
         2. 큐에 담겨진 데이터의 개수를 모드 출력하고 데이터를 순차적으로 출력했다.


         **/
        //사각형 채우기..?
        for (int t = 0; t < K; t++) {
            st = new StringTokenizer(br.readLine());
            int lx = Integer.parseInt(st.nextToken());
            int ly = Integer.parseInt(st.nextToken());
            int rx = Integer.parseInt(st.nextToken());
            int ry = Integer.parseInt(st.nextToken());


            for (int i = lx; i < rx; i++) {
                for (int j = ly; j < ry; j++) {
                    matrix[j][i] = true; //사각형으로 차 있음
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] &&
                        !matrix[i][j])
                    result.offer(dfs(i, j));

            }
        }
        bw.write(String.valueOf(result.size()));
        bw.write("\n");

        while(!result.isEmpty()) {
            bw.write(String.valueOf(result.poll()));
            bw.write("\n");
        }

        bw.flush();


        /**
         * 일단 사각형이 차지하는 자리를 채우고
         * 나머지 구역들을 구한다.
         */
    }

    static int dfs(int x, int y) {
        int temp_result = 1;

        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x+ dx[i];
            int ny = y+ dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M || matrix[nx][ny] || visited[nx][ny]) continue;

            temp_result += dfs(nx, ny);
        }

        return temp_result;

    }
}