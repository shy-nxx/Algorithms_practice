package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15651 {
	static int N, M;
	static boolean[] visited;
	static ArrayList<Integer> list = new ArrayList<>();
	static StringBuffer sf = new StringBuffer();
	static int[] arr;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[M];

		visited = new boolean[N+1];

		dfs(0);
		System.out.println(sf.toString());
	}
	static void dfs(int cnt) {

		if (cnt == M) {
			for (int now : arr) {
				sf.append(now + " ");
			}
			sf.append("\n");
			return;
		}
		for (int i = 1; i <= N; i++) {
			
				arr[cnt] = i;
				dfs(cnt+1);
		}



	}
}
