package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1859_remind {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			long[] cost = new long[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				cost[i] = Long.parseLong(st.nextToken());
			}
			
			long max = cost[N-1];
			long ans = 0;
			
			for (int i = N-1; i >= 0; i--) {
				if (max > cost[i]) {
					ans += max - cost[i]; //4 3
				}
				else {
					max = Math.max(max, cost[i]);
				}
			}
			
			//1 1 3 1 2 -> 뒤에서 부터 계산한다. 
			
			sf.append("#" + tc + " " + ans + "\n");
			
		}
		System.out.println(sf.toString());
	}
}
