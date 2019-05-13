package sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2117_square_search {
	//마름모 탐색 
	static int N, M;
	
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	
	static int house_cnt = 0;
	static int MAX = 0;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();

		for (int z = 0; z < T; z++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken()); //비용 
			
			map = new int[N][N];
			
			MAX = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 1; k <= N+1; k++ ) {
						house_cnt = 0;
						
						solve(i, j, k);
						
						int fee = house_cnt * M - (k * k - (k-1) * (k-1));
						if (fee >= 0) MAX = Math.max(MAX, house_cnt);
					}
					
				}
			}
			
			sf.append("#" + (z+1) + " " + MAX + "\n" );
			
		}
		System.out.println(sf.toString());
	}
	static void solve(int x, int y, int k) {
		int startx = y - k + 1;
		int endx = y + k - 1;
		
		int first = 0;
		int fs = startx;
		int fe = endx;
		int firsty = x;
		
		//가운데부터 위 
		while(first <= k) {
			for (int i = fs; i <= fe; i++) {
				if (i >= 0 && i < N && firsty >= 0 && firsty < N) {
					house_cnt += map[firsty][i];
				} else continue;
			}
			fs++;
			fe--;
			first++;
			firsty--;
			
		}
		
		//가운데부터아래 
		int second = 0;
		int ss = startx+1;
		int se = endx -1;
		int secondy = x+1;
		
		while(second <= k-1) {
			for (int i = ss; i <= se; i++) {
				if (i >= 0 && i < N && secondy >= 0 && secondy < N) {
					house_cnt += map[secondy][i];
				} else continue;
			}
			ss++;
			se--;
			second++;
			secondy++;
			
		}
	}
}
