package sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import sw_test.SWEA_5644.Position;

public class SWEA_5644 {

	static int M, A, C, P;
	//0 -> 이동 X / 1-> 상 (북) / 2 -> 우(동) / 3 -> 하 (남) / 4 -> 좌 (서)
	static int[] dx = {0,0,1,0,-1};
	static int[] dy = {0,-1,0,1,0};

	static Queue<Position> aQ = new LinkedList<>();
	static Queue<Position> bQ = new LinkedList<>();

//	static boolean[][] visited = new boolean[11][11];
	static LinkedList<Integer>[] list = (LinkedList<Integer>[]) new LinkedList[3];

	static boolean[][] dp = new boolean[2][9];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();

		for (int k = 0; k < T; k++) {
			sf.append("#" + (k+1) + " ");
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); //이동시간 
			A = Integer.parseInt(st.nextToken()); //BC의 개수 

			for (int j = 1; j <= 2; j++) {
				list[j] = new LinkedList<>();
				st = new StringTokenizer(br.readLine());
				for (int i = 1; i <= M; i++) {
					int dir = Integer.parseInt(st.nextToken());
					list[j].add(dir);
				}
			}

			BC[] bcs = new BC[A+1];
			for (int i = 1; i <= A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());

				bcs[i] = new BC(x,y,c,p);
			}

			/**
			 * 현재 좌표와 bc와의 거리가c이하-> 맨해튼 거리처럼 x1-x2 + y1-y2 이면 BC의 거리에 해당됨 
			 * 바이러스 문제처럼 퍼지게 하고 DFS로 사람 이동시키고 만약 같은 곳에서 만나면 P를 나누고 더 이득인 것으로 취하게 한다. 
			 * A는 1,1에서 출발하고 B는 10,10에서 출발 
			 * 
			 * path[2][A] 배열을 만들어서 매 시간마다 사용자 A 와 B 가 접근할 수 있는 모든 BC를 체크 한 후,  
			 * getMax 라는 함수를 통해 A와 B가 선택 가능한 BC의 모든 조합을 조사해 그중에서 최대 배터리 충전량을 구하는 식으로 문제를 해결했다.
			 */

			Position a = new Position(1, 1, 0);
			Position b = new Position(10, 10, 0);
			
			int sum = 0;
			
			for (int i = 1; i <= M; i++) {
				//일단 방향에 맞게이동 시킴 
				a.x += dx[list[1].get(i)];
				a.y += dy[list[1].get(i)];
				
				b.x += dx[list[2].get(i)];
				b.y += dy[list[2].get(i)];
				
				Arrays.fill(dp[0], false);
				Arrays.fill(dp[1], false);
				
				
				for (int j = 1; j <= A; j++) {
					if (checkRange(a.x, a.y, bcs[j])) {
						dp[0][j] = true;
					}
					if (checkRange(b.x, b.y, bcs[j])) {
						dp[1][j] = true;
					}
					sum += getMax(bcs);
					
				}
			}
			sf.append(sum + "\n");
		}

		System.out.println(sf.toString());
	}

	static int getMax(BC[] bcs) {
		int max = 0;
		
		for (int i = 1; i <= A; i++) {
			for (int j = 1; j <= A; j++) {
				int temp = 1;
				if (dp[0][i]) { //j번째 충전소의 범위에 있을 경우 
					if (dp[1][j]) {
						temp = i == j ?  bcs[i].p : bcs[i].p + bcs[j].p;
					}
					else {
						temp = bcs[i].p;
					}
					
				}
				else {
					if (dp[1][i]) {
						temp = bcs[i].p;
					}
				}
				max = Math.max(max, temp);
			}
		}
		return max;
	}
	static boolean checkRange(int x, int y, BC bcs) {
		if (x < 1 || x > 10 || y < 1 || y > 10) return false;

		int range = Math.abs(x- bcs.x) + Math.abs(y - bcs.y);
		if (range <= bcs.c) return true;
		return false;
	}

//	static void moveA(int x, int y, BC[] bcs) {
//		Queue<Position> q = new LinkedList<>();
//
//		while(!q.isEmpty()) {
//			Position p = q.poll();
//
//			int cx = p.x;
//			int cy = p.y;
//			int d = p.dir;
//
//			visited[cx][cy]= true;
//
//			//방향 고려 
//			//0 -> 이동 X / 1-> 상 (북) / 2 -> 우(동) / 3 -> 하 (남) / 4 -> 좌 (서)
//			int nx = cx + dx[d];
//			int ny = cy + dy[d];
//
//			if (nx < 1 || nx > 10 || ny < 1 || nx > 10 || visited[nx][ny]) continue;
//
//			//bc의 범위와 겹치면 그 성능을 가져온다. 
//			for (int j = 0; j < bcs.length; j++) {
//				int rangeX = Math.abs(nx - bcs[j].x);
//				int rangeY = Math.abs(ny - bcs[j].y);
//
//				if (rangeX + rangeY <= bcs[j].c) {
//					dp[1][nx][ny] = Math.max(dp[1][nx][ny], bcs[j].p);
//				}
//
//			}
//			
//			
//		}
//	}
//	static void moveB(int x, int y, BC[] bcs) {
//
//		Queue<Position> q = new LinkedList<>();
//
//		q.add(new Position(x, y, list[2].get(0)));
//
//		while(!q.isEmpty()) {
//			Position p = q.poll();
//
//			int cx = p.x;
//			int cy = p.y;
//
//			visited[cx][cy]= true;
//
//			for (int i = 0; i < 4; i++) {
//				int nx = cx + dx[i];
//				int ny = cy + dy[i];
//
//				if (nx < 1 || nx > 10 || ny < 1 || nx > 10 || visited[nx][ny]) continue;
//
//				//bc의 범위와 겹치면 그 성능을 가져온다. 
//				for (int j = 0; j < bcs.length; j++) {
//					int rangeX = Math.abs(nx - bcs[j].x);
//					int rangeY = Math.abs(ny - bcs[j].y);
//
//					if (rangeX + rangeY <= bcs[j].c) {
//						dp[1][nx][ny] = Math.max(dp[1][nx][ny], bcs[j].p);
//					}
//
//				}
//
//			}
//
//		}
//	}
	static class Position {
		int x,y;
		int dir;
		public Position(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}


	}
	static class BC {
		int x,y;
		int c;
		int p;
		public BC(int x, int y, int c, int p) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}


	}
}
