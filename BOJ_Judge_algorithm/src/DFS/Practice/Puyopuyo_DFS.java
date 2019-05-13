package DFS.Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Puyopuyo_DFS {
    /**
     * 같은 문자가 4개 연달아 나오면 터진다. 해당 문자가 사라지고 위에 있던 도형들이 중력의 영향을 받아 내려오면서 문자가 바뀐다.
     * -> dfs를 돌면서 같은 문자열이 나오면 list에 담고 list의 사이즈가 4가 되면 list안의 문자열들을 .으로 치환한 후
     * 팡팡 터트린다. (위의 문자들 혹은 .이 아래로 내려온다)
     */

    static char[][] map;
    static boolean[][] visited;

    static ArrayList<Point> list = new ArrayList<>();

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        map = new char[12][6];

        for (int i = 0; i < 12; i++) {
            String s = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] =s.charAt(j);
            }
        }

        boolean flag = false;

        int result = 0;

        while(true) {
            visited = new boolean[12][6];
            flag= false;

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] != '.' && !visited[i][j]) {
                        list = new ArrayList<Point>();
                        dfs(i, j, map[i][j]);
                        if (list.size() == 4) {
                            flag= true;
                            for (Point p : list) {
                                map[p.x][p.y] = '.'; //뿌요 터트리기
                            }
                        }
                    }
                }
            }
            if (!flag) {
                break;
            }
            else {
                System.out.println(result);
                result++;
            }

            pangpang(); //터트린 곳 다시 메꾸기
        }

        System.out.println(result);

    }
    static void dfs(int x, int y, char ch) {

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= 12 || ny < 0 || ny > 6) continue;

            if (!visited[nx][ny] && map[nx][ny] == ch) {
                visited[nx][ny] = true;
                list.add(new Point(nx, ny));
                dfs(nx, ny, ch);
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
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}