package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BFS_11559_puyopuyo {
    /**
     * 뿌요뿌요의 룰은 다음과 같다.
     * <p>
     * 필드에 여러 가지 색깔의 뿌요를 놓는다. 뿌요는 중력의 영향을 받아 아래에 바닥이나 다른 뿌요가 나올 때까지 아래로 떨어진다.
     * <p>
     * 뿌요를 놓고 난 후, 같은 색 뿌요가 4개 이상 상하좌우로 연결되어 있으면 연결된 같은 색 뿌요들이 한꺼번에 없어진다.
     * <p>
     * 뿌요들이 없어지고 나서 위에 다른 뿌요들이 있다면, 역시 중력의 영향을 받아 차례대로 아래로 떨어지게 된다.
     * <p>
     * 아래로 떨어지고 나서 다시 같은 색의 뿌요들이 4개 이상 모이게 되면 또 터지게 되는데, 터진 후 뿌요들이 내려오고 다시 터짐을 반복할 때마다 1연쇄씩 늘어난다.
     * <p>
     * 터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고 여러 그룹이 터지더라도 한번의 연쇄가 추가된다.
     * 남규는 최근 뿌요뿌요 게임에 푹 빠졌다.
     * 이 게임은 1:1로 붙는 대전게임이라 잘 쌓는 것도 중요하지만, 상대방이 터뜨린다면 연쇄가 몇 번이 될지 바로 파악할 수 있는 능력도 필요하다.
     * 하지만 아직 실력이 부족하여 남규는 자기 필드에만 신경 쓰기 바쁘다. 상대방의 필드가 주어졌을 때, 연쇄가 몇 번 연속으로 일어날지 계산하여 남규를 도와주자!
     */

    static char[][] map = new char[13][7];
    static boolean[][] visited;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int max = 0;

    static ArrayList<Point> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 12; i++) {
            String s = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = s.charAt(j);
            }
        }


        boolean flag= false;


        while(true) {
            visited = new boolean[12][6];
            flag = false;

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {

                    System.out.println(map[i][j]);
                    if (map[i][j] != '.' && !visited[i][j]) {
                        list = new ArrayList<>();
                        dfs(i, j, map[i][j]);

                        if (list.size() >= 4) {
                            flag = true;

                            for (Point p : list) {
                                map[p.x][p.y] = '.';
                            }

                        }
                    }
                }
            }
            if (!flag) {
                break;
            }
            else {
                max++;
            }
            pangpang();
        }
        System.out.println(max);
    }

    /**
     *
     * 밑에 있는 게 터지면 위에 있는 게 내려와야 한다. -> 어디서 터지는지 저장해야한다.
     *
     *
     * */
    static void dfs(int x, int y, char ch) {
        for (int j = 0; j < 4; j++) {
            int nx = x + dx[j];
            int ny = y + dy[j];

            if (nx < 0 || nx >= 12 || ny < 0 || ny >= 6 ) continue;

            if (!visited[nx][ny] && map[nx][ny] == ch) {
                visited[nx][ny] = true;
                list.add(new Point(nx, ny));
                dfs(nx, ny,ch);

            }
        }
    }

    static void pangpang() {
        for (int i = 0; i < 6; i++) {
            for (int j = 10; j >= 0; j--) {
                for (int k = 11; k > j; k--) {
                    if (map[j][i] != '.' && map[k][i] == '.') {
                        map[k][i] = map[j][i];
                        map[j][i] = '.';
                        break;
                    }
                }
            }
        }
    }

    static class Point {
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}