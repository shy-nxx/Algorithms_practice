package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_3184_sheep {
	static int R, C;
	
	static char[][] forest = new char[251][251];
	static boolean[][] visited = new boolean[251][251];
	
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	
	static int sheepN = 0, totalSheep = 0;
	static int wolfN = 0, totalWolf = 0;
	
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
		
		//영역 구하기 (#를 지나지 않고 수직 수평으로 이동할 수 있는 영역 -> BFS)
		
//		while(true) {
//			if (sheepQ.isEmpty() || wolfQ.isEmpty()) {
//				System.out.println(sheepQ.size() + " " +  wolfQ.size());
//				break;
//			}
//			extendLimitArea(wolfQ);
//			
//			surviveSheep();
//			
//			
//		} 
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (forest[i][j] != '#' && !visited[i][j]) {
					bfs(i, j);
					
					surviveSheep();
					
				}
			}
				
		}
		System.out.println(totalSheep + " " + totalWolf);
		
	}
	
	static void bfs(int x, int y) {
		Queue<Position> q = new LinkedList<>();

		if (forest[x][y] == 'v') {
			wolfN++;
		}
		else if (forest[x][y] == 'o') {
			sheepN++;
		}
		
		q.add(new Position (x, y));
		
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
				
				if (forest[nx][ny] == 'o') {
					sheepN++;
				}
				
				else if (forest[nx][ny] == 'v') {
					wolfN++;
				}
				visited[nx][ny] = true;
				forest[nx][ny] = '#';
				q.add(new Position(nx, ny));
//				System.out.println(sheepN + " " + wolfN);
				
			}
			
		}
	}
	

	static void surviveSheep() {
		//마주치면 둘 중 하나는 사라짐 
		
		if (sheepN > wolfN) totalSheep += sheepN;
		else {
			totalWolf += wolfN;
		}
		sheepN = wolfN = 0; 
	}
	
//	static void extendLimitArea(Queue<Position> q) {
//		int n = q.size();
//		
//		for (int i = 0; i < n; i++) {
//			Position p = q.poll();
//			
//			int x = p.x;
//			int y = p.y;
//			
//			for (int j = 0; j < dx.length; j++) {
//				int nx = x + dx[i];
//				int ny = y + dy[i];
//				
//				if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
//				
//				if (forest[nx][ny] == '#') continue;
//				
//				if (forest[nx][ny] == 'v') {
//					wolfN++;
//					q.add(new Position(nx, ny));
//				}
//			}
//		}
//	}
	
	static class Position {
		int x, y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		
	}
}
