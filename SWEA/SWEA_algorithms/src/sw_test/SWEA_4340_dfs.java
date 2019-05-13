package sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import sw_test.SWEA_4340.Point;

public class SWEA_4340_dfs {
	//벽 부수고 이동하기,탈주범 검거 
	//백트래킹?? 
	static int N;
	static int[][] map;
	static boolean[][] visited;

	static final int RIGHT = 0;
	static final int LEFT = 1;
	static final int UP = 2;
	static final int DOWN = 3;

//	static int[] dx = {0,0,-1,1};
//	static int[] dy = {1,-1,0,0};
//
//	static final int YES = 1;
//	static final int NO = 0;
	
	static int MIN = Integer.MAX_VALUE;
	
//	static boolean[] dir = new boolean[4];
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
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			MIN = Integer.MAX_VALUE;

			
			dfs(N-1, N-1, LEFT ,1); //10.10은 반드시 왼쪽으로 이어지는 파이프 
			sf.append("# " + (k+1) + " " + MIN + "\n");
			
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

//		pipe_init(x,y); //각 파이프 별 갈 수 있는 방향지정 
		
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

//	static boolean checkThrough(int x, int y, int dir) {
//		if (dir ==RIGHT) { //오른쪽 
//			if (map[x][y] == 1 || map[x][y] == 4 || map[x][y] == 5) return true;
//		} else if (dir == LEFT) { //왼
//			if (map[x][y] == 1 || map[x][y] == 3 || map[x][y] == 6) return true;
//		} else if (dir == UP) { //오른쪽 
//			if (map[x][y] == 2 || map[x][y] == 3 || map[x][y] == 4) return true;
//		} else if (dir == DOWN) { //오른쪽 
//			if (map[x][y] == 2 || map[x][y] == 5 || map[x][y] == 6) return true;
//		}
//		return false;
//	}
//	static void pipe_init(int x, int y) {
//
//		switch(map[x][y]) {
//		case 0:
//			Arrays.fill(dir, false);
//			break;
//		case 1:
//			dir[LEFT] = true;
//			dir[RIGHT] = true;
//			break;
//		case 2:
//			dir[UP] = true;
//			dir[DOWN] = true;
//			break;
//		case 3:
//			dir[RIGHT] = true;
//			dir[DOWN] = true;
//			break;
//		case 4:
//			dir[LEFT] = true;
//			dir[DOWN] = true;
//			break;
//		case 5:
//			dir[LEFT] = true;
//			dir[UP] = true;
//			break;
//		case 6:
//			dir[UP] = true;
//			dir[RIGHT] = true;
//			break;
//		}
//
//	}
//	static class Point {
//		int x,y;
//		int left, right, up, down;
//		int isShorten;
//		
//		public Point(int x, int y, int left, int right, int up, int down, int isShorten) {
//			super();
//			this.x = x;
//			this.y = y;
//			this.left = left;
//			this.right = right;
//			this.up = up;
//			this.down = down;
//			this.isShorten = isShorten;
//		}
//
//		
//		public Point(int x, int y, int isShorten) {
//			super();
//			this.x = x;
//			this.y = y;
//			this.isShorten = isShorten;
//		}
//
//
//	}

}
