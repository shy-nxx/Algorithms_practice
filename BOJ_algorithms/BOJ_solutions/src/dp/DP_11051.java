package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_11051 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		StringBuffer sf = new StringBuffer();
	
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		
		int dp[][] = new int[1000][1000];
		
		dp[0][0] = 1;
		dp[1][0] = 1;
		dp[1][1] = 1;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= i; j++) {
				if (j == 0 || j == i) {
					dp[i][j] = 1;
				}
				else {
					dp[i][j] = (dp[i-1][j] + dp[i-1][j-1]) % 10007;
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}
}
