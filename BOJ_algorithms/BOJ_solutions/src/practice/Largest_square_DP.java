package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Largest_square_DP {
	public static void main(String[] args) throws IOException {
		/**
		 * (←, ↖, ↑의 인덱스 값 중 최소) + 1이 (i, j)에서 만들 수 있는 가장 큰 정사각형 변의 길이라는 것을 알아내는 것이 핵심이였던 문제였습니다.
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		
		int[][] dp = new int[N][M];
		
		for (int i = 0; i< N; i++) {
			String s = br.readLine();
			for (int j= 0; j < M; j++) {
				map[i][j ] = s.charAt(j) - 48;
			}
		}
		
		int max = 0;
		
		for (int i = 0; i <N; i++) {
			for (int j = 0; j < M; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = map[i][j];
					
				}
				
				else if (map[i][j] == 1) {
					dp[i][j] = Math.min(dp[i-1][j-1] , Math.min(dp[i-1][j], map[i][j-1])) + map[i][j];
				}
				max = Math.max(dp[i][j], max);
			}
			
		}
		
		System.out.println(max * max);
	}
	
}
