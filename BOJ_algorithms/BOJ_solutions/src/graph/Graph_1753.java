package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Graph_1753 {
//	static ArrayList<Integer> list = new ArrayList<>();
//
	static long[][] edges;
	static long[][] dp;
	static int V, E;
	
 	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int S= Integer.parseInt(st.nextToken());
		
		dp = new long[V+1][V+1];
		edges = new long[E+1][E+1];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			edges[a][b] = w;
		}
		for (int i = 1; i <= V; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		dp[S][S] = 0;
		for (int i = 1; i <= V; i++)
			dijkstra(i);
		
		for (int i = 1; i <= V; i++) {
			if (dp[S][i] == -1) {
				System.out.println("INF");
			}
			else {
				System.out.println(dp[S][i]);
			}
		}
	}
 	static void dijkstra(int s) {
 		Queue<Integer> q = new LinkedList<>();
 		
 		q.add(s);
 		
 		while(!q.isEmpty()) {
 			int now = q.poll();
 			
 			for (int i = 1; i <= V; i++) {
 				if (edges[now][i] != 0 && dp[s][i] == -1) {
 					dp[s][i] = dp[s][now] + edges[now][i];
 					q.add(i);
 				}
 			}
 		}
 	}
}
