package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1859_sol {
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
			
			dp[1] = 0;
			
			int ans = 0;
			int max = cost[N];
			
			for (int i = N-1; i >= 1; i--) {
				
				if (cost[i] < max) {
					ans += max - cost[i];
				} else {
					max = Math.max(cost[i], max);
				}
			}
			
			/**
			 * 5
				1 1 3 1 2
				-> 1+1 = 2의 비용 
				3 * 2 - 2 => 4 (이득) 
				현재 가지고 있는 물건  0 
				 1 -> 1의 비용 
				현재 가지고 있는 물건 1
				2 -1 -> 1(이득)
				-> 4+ 1= 5 의 총 이득 
				각 날짜마다의 축적 이득을 구한 후에 팔고 난 물건의 수량을 저장해놓고 
				그걸 사용해야 할듯 
				
				뒤에서부터 풀어야 해 
			 */
		
//			for (int i = N; i >= 1; i--) {
//				dp[i] = Math.max(dp[i], cost[i] * (i-1) - result );
//				result += cost[i]; //물건을 사는 데 써야할 돈  -> 사용한 만큼 제외해야한다. 
//				ans = Math.max(dp[i], ans);
//			}
			
			
			sf.append(ans + "\n");
		
		}
		System.out.println(sf.toString());
	}
	
	
}
