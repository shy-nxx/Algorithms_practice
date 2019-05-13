package sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_4340 {
	//벽 부수고 이동하기,탈주범 검거 
	//백트래킹?? 
	static int N;
	static int[][] map;
	static boolean[][][] visited;

	static final int RIGHT = 0;
	static final int LEFT = 1;
	static final int UP = 2;
	static final int DOWN = 3;

	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};

	static final int YES = 1;
	static final int NO = 0;
	
	static int MIN = Integer.MAX_VALUE;
	
	static boolean[] dir = new boolean[4];
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
			visited = new boolean[N][N][2];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			MIN = Integer.MAX_VALUE;
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					if (!visited[i][j][YES] && map[i][j] != 0)
//						bfs(0,0);
//				}
//			}

			bfs(0,0); //0.0은 반드시 왼쪽으로 이어지는 파이프 
			sf.append("# " + (k+1) + " " + MIN + "\n");
			
		}
		System.out.println(sf.toString());
	}

	static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y, NO));
		visited[x][y][YES] = true;
		visited[x][y][NO] = true;

		int count = 0;
		while(!q.isEmpty()) {
			++count;
			
			int n = q.size();
			for (int j = 0; j < n; j++ ) {
				Point p = q.poll();

				int cx = p.x;
				int cy = p.y;
				
				if (cx == N-1 && cy == N-1) {
					MIN = Math.min(MIN, count);
					return;
				}

				dir = new boolean[4];
				pipe_init(cx,cy);

				for (int i = 0; i < 4; i++) {
					if (!dir[i]) continue;
					int nx = cx + dx[i];
					int ny = cy + dy[i];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 0 ) continue;

					if (checkThrough(nx, ny, i)) { //갈 수 있는 곳 -> 그냥 감 
						if (!visited[nx][ny][p.isShorten])  {
							q.add(new Point(nx, ny, p.isShorten));
							visited[nx][ny][p.isShorten] = true;
						}
					} 
					else {
						System.out.println(nx + " " + ny);
						if (!visited[nx][ny][YES]) {
							if (p.isShorten < YES) {
								visited[nx][ny][YES] = true;
								/**
								 * 여기서 백트래킹으로 전환을 하느냐 아니면 dir 값을 저장해서 그 값을 대입하느냐 ....
								 * 바로 다음의 파이프가 내가 방향전환을 하면 닿을 수 있는 곳이면 바꾼다? -> 그럼 여기가 더 이상 내가 갈 수 없는 곳이니까 내 방향을 돌려줘야하는데..
								 * 역시 dfs인가?? 
								 * 
								 */
								
								q.add(new Point(nx, ny, YES));
							}
						}
					}
				}
			}
		}
	}

	static boolean checkThrough(int x, int y, int dir) {
		if (dir ==RIGHT) { //오른쪽 
			if (map[x][y] == 1 || map[x][y] == 4 || map[x][y] == 5) return true;
		} else if (dir == LEFT) { //왼
			if (map[x][y] == 1 || map[x][y] == 3 || map[x][y] == 6) return true;
		} else if (dir == UP) { //오른쪽 
			if (map[x][y] == 2 || map[x][y] == 3 || map[x][y] == 4) return true;
		} else if (dir == DOWN) { //오른쪽 
			if (map[x][y] == 2 || map[x][y] == 5 || map[x][y] == 6) return true;
		}
		return false;
	}
	static void pipe_init(int x, int y) {

		switch(map[x][y]) {
		case 0:
			Arrays.fill(dir, false);
			break;
		case 1:
			dir[LEFT] = true;
			dir[RIGHT] = true;
			break;
		case 2:
			dir[UP] = true;
			dir[DOWN] = true;
			break;
		case 3:
			dir[RIGHT] = true;
			dir[DOWN] = true;
			break;
		case 4:
			dir[LEFT] = true;
			dir[DOWN] = true;
			break;
		case 5:
			dir[LEFT] = true;
			dir[UP] = true;
			break;
		case 6:
			dir[UP] = true;
			dir[RIGHT] = true;
			break;
		}

	}
	static class Point {
		int x,y;
		int left, right, up, down;
		int isShorten;
		
		public Point(int x, int y, int left, int right, int up, int down, int isShorten) {
			super();
			this.x = x;
			this.y = y;
			this.left = left;
			this.right = right;
			this.up = up;
			this.down = down;
			this.isShorten = isShorten;
		}

		
		public Point(int x, int y, int isShorten) {
			super();
			this.x = x;
			this.y = y;
			this.isShorten = isShorten;
		}


	}

}
