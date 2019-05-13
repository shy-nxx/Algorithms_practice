package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1966 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T =  Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();
		
		for (int k=0; k < T; k++) {
			sf.append("#" + (k+1) + " ");
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			
			int[] nums = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] =Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(nums);
			
			for (int i = 0; i < N; i++) {
				sf.append(nums[i] + " ");
			}
			sf.append("\n");
		}
		System.out.println(sf.toString());
		
	}
}
