package sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1949 {
	static int N, K;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static int MAX = 0;
//등산로조성 
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();

		for (int k = 0; k < T; k++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			visited = new boolean[N][N];
			
			int max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(map[i][j], max);
				}
			}
			
			/**
			 * 자신보다 낮은 곳으로 등산로를 조성해야 한다. -> 높은 곳에서 낮은 곳으로 나아가면서 K개를 깍으면 더 멀 수 있는 길이를 찾는다. 
			 * 각 지점을 봉우리로 간주한 다음 자신보다 낮은 곳으로 돈다?
			 * 맵을 복사해야하나? 
			 * 
			 * 1. dfs로 모든 경우에 대해 탐색
2. 탐색할때 k만큼 깍는 작업을 했는지 안했는지에 따라 분류
3. k만큼 깍는 작업을 하고 넘어 왔을경우, 이전 상태를 알려주는 pre 매개변수를 통해 넘어온 지점이 얼마로 깍여 왔는지에 대해 알 수 있고 pre 변수로 다음 지점과 비교하여 탐색.
4. 최대값 찾는다.
			 */
			
			MAX = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == max) {
						dfs(i, j, 1, map[i][j], false);
					}
				}
			}
			sf.append(MAX + "\n");
		}
		System.out.println(sf.toString());
	}
	static void dfs(int x, int y, int cnt, int pre_v, boolean flag) {
		visited[x][y] = true;
		
		MAX = Math.max(MAX, cnt);
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;
			
			//아직 안 깎음 
			if (!flag) {
				//내리막길 -> 깍을 필요 없다. 
				if (map[nx][ny] < map[x][y]) {
					dfs(nx, ny, cnt+1, map[nx][ny], false);
					
				} else { //내리막길 아님 -> 만약다음에 오는 위치 - K만큼 깎은 값이 현재 위치보다 작은 경우 현재 위치 -1만큼 깎아준다. 
					if (map[nx][ny] - K < map[x][y]) {
						dfs(nx, ny,cnt+1, map[x][y] - 1, true);
					}
				}
			} else { //이미 깎음 
				if (pre_v > map[nx][ny]) { //pre ->이미 깎인 경우 얼마나 깎였는지 알려주고 다음 지점과 비교하여 탐색한다.
					dfs(nx, ny, cnt+1, map[nx][ny], true);
				}
			}
			
		}
		visited[x][y] = false;
	}
}
