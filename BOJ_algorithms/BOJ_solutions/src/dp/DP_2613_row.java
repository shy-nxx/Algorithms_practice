package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_2613_row {

	public static void main(String[] args) throws IOException {
		/**
		 * (←, ↖, ↑의 인덱스 값 중 최소) + 1이 (i, j)에서 만들 수 있는 가장 큰 정사각형 변의 길이라는 것을 알아내는 것이 핵심이였던 문제였습니다.
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		 
		int[] childs = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			childs[i] = Integer.parseInt(st.nextToken());
		}
		
		/**
		 * 스택 or 큐? 
		 * dp[N] = i번째 아이를 옮긴 횟수? 
		 * 
		 * 오름차순하기 위해서 몇개의 원소를 옮겨야 하는가 -> 일단 맨 뒤의 아이를 가장 가까운 곳으로 옮긴다. 
		 * 이분 탐색? 
		 * 퀵 소트에서처럼 하나를 잡고 그 자리에 가져다놓으면?
		 * 
		 */
		int[] dp = new int[N+1];
		int max = 0;
		
		for (int i = 2; i <= N; i++)  {
			int min = 0;
			for (int j = 1; j < i; j++ ) {
				if (childs[j] > childs[i]) {
					if (min < dp[j]) min = dp[j];
				}
			}
			dp[i] = min + 1;
		}
		
		System.out.println(N - dp[N]);
	}
}
