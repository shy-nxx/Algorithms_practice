package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DFS_6603 {
	static int K;
	
	static boolean[] visited = new boolean[14];
	static int nums [] = new int[14];
	static int cnt = 0;
	
	static StringBuffer sf = new StringBuffer();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		while((K = Integer.parseInt(st.nextToken())) != 0) {
			
			nums = new int[K];
			visited = new boolean[K];
			
			for (int i = 0; i < K; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
//			for (int i = 0; i < K; i++) {
//				System.out.println(nums[i]);
//			}
			
			//K는 6부터 13까지 -> 즉, 6개면 경우의 수가 1이고  / 13개면 5번만 DFS 실행 하면된다. 
			for (int i = 0; i < K-5; i++) { 
				cnt = 1; //하나 이미 픽함 
				findAllComb(i, nums[i] + " ");
			}
			sf.append("\n");
			st = new StringTokenizer(br.readLine());
		}
		
		System.out.println(sf.toString());
		
	}
	static void findAllComb(int start, String str) {
		if (cnt == 6) {
			sf.append(str + "\n");
			
		} else {
			for (int i = start + 1; i < K; i++) {
				++cnt;
				findAllComb(i, str + nums[i] + " ");
			}
		}
		--cnt; //백트래킹 
		
	}
}
