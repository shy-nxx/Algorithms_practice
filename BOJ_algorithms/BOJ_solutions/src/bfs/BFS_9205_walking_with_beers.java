package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_9205_walking_with_beers {

	static int N;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		StringBuffer sf = new StringBuffer();
		
		int T = Integer.parseInt(st.nextToken());

		for (int k = 0; k < T; k++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //편의점의 개수 
		 
			ArrayList<Position> list = new ArrayList<>();
			
			boolean[] visited = new boolean[101];
			
			int[][] dp = new int[101][101];
			
			for (int i = 1; i <= N + 2; i++) {
				st = new StringTokenizer(br.readLine());
				list.add(new Position(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			}
			
			if (bfs(list, visited)) {
				sf.append("happy\n");
				
			}
			else {
				sf.append("sad\n");
			}
			
		}
		System.out.println(sf.toString());
	}
	static boolean bfs(ArrayList<Position> list, boolean[] visited) {
		Queue<Position > q = new LinkedList<>();
		q.add(list.get(0)); //집 위치부터 넣음 
		visited[0]= true;
		
		while(!q.isEmpty()) {
			Position p = q.poll();

			if (p.x == list.get(list.size()-1).x && p.y == list.get(list.size()-1).y) {
				return true;
			}
			for (Position next : list) {
				int distance = Math.abs(p.x - next.x) + Math.abs(p.y - next.y);
				
				if (distance <= 1000 && !visited[list.indexOf(next)]) {
					q.add(next);
					visited[list.indexOf(next)] = true;
					break;
				}
			}
		}
		return false;
	}
	static class Position {
		int x, y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		
	}
}
