package swea_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SWEA_5644 {
	static int M, C;
	static final int N = 10;
	
	static final int up = 1;
	static final int right = 2;
	static final int down = 3;
	static final int left = 4;

	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static BC[] bcs = new BC[9];

	static int MAX = 0;
	
	static boolean[][] dp = new boolean[2][9];
	static int[][] directions = new int[2][101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			M = Integer.parseInt(st.nextToken()); //총 이동 시간 
			C= Integer.parseInt(st.nextToken()); // 충전소의 개수 
		
			bcs = new BC[9];

			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int dir = Integer.parseInt(st.nextToken());
					directions[i][j] = dir;
				}
			}

			for (int i = 0; i < C; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int cov = Integer.parseInt(st.nextToken());
				int per = Integer.parseInt(st.nextToken());

				bcs[i] = new BC(x, y, cov, per);
			}
			
			dp = new boolean[2][9]; //모든 충전소를 돌면서 A와 B가 충전한 값을 저장 
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					
				}
			}
			
		}
	}

	static class BC {
		int x, y;
		int cover;
		int perform;

		public BC(int x, int y, int cover, int perform) {
			super();
			this.x = x;
			this.y = y;
			this.cover = cover;
			this.perform = perform;
		}
	}
}
