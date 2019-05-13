package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1984_average {
	static int COUNT = 10;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		StringBuffer sf = new StringBuffer();
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int k = 0; k < T; k++) {
			sf.append("#" + (k+1) + " ");
			st = new StringTokenizer(br.readLine());
			
			int[] nums = new int[COUNT+1];
			for (int i = 1; i <= COUNT; i++)
				nums[i] = Integer.parseInt(st.nextToken());

			Arrays.sort(nums);
			
			int sum = 0;
			for (int i = 1; i <= COUNT; i++) {
				if (i == 1 || i == COUNT) continue;
				sum += nums[i];
			}
			
			double avg = Math.round(sum / (double)(COUNT-2));
			
			sf.append(Math.round(avg) + "\n");
		}
		System.out.println(sf.toString());
	}
	
	
}
