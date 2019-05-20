package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1204 {
	static final int N = 1000;
	static int[] dp = new int[101];
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int t = Integer.parseInt(st.nextToken());
			
			dp = new int[101];
			
			int max = 0;
			int index= 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 1000; i++) {
				int score = Integer.parseInt(st.nextToken());
				
				dp[score]++;
				if (max <= dp[score]) {
					max = dp[score];
					index = score;
				}
			}
			
			sf.append("#" + t + " " + index + "\n");
			
			
		}
		System.out.println(sf.toString());
	}
}
