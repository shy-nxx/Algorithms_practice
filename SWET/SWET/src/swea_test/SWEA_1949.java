package swea_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_1949 {
	static int N, K;
	static int[][] map;
	static boolean[][] visited;
	
	static ArrayList<Position> list = new ArrayList<>();
	
	static int MAX = 0;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());// (3 ≤ N ≤ 8)
			K = Integer.parseInt(st.nextToken());// (1 ≤ K ≤ 5)
			
			map = new int[N][N];
			visited = new boolean[N][N];
			
			int max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, map[i][j]);
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == max) dfs(i, j, 1, map[i][j], false);
				}
			}
			
			System.out.println(MAX);
			
		}
	}
	static void dfs(int x, int y, int cnt, int prev, boolean flag) { //cnt모인 길의 개수
		
		visited[x][y] = true;
		
		MAX = Math.max(MAX, cnt);
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;
			
			if (!flag) { //아직 안 깎음 
				if (map[nx][ny] < map[x][y]) { //깍을 필요 없다. 
					dfs(nx, ny, cnt+1, map[nx][ny], false);
				}
				else {
					if (map[nx][ny] - K < map[x][y]) {
						dfs(nx, ny, cnt+1, map[nx][ny]-1, true);
					}
				}
			}
			else { //이미 깎음 
				if (map[nx][ny] < prev)
					dfs(nx, ny, cnt+1, map[nx][ny], true);
			}
		}
		visited[x][y] = false;
	}
	static class Position{
		int x, y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		
	}
}
