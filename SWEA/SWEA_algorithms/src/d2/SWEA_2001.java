package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2001 {
	static int N, M;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/**
		 * 10:10~
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		
		for (int k = 0; k < T; k++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken()); //모기의 개수가 적힌 배열 
			M = Integer.parseInt(st.nextToken()); //파리채의 크기 
				
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int max = 0;
			
			for (int i = 0; i <= N-M ; i++) {
				for (int j = 0; j <= N-M; j++) {
					max = Math.max(max, getResult(i, j));
				}
			}
			System.out.println("#" + (k+1) + " " + max);
			
		}
	}
	static int getResult(int x, int y) {
		int res = 0;
		
		for (int i = x; i < x + M; i++) {
			for (int j = y; j < y + M; j++) {
				res += map[i][j];
			}
		}
		return res;
	}
}
