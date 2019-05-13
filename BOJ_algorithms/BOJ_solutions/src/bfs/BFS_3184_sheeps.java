package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_3184_sheeps {
	static int R, C;
	static char[][] forest = new char[251][251];
	
	static boolean[][] visited = new boolean[251][251];
	
	static int sheepN, totalSheep, wolfN, totalWolf;
	
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
	
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				forest[i][j]= s.charAt(j);
			}
		}
		
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!visited[i][j] && forest[i][j] != '#' ) {
					bfs(i,j);
					checkSurvive();
					
				}
			}
		}
		
		System.out.println(totalSheep + " " + totalWolf);
	}
	static void bfs(int x, int y) {
		Queue<Position> q = new LinkedList<>();
		
		q.add(new Position (x, y));
		
		if (forest[x][y] == 'v') {
			wolfN++;
		}
		else if (forest[x][y] == 'o'){
			sheepN++;
		}
		
		while(!q.isEmpty()) {
			Position p = q.poll();
			
			int cx = p.x;
			int cy = p.y;
			
			visited[cx][cy] = true;
			
			for (int j = 0; j < dx.length; j++) {
				int nx = cx + dx[j];
				int ny = cy + dy[j];
				
				if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny]) continue;
				
				if (forest[nx][ny] == '#') continue;
				
				if (forest[nx][ny] == 'v') wolfN++;
				else if (forest[nx][ny] == 'o') sheepN++;
				
				visited[nx][ny] = true;
				q.add(new Position(nx, ny));
				
			}
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
