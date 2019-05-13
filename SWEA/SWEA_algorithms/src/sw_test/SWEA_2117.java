package sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_2117 {
	static int N, M;
	static int[][] map;
	static int[] houses;
	static boolean[][] visited;
	static int[] dp; //모든 경우의 비용을 저장함 

	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	static int profit = 0;

	static int max_fee = 0;
	
	static int MAX = 0;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();

		for (int z = 0; z < T; z++) {
			sf.append("#" + (z+1) + "\n");
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken()); //도시의 크기 
			M = Integer.parseInt(st.nextToken()); //하나의 집이 지불할 비용 

			map = new int[N][N];
			
			max_fee = 0;
			MAX = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] =Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) max_fee += M; //최대비용 
				}
			}


			for (int i = 0;  i < N; i++) {
				for (int j = 0;  j < N; j++) {
					bfs(i, j); //각 지점에 대해 bfs를 호출한다. 
				}
			}
		
			System.out.println(MAX);
		}
		
	}

	static void bfs(int x, int y) {
		int k = 1;
		while(true) { //범위를 넓혀간다. 
			Queue<Pos> q = new LinkedList<>(); //기준 점이 달라지기 때문에 이전 큐의 값을 유지할 필요가 없다. 
			q.add(new Pos(x, y, 1));
			visited = new boolean[N][N];
			
			int fee = 0;
			int cnt = 0;
			int op_fee = k * k + (k-1) * (k-1);
			
			while(!q.isEmpty()) {
				Pos now = q.poll();
				int cx = now.x;
				int cy = now.y;

				visited[cx][cy] = true;
				
				if (map[cx][cy] ==1) {
					fee += M;
					cnt++; //범위 안의 집의 수를 늘려준다. 
				}
				

				System.out.println(cnt + " " + k);
				for (int j = 0; j < 4; j++) {
					int nx = cx + dx[j];
					int ny = cy + dy[j];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

					if (now.cnt < k &&!visited[nx][ny]) {
						visited[nx][ny] = true;
						Pos next= new Pos(nx, ny, now.cnt+1);
						q.add(next);
					}
				}

			}
			fee = fee - op_fee;
//			System.out.println(fee);
			if (fee >= 0) {
				MAX = Math.max(MAX, cnt);
			}
			if (op_fee > max_fee) { //최대 비용 보다 운영비가 큰 경우는 가망이 없다. -> 더 이상 범위를 넓히지 못하는 제한 선 
				break;
			}
			k++;

		}
		
	}

	static class Pos {
		int x,y;
		int cnt;
		
		public Pos(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}


	}
}
