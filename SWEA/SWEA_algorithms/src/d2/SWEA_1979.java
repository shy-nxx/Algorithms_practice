package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1979 {
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};

	static int[][] map;
	static boolean[][] visited;
	static int N, K;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T =  Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();
		for (int k=0; k < T; k++) {
			sf.append("#" + (k+1) + " ");
			st = new StringTokenizer(br.readLine());

			N =  Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken()); //단어의 길이 

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); //1은 흰색 / 0은 검은색 
				}
			}

			visited = new boolean[N][N];
			ans = 0;
			/**
			 * 가로 세로 길이가 K이면 단어가 들어갈 수 있다. 
			 */
			//			for (int i = 0; i < N; i++) {
			//				for (int j = 0; j < N; j++) {
			//					if (!visited[i][j] && map[i][j] == 1)
			//						dfs(i,j,N, 0);
			//				}
			//			}

			for (int i = 0; i < N; i++) {
				int j = 0;
				int count = 0;
				while(true) {
					if (map[i][j] != 1) { //막힌 곳을 만나면 
						
						if (count == K) ans++;
						count = 0;
						j++;
					}
					else { //블랭크를 만나면 
						if (i == 2) {
//							System.out.println(i + " " + j);
						}
						count++;
						j++;
					}
					if (j == N) { //끝까지 왔다면 
						if (count == K) ans++;
						break;
					}
				}
			}
			//세로 체크 
			for (int i = 0; i < N; i++) {
				int j = 0;
				int count = 0;
				while(true) {
					
					if (map[j][i] != 1) {
						if (count == K) ans++;
						count = 0;
						j++;
					}
					else  {
						count++;
						j++;
					}
					if (j == N) {
						if (count == K) ans++;
						break;
					}
				}
			}
			sf.append(ans + "\n");

		}
		System.out.println(sf.toString());
	}
	//	static void dfs(int x,int y, int length, int count) {
	//		
	//		if (count == 3) {
	//			ans++;
	//			return;
	//		}
	//		
	//		visited[x][y] = true;
	//		
	//		for (int i = 0; i < 4; i++) {
	//			int nx = x + dx[i];
	//			int ny = y + dy[i];
	//			
	//			if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;
	//			
	//			if (map[nx][ny] == 0) 
	//				break;
	//			
	//			
	//			
	//			visited[nx][ny] = true;
	//			dfs(nx, ny, count + 1);
	//			
	//		}	
	//	}

}
