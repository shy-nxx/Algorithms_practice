package sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4340_sol {
	static int N;
	static int[][] map;
	static boolean[][] visited;

	static int MIN = Integer.MAX_VALUE;

	static final int LEFT = 0;
	static final int RIGHT = 1;
	static final int UP = 2;
	static final int DOWN = 3;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();

		for (int k = 0; k < T; k++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			visited = new boolean[N][N];

			MIN = Integer.MAX_VALUE;

			//up-> down 방식으로 끝점부터 진행한다. 

			dfs(N-1, N-1, LEFT, 1);//-> 백 트래킹으로 진행한다. 

			sf.append("#" + (k+1) + " " + MIN  + "\n");
		}
		System.out.println(sf.toString());
	}

	static void dfs(int x, int y, int dir, int cnt) {

		visited[x][y] =true;
		
		if (x == 0 && y == 0 && ((map[x][y] < 3 && dir == LEFT) || (map[x][y] > 2 && dir == UP ))) { 
			MIN = cnt;
			visited[x][y] = false;
			return;
		}
		
		if (map[x][y] < 3) { //좌우 / 상하로만 파이프 방향이 바뀜 
			if (dir == UP) {
				int tx = x-1, ty = y;
				if (tx >= 0 && tx < N && ty >= 0 && ty < N && map[tx][ty] != 0 && !visited[tx][ty] && tx + ty + 1 + cnt < MIN ) {
					dfs(tx, ty, dir, cnt+1);
				}
			} else if (dir == DOWN) {
				int tx = x+1, ty = y;
				if (tx >= 0 && tx < N && ty >= 0 && ty < N && map[tx][ty] != 0 && !visited[tx][ty] && tx+ty + 1 + cnt < MIN) {
					dfs(tx,ty,dir, cnt+1);
				}
			} else if (dir == RIGHT) {
				int tx = x, ty = y+1;
				if (tx >= 0 && tx < N && ty >= 0 && ty < N && map[tx][ty] != 0 && !visited[tx][ty] && tx+ty + 1 + cnt < MIN) {
					dfs(tx,ty,dir, cnt+1);
				}
			} else {
				int tx = x, ty = y-1;
				if (tx >= 0 && tx < N && ty >= 0 && ty < N && map[tx][ty] != 0 && !visited[tx][ty] && tx+ty + 1 + cnt < MIN) {
					dfs(tx,ty,dir, cnt+1);
				}
			}
			
		}
		else {
			if (dir == UP || dir == DOWN) {
				int tx = x, ty = y-1;
				if (tx >= 0 && tx < N && ty >= 0 && ty < N && map[tx][ty] != 0 && !visited[tx][ty] && tx+ty + 1 + cnt < MIN) {
					dfs(tx,ty, LEFT, cnt+1);
				}
				tx = x; ty = y+1;
				if (tx >= 0 && tx < N && ty >= 0 && ty < N && map[tx][ty] != 0 && !visited[tx][ty] && tx+ty + 1 + cnt < MIN) {
					dfs(tx,ty,RIGHT, cnt+1);
				}
			}
			else {
				int tx = x-1, ty = y;
				if (tx >= 0 && tx < N && ty >= 0 && ty < N && map[tx][ty] != 0 && !visited[tx][ty] && tx+ty + 1 + cnt < MIN) {
					dfs(tx,ty,UP, cnt+1);
				}
				tx = x+1; ty = y;
				if (tx >= 0 && tx < N && ty >= 0 && ty < N && map[tx][ty] != 0 && !visited[tx][ty] && tx+ty + 1 + cnt < MIN) {
					dfs(tx,ty,DOWN, cnt+1);
				}
			}
		}
		
		visited[x][y] = false;
		return;
	}
}
