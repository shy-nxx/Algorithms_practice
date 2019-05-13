package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1970 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		StringBuffer sf = new StringBuffer();
		
		int T = Integer.parseInt(st.nextToken());
		
		int[] coins = {50000, 10000,5000,1000,500,100,50,10};
		
		for (int k = 0; k < T; k++) {
			sf.append("#" + (k+1) + "\n");
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int[] dp = new int[coins.length];
			
			int change = N;
			for (int i =0; i < coins.length; i++) {
				if (coins[i] > N) {
					dp[i] = 0;
					continue; //거스름돈보다 화폐 단위 클 경우 
				}
				
				int n = change / coins[i];
				for (int j = 1; j <= n; j++) {
					change -= coins[i];
					dp[i]++;
				}
				
			}
			
			for (int i = 0; i < dp.length; i++) {
				sf.append(dp[i] + " ");
			}
			
			sf.append("\n");
			
		}
		System.out.println(sf.toString());
	}
}
