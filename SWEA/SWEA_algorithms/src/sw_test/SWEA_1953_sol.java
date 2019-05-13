package sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953_sol {
	static int N, M, R, C, L;
	static int[][] map;
	static boolean[][] visited;
	
	static StringBuffer sf = new StringBuffer();
	
	static final int RIGHT = 0;
	static final int LEFT =1;
	static final int UP = 2;
	static final int DOWN = 3;
	
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T =  Integer.parseInt(st.nextToken());

		
		for (int k=0; k < T; k++) {
			sf.append("#" + (k+1) + " ");
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++)  {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			visited = new boolean[N][M];
			
			bfs();
			
			int ans = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (visited[i][j]) ans++;
				}
			}
			
			sf.append(ans + "\n");
			
		}
		System.out.println(sf.toString());
	}
	static void bfs() {
		Queue<Position> q = new LinkedList<>();
		q.add(new Position(R,C));
		
		visited[R][C] = true;
		
		int time = 0;
		
		while(!q.isEmpty()) {
			time++;
			
			if (time == L) break;
			
			int n = q.size();
			
			for (int i = 0; i < n; i++) {
				
				Position p = q.poll();
				
				int x = p.x;
				int y = p.y;
				
				boolean[] dir = new boolean[4];
				
				switch(map[x][y]) { //각 터널 별 갈 수 있는 방향을 미리 지정해놓는다. 
				case 1 : 
					Arrays.fill(dir, true);
					break;
				case 2:
					dir[UP] = true;
					dir[DOWN] = true;
					break;
				case 3:
					dir[RIGHT] = true;
					dir[LEFT] = true;
					break;
				case 4:
					dir[UP] = true;
					dir[RIGHT] = true;
					break;
				case 5:
					dir[DOWN] = true;
					dir[RIGHT] = true;
					break;
				case 6:
					dir[DOWN] = true;
					dir[LEFT] = true;
					break;
				case 7:
					dir[UP] = true;
					dir[LEFT] = true;
					break;
				}
				
				//이제 파이프 이동 시작 
				
				for (int j = 0; j < 4; j++) {
					if (!dir[j]) continue; //갈 수 없는 방향 
					int nx = x + dx[j];
					int ny = y + dy[j];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || map[nx][ny] == 0) continue;
					
					if (checkThroughOK(nx, ny, j)) {
						visited[nx][ny] = true;
						q.add(new Position(nx, ny));
					}
				}
			}
		}
	}
	static boolean checkThroughOK(int x, int y, int dir) {
		if (dir == RIGHT) {
			if (map[x][y] == 1 || map[x][y] == 3 || map[x][y] == 6 || map[x][y] == 7 ) return true;
		} else if (dir == LEFT) {
			if (map[x][y] == 1 || map[x][y] == 3 || map[x][y] == 4 || map[x][y] == 5 ) return true;
		} else if (dir == DOWN) {
			if (map[x][y] == 1 || map[x][y] == 2 || map[x][y] == 4 || map[x][y] == 7 ) return true;
		} else if (dir == UP) {
			if (map[x][y] == 1 || map[x][y] == 2 || map[x][y] == 5 || map[x][y] == 6 ) return true;
		}
		return false;
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
