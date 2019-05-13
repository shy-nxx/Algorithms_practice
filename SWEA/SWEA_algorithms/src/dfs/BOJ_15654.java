package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15654 {
	static int N, M;
	static boolean[] visited;
	static ArrayList<Integer> list = new ArrayList<>();
	static StringBuffer sf = new StringBuffer();
	static int[] arr;
	static int[] nums;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[M];
		nums = new int[N+1];
		visited = new boolean[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);
		dfs(0,0);
		System.out.println(sf.toString());
	}
	static void dfs(int start, int cnt) {

		if (cnt == M) {
			for (int now : arr) {
				sf.append(now + " ");
			}
			sf.append("\n");
			return;
		}
		for (int i = 1; i <= N; i++) {
			if (start <= nums[i]) {
				
				arr[cnt] = nums[i];
				dfs(nums[i],cnt+1);
			}
		}



	}
}