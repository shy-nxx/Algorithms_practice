package sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_5644_sol {
	static int M, A;
	
	static int[][] directions = new int[2][101];
	static boolean[][] dp;
	//0 -> 이동 X / 1-> 상 (북) / 2 -> 우(동) / 3 -> 하 (남) / 4 -> 좌 (서)
	static int[] dx = {0,0,1,0,-1};
	static int[] dy = {0,-1,0,1,0};

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();

		for (int k = 0; k < T; k++) {
			sf.append("#" + (k+1) + " ");
			st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken()); //이동 시간 
			A = Integer.parseInt(st.nextToken()); //BC의 개수 
			
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= M; j++ ) {
					directions[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			BC[] bcs = new BC[A];
			
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				
				bcs[i] = new BC(x, y, c, p);
			}
			
			dp = new boolean[2][A];
			
			int ax = 1, ay = 1, bx = 10, by = 10;
			int sum = 0;
			
			for (int i = 0; i <= M; i++) {
				int nax = ax + dx[directions[0][i]];
				int nay = ay + dy[directions[0][i]];
				int nbx = bx + dx[directions[1][i]];
				int nby = by + dy[directions[1][i]];
				
				Arrays.fill(dp[0], false);
				Arrays.fill(dp[1], false);
				
				for (int j = 0; j < A; j++) {
					if (checkRange(nax, nay, bcs[j])) {
						dp[0][j] = true;
					}
					if (checkRange(nbx, nby, bcs[j])) {
						dp[1][j] = true;
					}
				}
				sum += getMax(bcs);
				ax = nax;
				ay = nay;
				bx = nbx;
				by = nby;
				
			}
			sf.append(sum + "\n");
		}
		System.out.println(sf.toString());
	}
	static boolean checkRange(int x, int y, BC bcs) {
		int range = Math.abs(x - bcs.x) + Math.abs(y - bcs.y);
		if (range <= bcs.c) return true;
		return false;
	}
	
	static int getMax(BC[] bcs) {
		int max = 0;
		
		for (int i = 0; i < A; i++) {
			for (int j = 0; j < A; j++) {
				int temp = 0;
				
				if (dp[0][i]) {
					if (dp[1][j]) {
						temp = i == j ? bcs[j].p : bcs[i].p + bcs[j].p;
					}
					else {
						temp = bcs[i].p;
					}
				}
				else {
					if (dp[1][j]) {
						temp = bcs[j].p;
					}
				}
				max = Math.max(max,temp);
			}
			
		}
		return max;
	}
	static class BC {
		int x, y, c, p;

		public BC(int x, int y, int c, int p) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
		
		
	}
}
