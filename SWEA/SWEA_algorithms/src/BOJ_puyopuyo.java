import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_puyopuyo {
	static final int N = 12, M = 6;
	
	static char[][] map = new char[N][M];
	static boolean[][] visited = new boolean[N][M];
	
	static int ans = 0;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	static ArrayList<Point> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		for (int i = 0; i < N; i++ ){
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		boolean flag = false;
		while(true) {
			flag = false;
			visited = new boolean[N][M];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] != '.' && !visited[i][j]) {
						list = new ArrayList<>();
						dfs(i, j, map[i][j]);
						
						if (list.size() >= 4) {
							//터트리기 
							flag= true;
							
							for (Point p : list)  { //4개가 다 모이면 터트림 
								map[p.x][p.y] = '.';
							}
						}
					}
				}
			}
			
			if (!flag) {
				break;
			} else {
				ans++;
			}
			pangpang();
			
		}
		System.out.println(ans);
		
		
	}
	static void dfs(int x, int y, char ch) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;
			
			if (map[nx][ny] == ch) {
				list.add(new Point(nx, ny));
				visited[nx][ny] = true;
				dfs(nx, ny, map[nx][ny]);
			}
		}
	}
	
	static void pangpang() {
		for (int i = 0; i < M; i++) {
			for (int j = N-2; j >= 0; j--) {
				for (int k = N-1; k > j; k--) {
					if (map[j][i] != '.' && map[k][i] == '.') {
						map[k][i] = map[j][i];
						map[j][i] = '.';
						break;
					}
				}
			}
		}
	}
	static class Point {
		int x,y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;

		}
		
		
	}
}
