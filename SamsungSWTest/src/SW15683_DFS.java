import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW15683_DFS {
    /**
     * 1번 CCTV - (오른쪽), (위쪽), (왼쪽), (아래쪽) => 4가지
     * 2번 CCTV - (왼쪽, 오른쪽), (위쪽, 아래쪽) => 2가지
     * 3번 CCTV - (위쪽, 오른쪽), (오른쪽, 아래쪽), (아래쪽, 왼쪽), (왼쪽, 위쪽) => 4가지
     * 4번 CCTV - (왼쪽, 위쪽, 오른쪽), (위쪽, 오른쪽, 아래쪽), (오른쪽, 아래쪽, 왼쪽), (아래쪽, 왼쪽, 위쪽) => 4가지
     * 5번 CCTV - (왼쪽, 위쪽, 오른쪽, 아래쪽) => 1가지
     *
     * 1. 이전 감시 현황(prev)을 가져온다.
     *
     * 2. CCTV 감시한다.
     *
     * 3. 다음 CCTV 를 탐색한다.
     *
     *
     */
    static int N, M;
    static int[][] matrix;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int MIN = Integer.MAX_VALUE;
    static ArrayList<Pos> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if (matrix[i][j] >= 1 && matrix[i][j] <= 5) {
                    list.add(new Pos(i, j, matrix[i][j]));
//                    System.out.println(matrix[i][j]);
                }
            }
        }

        DFS(0, matrix);

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 0) count++;
            }
        }

        System.out.println(MIN);
    }

    /**
     * 1번 CCTV - (오른쪽), (위쪽), (왼쪽), (아래쪽) => 4가지
     * 2번 CCTV - (왼쪽, 오른쪽), (위쪽, 아래쪽) => 2가지
     * 3번 CCTV - (위쪽, 오른쪽), (오른쪽, 아래쪽), (아래쪽, 왼쪽), (왼쪽, 위쪽) => 4가지
     * 4번 CCTV - (왼쪽, 위쪽, 오른쪽), (위쪽, 오른쪽, 아래쪽), (오른쪽, 아래쪽, 왼쪽), (아래쪽, 왼쪽, 위쪽) => 4가지
     * 5번 CCTV - (왼쪽, 위쪽, 오른쪽, 아래쪽) => 1가지
     * */

    static void DFS(int cctvIdx, int[][] prev) {
        int[][] visited = new int[N][M];

        int temp = 0;

        if (cctvIdx == list.size()) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (prev[i][j] == 0) {
                        temp++;
                    }
                    System.out.print(prev[i][j] + " ");
                }
                System.out.println("\n");
            }
//            System.out.println(temp);

            if(MIN > temp) {
                MIN = temp;
            }
        }
        else {
            Pos p = list.get(cctvIdx);

            int idx = p.idx;
            int x = p.x;
            int y = p.y;

            // 이전 감시 현황(prev)을 가져온다.
            switch (idx) {
                case 1 :
                    for (int i = 0; i < dx.length; i++) {
                        for (int j = 0; j < N; j++) {
                            visited[i] = Arrays.copyOf(prev[i], M); //prev == matrix에 있는 값을 전부 복사한다.
                        }

                        detect (visited, y, x, i);
                        DFS(cctvIdx + 1, visited);
                    }
                    break;
                case 2:
                    for (int i = 0; i < dx.length; i++) {
                        for (int j = 0; j < N; j++) {
                            visited[i] = Arrays.copyOf(prev[i], M);
                        }
                        detect (visited, y, x, i);
                        detect (visited, y, x, i + 2);
                        DFS(cctvIdx + 1, visited);
                    }
                    break;
                case 3:
                    for (int i = 0; i < dx.length; i++) {
                        for (int j = 0; j < N; j++) {
                            visited[i] = Arrays.copyOf(prev[i], M);
                        }
                        detect (visited, y, x, i);
                        detect (visited, y, x, (i + 1)%4);
                        DFS(cctvIdx + 1, visited);
                    }
                    break;
                case 4:
                    for (int i = 0; i < dx.length; i++) {
                        for (int j = 0; j < N; j++) {
                            visited[i] = Arrays.copyOf(prev[i], M);
                        }
                        detect (visited, y, x, i);
                        detect (visited, y, x, (i + 1)%4);
                        detect (visited, y, x, (i + 2)%4);
                        DFS(cctvIdx + 1, visited);
                    }
                    break;
                case 5:

                    for (int i = 0; i < N; i++) {
                        visited[i] = Arrays.copyOf(prev[i], M);
                    }

                    detect (visited, y, x, 0);
                    detect (visited, y, x, 1);
                    detect (visited, y, x, 2);
                    detect (visited, y, x, 3);
                    DFS(cctvIdx + 1, visited);
                    break;

            }
        }
    }

    public static void detect(int[][] visited, int y, int x, int direction) {
        System.out.println(direction);
        switch (direction) {

            case 0 : //서쪽
                for (int k = x; k >= 0; k--) {
                    if (matrix[y][k] == 6) break;
                    visited[y][k] = 7; //감시하고 있는 구역 표시
                }
                break;
            case 1 : //북쪽
                for (int k = y; k >= 0; k--) {
                    if (matrix[k][x] == 6) break;
                    visited[k][x] = 7; //감시하고 있는 구역 표시
                }
                break;
            case 2 : //동쪽
                for (int k = x; k < M; k++) {
                    if (matrix[y][k] == 6) break;
                    visited[y][k] = 7; //감시하고 있는 구역 표시
                }
                break;
            case 3 : //남쪽
                for (int k = y; k < N; k++) {
                    if (matrix[k][x] == 6) break;
                    visited[k][x] = 7; //감시하고 있는 구역 표시
                }
        }
    }

    public static class Pos {
        int x;
        int y;
        int idx;

        public Pos(int x, int y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;

        }

    }
}