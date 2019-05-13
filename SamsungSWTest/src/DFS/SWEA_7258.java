package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7258 {
    static int R, C;
    static char[][] map;
    static boolean[][][][] check; //행/열/방향/메모리

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0}; //동/서/남/북

    static StringBuffer sf = new StringBuffer();

    static int memo;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int k = 0; k < T; k++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new char[R+1][C+1];

            for (int i = 1; i <= R; i++) {
                String s = br.readLine();
                for (int j = 1; j <= C; j++) {
                    map[i][j] = s.charAt(j-1);
                }
            }

            memo = 0;
            check = new boolean[R+1][C+1][4][16];
            sf.append("#" + (k+1) + " ");
            if (dfs(1,1,0))
                sf.append("YES\n");
            else
                sf.append("NO\n");
        }
        System.out.println(sf.toString());
    }
    static boolean dfs(int x, int y, int dir) {

        boolean flag = false;

        //방향 동서남북
        if (map[x][y] >= '0' && map[x][y] <= '9') memo = map[x][y] - '0';
        else if (map[x][y] =='_'&& memo == 0 || map[x][y] == '>' ) dir = 0 ;
        else if (map[x][y] == '<' || map[x][y] == '_' && memo != 0) dir = 1;
        else if (map[x][y] == 'v' || map[x][y] == '|' && memo == 0) dir = 2;
        else if (map[x][y] == '^' || map[x][y] == '|' && memo != 0) dir = 3;
        else if (map[x][y] == '+' ) memo = (memo + 1) % 15;
        else if (map[x][y] == '-' && memo == 0) memo = 15;
        else if (map[x][y] == '-') memo -= 1;
        else if (map[x][y] == '@') return true;

        if (check[x][y][dir][memo]) return false;
        else check[x][y][dir][memo] = true;

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (nx == 0) nx = R;
        else if (nx == R+1) nx = 1;

        if (ny == 0) ny = C;
        else if (ny == C+1) ny = 1;

        if (map[x][y] == '?') { //똑같은 확률이므로 모든 방향에 대해 탐색하면 된다.
            for (int i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];

                if (nx == 0) nx = R;
                else if (nx == R+1) nx = 1;

                if (ny == 0) ny = C;
                else if (ny == C+1) ny = 1;

                return dfs(nx, ny, i);

            }
        }
        else
            return dfs(nx, ny, dir); //?이 아니면 위에 정한 방향대로 탐색한다.

        return false;
    }
}
