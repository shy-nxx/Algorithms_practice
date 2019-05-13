package sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2105 {
	/**
	 * 조건 
	 * 같은 수의 디저트는 안됨 
	 * 같은 곳을 또 들리면 안됨 (근데 출발 지점으로 되돌아와야 한다. (visited[][] 백트래킹)
	 * 대각선으로 움직여햐 하고 
	 * 최대 개수의 디저트를 먹어야 한다. 
	 * 
	 */
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static boolean[] check;//같은 수의 디저트를 팔고 있는 가게는 스루해야함 

	//ㅅㅣ계 -> 반시  -> 항상 돌아올 때는 3번 꺾어서 돌아와야지 사각형이 만들어진다. 
	static int[] dx = {1,1,-1,-1};
	static int[] dy = {1,-1,-1,1};

	static int sx, sy;

	static int max = 0;

	static StringBuffer sf = new StringBuffer();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T =  Integer.parseInt(st.nextToken());

		for (int k=0; k < T; k++) {
			max = 0;
			sf.append("#" + (k+1) + " ");
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			check = new boolean[101]; //같은 수의 디저트는 먹으면 안됨 

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}


			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 1; j < N-1; j++) {
					visited[i][j] = true;
					check[map[i][j]] = true; //같은 수의 디저트 가게는 안들려야 함. 
					sx = i; sy = j;
					dfs(i, j, 0, 0);
					visited[i][j] = false;
					check[map[i][j]] = false; //백트래킹 

				}
			}
			if (max == 0) {
				sf.append(-1 + "\n");
			}
			else {
				sf.append(max + "\n");
			}
		}
		System.out.println(sf.toString());
	}

	/**
	 * 방향이 이상하게 가고있다. (지그재그..)-> 사이클 형성 검사.
	 * 
	 */
	static void dfs(int x, int y, int dir, int cnt) {
		if (dir == 4) { //방향 전환 4번이면 사이클 형성됨 
			if (x == sx && y == sy && cnt > 1 ) { 
				max = Math.max(max, cnt);
			}
			return;
		}
		
		for (int i = 0; i < 2; i++) { //방향은 계속 나아가는 방향과 90도 회전방향 2개밖에없기 때문에,
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (nx >= 0 && nx < N && ny >= 0 && ny < N && !check[map[nx][ny]] && !visited[nx][ny]) {
				check[map[nx][ny]] = true;
				visited[nx][ny] = true;

				dfs(nx, ny, dir+i, cnt+1);
				visited[nx][ny] = false;
				check[map[nx][ny]] = false;

			}
			else if (nx == sx && ny == sy) {
				dfs(nx, ny, dir+i, cnt+1);
			}
			
		}
		

	}
}
