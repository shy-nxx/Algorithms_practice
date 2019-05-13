package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DFS_LOTTO_sol {
	static int K;
	static int[] nums = new int[13];
	
	static int cnt = 0;
	
	static StringBuffer sf = new StringBuffer();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		while((K = Integer.parseInt(st.nextToken())) != 0) {
			for (int i = 0; i < K; i++ ) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < K-5; i++ ) {
				cnt = 1;
				findAllComb(i, nums[i] + " ");
			}
			
			sf.append("\n");
			st = new StringTokenizer(br.readLine());
		}
		System.out.println(sf.toString());	
	}
	
	static void findAllComb(int start, String str ) {
		if (cnt == 6) {
			sf.append(str + "\n");
			
		} else {
			for (int i = start + 1; i < K; i++) {
				++cnt;
				findAllComb(i, str + nums[i] + " ");
			}
		}
		--cnt; //백트래킹 (6을 달성했을 때 하나씩 빼줌)
	}
}
