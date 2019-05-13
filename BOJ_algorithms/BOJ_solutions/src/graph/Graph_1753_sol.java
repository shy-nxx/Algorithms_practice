package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Graph_1753_sol {
	static ArrayList<Edge>[] edges;
	static boolean[] visited;
	static int dp[];
	static int V, E;
	
 	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int S= Integer.parseInt(st.nextToken());
		
		edges = new ArrayList[V+1];
		visited = new boolean[V+1];
		dp = new int[V+1];
		
		Arrays.fill(dp, -1);

		
		for (int i = 1; i <= V; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			edges[a].add(new Edge(b,w));
		}
		
		dijkstra(S);
		
		for (int i = 1; i <= V; i++) {
			if (dp[i] != -1) {
				System.out.println(dp[i]);
			}
			else {
				System.out.println("INF");
			}
		}
	
	}
 	static void dijkstra(int s) {
 		PriorityQueue<Edge> q = new PriorityQueue<>();
 		
 		q.add(new Edge(s, 0));
 		
 		dp[s] = 0;
 		while(!q.isEmpty()) {
 			Edge now = q.poll();
 			
 			visited[now.b] = true;
 			
 			for (int i = 0; i < edges[now.b].size(); i++) {
 				Edge next = edges[now.b].get(i);
 				
 				if (dp[next.b] > dp[now.b] + next.w) {
 					dp[next.b] = dp[now.b] + next.w;
 					q.add(next);
 				}
 			}
 		}
 	}
 	static class Edge implements Comparable<Edge> {
 		int b;
 		int w;
		public Edge(int b, int w) {
			super();
			this.b = b;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			 return (this.w >= o.w) ? 1 : -1;
		}
 		
 		
 	}
}
