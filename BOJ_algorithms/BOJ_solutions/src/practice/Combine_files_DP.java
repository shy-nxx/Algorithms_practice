package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Combine_files_DP {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		StringBuffer sf = new StringBuffer();
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int k = 0; k < T; k++ ) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			int[] cost = new int[N+1];
			int[] sum = new int[N+1];
		
			st = new StringTokenizer(br.readLine());
			
			for (int i = 1; i <= N; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
				sum[i] = cost[i] + sum[i-1]; //앞에서부터 순처적으로 비용을 모두 더한 값 
			}
			
			int[][] dp = new int[501][501]; 
		
			for (int a = 1; a <= N; a++) {
				for (int i = 1; i + a <= N; i++) {
					int j = i + a;
					dp[i][j] = Integer.MAX_VALUE;
					
					for (int mid = i; mid <= j; mid++) {
						dp[i][j] = Math.min(dp[i][j], dp[i][mid] + dp[mid+1][j] + sum[j] - sum[i-1] );
					}
				}
			}
			sf.append(dp[1][N] + "\n");
			
		}
		System.out.println(sf.toString());
	}
	
}
