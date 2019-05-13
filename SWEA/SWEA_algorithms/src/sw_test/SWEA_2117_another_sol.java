package sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2117_another_sol {
	//맨해튼 거리 
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
					
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					solve(i, j);
				}
			}
			
			sf.append("#" + (k+1) + " " + MAX + "\n" );
			
		}
		System.out.println(sf.toString());
	}
	static void solve(int x, int y) {
		for (int k = 0; k < N+2; k++) {
			int cnt = 0;
			for (int i = 0; i < N; i++ ){
				for (int j = 0; j < N; j++) {
					int nx = Math.abs(i - x);
					int ny = Math.abs(j - y);
					
					if (nx + ny < k && map[i][j] == 1) {
						cnt++; //해당 영역안에 집이 존재함 
					}
					
				}
			}
			int op_fee = k * k + (k-1) * (k-1);
			int fee = (cnt * M) - op_fee;
			if (fee >= 0) MAX = Math.max(MAX, cnt);
		}
	}

}
