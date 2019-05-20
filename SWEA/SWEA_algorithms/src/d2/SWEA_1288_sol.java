package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1288_sol {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		boolean[] visited = new boolean[10];
		
		StringBuffer sf = new StringBuffer();
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());

			Arrays.fill(visited, false);
			
			int result = 1;
			boolean flag = false;
			while(true) {
				int temp = N*result;

				while(temp > 0) {
					visited[temp % 10] = true; 
					
					temp /= 10;
				}
				
				
				for (int i = 0; i <= 9; i++) {
					
					if (!visited[i]) {
						flag = false;
						break;
					}
					flag= true;
				}

				if (flag) break;
				result++;
			}

			sf.append("#" + tc + " " + result*N + "\n");
		}
		
		System.out.println(sf.toString());
	}
}
