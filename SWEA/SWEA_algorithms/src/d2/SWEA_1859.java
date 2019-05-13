package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1859 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		StringBuffer sf = new StringBuffer();
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int k = 0; k < T; k++) {
			sf.append("#" + (k+1) + " ");
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			int dp[] = new int[N+1];
			int[] cost = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
				
			}
			
			dp[1] = cost[1];
			int result = 0;
			int ans = 0;
			
			
			for (int i = 1; i <= N; i++) {
				dp[i] = Math.max(dp[i], cost[i] * (i-1) - result );
				result += cost[i];
				ans = Math.max(dp[i], ans);
				
			}
			sf.append(ans + "\n");
		
		}
		System.out.println(sf.toString());
	}
}
