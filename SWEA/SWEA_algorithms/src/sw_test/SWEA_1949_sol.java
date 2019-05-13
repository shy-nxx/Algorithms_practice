package sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1949_sol {
	static int N, K;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static int MAX = 0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();

		for (int k = 0; k < T; k++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			visited = new boolean[N][N];
			
			int max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(map[i][j], max);
				}
			}
			
			MAX = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == max) {
						dfs(i, j, 1, map[i][j], false);
					}
						
				}
			}
			sf.append(MAX + "\n");
		}
		System.out.println(sf.toString());
	}
	static void dfs(int x, int y, int cnt, int pre, boolean flag) {
		visited[x][y] = true;
		
		MAX = Math.max(MAX, cnt);
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;
			
			if (!flag) {
				//아직 깎기전
				if (map[x][y] > map[nx][ny]) {
					//내리막길임 
					dfs(nx, ny, cnt+1, map[nx][ny], false);
				}
				else {
					//오르막길 혹은 평지 
					if (map[x][y] > map[nx][ny] - K) {
						dfs(nx, ny, cnt+1, map[x][y]-1, true); //현재 높이의 -1만큼 깎아주면 예외처리를 안해도 된다. 
					}
				}
			}
			else {
				//이미 깎음 
				if (pre > map[nx][ny]) {
					dfs(nx, ny, cnt+1, map[nx][ny], true);
				}
			}
		}
		visited[x][y] = false;
	}
}
