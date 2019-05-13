package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_11066 {
	static final int YES = 1;
	static final int NO = 0;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		StringBuffer sf = new StringBuffer();
		
		int T = Integer.parseInt(st.nextToken());

		for (int k = 0; k < T; k++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());

			int[] cost = new int[N+1];
			int[][] dp = new int[501][501]; //i번째 장부터 j번재 장까지 합치는 드는 최소 비용 

			st = new StringTokenizer(br.readLine());

			int[] sum = new int[501];
			
			for (int i = 1; i <= N; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
				sum[i] = cost[i] + sum[i-1];
			}
			
			for (int a = 1; a <= N; a++) {
				for (int i = 1; i + a <= N; i++) {
					int j = i + a;
					dp[i][j] = Integer.MAX_VALUE;
					
					//a가 가장 바깥에서 1부터 마지막 장까지 돌 동안 i와j는 1장부터 앞장들을 더해 가면서 각각의 작은 수를 찾는다. 
					//
//					System.out.print(a + " " + i + " ");
					for (int mid = i; mid < j; mid++) {
//						System.out.print(mid);
						dp[i][j] = Math.min(dp[i][j], dp[i][mid] + dp[mid+1][j] + sum[j] -sum[i-1]);
//						System.out.println( dp[i][j] + " " + (dp[i][mid] + dp[mid+1][j] + sum[j] -sum[i-1]));
//						System.out.println();
						}
				}
			}

			sf.append(dp[1][N] + "\n");

		}
		System.out.println(sf.toString());
	}
}
