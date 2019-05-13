package sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_2117_sol {
	//마름모 구하기/ bfs / 맨해튼 거리 계산 
	static int N, M;
	
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	
	static int max_fee = 0;
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
			M = Integer.parseInt(st.nextToken()); //비용 
			
			map = new int[N][N];
			
			max_fee = 0;
			MAX = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) max_fee += M; //모든 집을 커버할 경우 최대 비용 
				}
			}
			
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(i, j); //모든 정점을 돌면서 탐색 
				}
			}
			sf.append("#" + (k+1) + " " + MAX + "\n");
			
		}
		System.out.println(sf.toString());
	}
	static void bfs(int x, int y) {
		int k = 1;
		
		while(true) {
			Queue<Pos> q = new LinkedList<>();
			q.add(new Pos(x, y, 1));
			visited = new boolean[N][N];
			
			int fee = 0; 
			int cnt = 0; //해당 범위 내의 집의 수를 저장 
			int op_fee = k * k + (k-1) * (k-1);
			
			while(!q.isEmpty()) {
				Pos now = q.poll();
				
				int cx = now.x;
				int cy = now.y;
				
				visited[cx][cy] = true;
				
				if (map[cx][cy] == 1) {
					fee += M;
					cnt++; //집의 수를 늘림 
				}
				
				for (int i = 0; i < 4; i++) {
					int nx = cx + dx[i];
					int ny = cy + dy[i];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;
					if (now.cnt < k) {
						q.add(new Pos(nx, ny, now.cnt+1)); //해당 좌표의 영역을 저장  
						visited[nx][ny] = true;
					}
				}	
			}
			fee = fee - op_fee;
			if (op_fee > max_fee) break; //최대비용을 넘을 경우 잘못된 계산 
			if (fee >= 0) MAX = Math.max(MAX, cnt); //수익 = 운영비용도 가능 (수익이 없을 경우도 이익이 된다고 가정함)

			k++;
		}
	}
	static class Pos {
		int x,y;
		int cnt; //N+1보다 이하여야 한다. 
		
		public Pos(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}
