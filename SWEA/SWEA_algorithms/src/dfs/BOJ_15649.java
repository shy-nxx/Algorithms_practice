package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15649 {
	static int N, M;
	static boolean[] visited;
	static StringBuffer sf = new StringBuffer();
	static int[] arr;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new boolean[N+1];
		arr = new int[M];
		
		dfs(0);
		System.out.println(sf.toString());
	}
//	static void dfs(int start, int cnt, String str) {
//
//		if (cnt == M) {
//			sf.append(str.trim() + "\n");
//			return;
//		}
//		for (int i = 1; i <= N; i++) {
//			if (!visited[i]) {
//				visited[i] = true;
//				
//				dfs(i, cnt+1, str + " " + i);
//				
//				visited[i] = false;
//			}
//		}
//
//	}
	static void dfs(int cnt) {
		if (cnt == M) {
			for (int now : arr ) {
				sf.append(now + " ");
			}
			sf.append("\n");
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				visited[i ] = true;
				arr[cnt] = i;
				dfs(cnt+1);
				visited[i] = false;
			}
		}
	}
}
