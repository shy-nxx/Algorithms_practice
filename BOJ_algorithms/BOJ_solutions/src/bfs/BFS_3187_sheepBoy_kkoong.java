package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BFS_3187_sheepBoy_kkoong {
	static int R, C;
	
	static char[][] garden = new char[251][251];
	static boolean[][] visited = new boolean[251][251];
	
	static int dx [] = {-1,1,0,0};
	static int dy [] = {0,0,-1,1};
	
	static int sheepN, wolfN, totalSheep, totalWolf;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				garden[i][j] = s.charAt(j);
			}
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(!visited[i][j] && garden[i][j] != '#') {
					dfs(i, j);
					if (garden[i][j] == 'v') wolfN++;
					else if (garden[i][j] == 'k') sheepN++;
					checkSurvive();
				}
				
			}
		}
		System.out.println(totalSheep + " " + totalWolf);
	}
	static void dfs(int x, int y) {
		visited[x][y] = true;
		
		for (int j = 0; j < dx.length; j++) {
			int nx = x + dx[j];
			int ny = y + dy[j];
			
			if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny]) continue;
			
			if (garden[nx][ny] == '#') continue;
			
			if (garden[nx][ny] == 'v') wolfN++;
			else if (garden[nx][ny] == 'k') sheepN++;
			
			visited[nx][ny] = true;
			dfs(nx, ny);
			
		}
		
	}
	
	static void checkSurvive() {
		if (sheepN > wolfN) totalSheep += sheepN;
		else {
			totalWolf += wolfN;
		}
		sheepN = wolfN = 0;
	}
	
	static class Position {
		int x, y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}

}
