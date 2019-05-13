package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BFS_9205_walking_with_beer {

	public static void main(String[] args) throws IOException {
		/**
		 * (←, ↖, ↑의 인덱스 값 중 최소) + 1이 (i, j)에서 만들 수 있는 가장 큰 정사각형 변의 길이라는 것을 알아내는 것이 핵심이였던 문제였습니다.
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		StringBuffer sf = new StringBuffer();
		
		int T = Integer.parseInt(st.nextToken());

		for (int k = 0; k < T; k++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); //편의점의 개수 
		
			ArrayList<Position> list = new ArrayList<>();
			
			for (int i = 1; i <= N+2; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				list.add(new Position(x, y));
				//집(1) ->편의점 -> 페스티벌 (N+2)
				//두 좌표 사이의 거리 x좌표 차 + y 좌표 차 
			}
		
			//0-> 집 / 1~N은 편의점 / N+1은 편의점 
			
			int dp[][] = new int[101][101];
			
			for (int i = 0; i <= N+1; i++) {
				for (int j = 0; j <= N+1; j++) {
					if (i == j) continue;
					
					Position p = list.get(i);
					Position next = list.get(j);
					
					int dist = Math.abs(p.x - next.x) + Math.abs(p.y - next.y);
					
					if (dist <= 1000 ) { //20병으로는 1000미터밖에 못 가니까 
						dp[i][j] = 1;
					}
				}
			}
			
			for (int a = 0; a <= N+1; a++) {
				for (int i = 0; i <= N+1; i++) {
					for (int j = 0; j <= N+1; j++) {
						if (dp[i][a] == 1 && dp[i][a] == dp[a][j]) {
							dp[i][j] = dp[i][a];
						}
					}
				}
			}
			if (dp[0][N+1] == 1) {
				sf.append("happy\n");
			}
			else {
				sf.append("sad\n");
			}
			
		}
		System.out.println(sf.toString());
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
