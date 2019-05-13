package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DFS_9521 {
	static int N, K;
	static boolean[] visited;
	static int[][] fi;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); //그림 
		K = Integer.parseInt(st.nextToken()); //물감 

		fi = new int[N+1][N+1];
		visited = new boolean[N+1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int b = Integer.parseInt(st.nextToken()); 
			fi[i][b] = -1; //i번째 그림은 b번째 그림과 같은 물감 색으로 칠하면안된다. 
			fi[b][i] = -1;
		}

		//		for (int a = 1; a <= N; a++) {
		//			for (int i = 1; i <= N; i++) {
		//				for (int j = 1; j <= N; j++) {
		//					//i < a , i<a && a<j -> i<j
		//					if (fi[i][a] == -1 && fi[i][a] == fi[a][j]) {
		//						fi[i][j] = fi[i][a];
		//					}
		//				}
		//			}
		//		}
		//		
		//		for (int i = 1; i <= N; i++) {
		//			for (int j= 1; j <= N; j++) {
		//				System.out.print(fi[i][j] + " ");
		//			}
		//			System.out.println();
		//		}
		//		
		for (int i = 1; i <= N; i++) {
			if (!visited[i])
				dfs(i);

		}

	}
	static void dfs(int start) {
		visited[start] = true;
		
		for (int i = 1; i <= N; i++) {
			if (fi[start][i] == start && visited[i]) {
				
			}
		}
	}

}
