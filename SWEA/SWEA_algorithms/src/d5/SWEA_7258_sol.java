package d5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7258_sol {
	static int R, C;
	
	static char[][] map;
	static boolean[][][][] visited;
	
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0,};
	
	static int memory;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();

		for (int k = 0; k < T; k++) {

			st = new StringTokenizer(br.readLine());

			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new char[R+1][C+1];
			
			for (int i = 1; i <= R; i++) {
				String s = br.readLine();
				for (int j = 1; j <= C; j++) {
					map[i][j] = s.charAt(j-1);
				}
			}
			
			visited = new boolean[R+1][C+1][4][16];
			
			memory = 0;
			sf.append("#" + (k+1) + " ");
			if (dfs(1,1,0)) {
				sf.append("YES\n");
			} else {
				sf.append("NO\n");
			}
		}
		System.out.println(sf.toString());
	}
	static boolean dfs(int x, int y, int dir) {
		
		if ('0' <= map[x][y] && map[x][y] <= '9' ) memory = map[x][y] - 48;
		else if (map[x][y] == '>' || map[x][y] == '_' && memory == 0) dir = 0;
		else if (map[x][y] == '<' || map[x][y] == '_' && memory != 0) dir = 1;
		else if (map[x][y] == 'v' || map[x][y] == '|' && memory == 0) dir = 2;
		else if (map[x][y] == '^' || map[x][y] == '|' && memory != 0) dir = 3;
		else if (map[x][y] == '+' ) memory = (memory+1) % 15;
		else if (map[x][y] == '-' && memory == 0) memory = 15;
		else if (map[x][y] == '-') memory -=1;
		else if (map[x][y] == '@') return true;
		
		if (visited[x][y][dir][memory]) return false; //한 번 지나간 길은 다시 지나지 않음 -> 무한 루프 방지 
		visited[x][y][dir][memory] = true;
		
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		
		if (nx == 0) nx = R; // 양 끝단에 다다르면 다른 방향으로 간다. 
		else if (nx == R+1) nx = 0;
		
		if (ny == 0) ny = C;
		else if (ny == C+1) ny = 0;
		
		if (map[x][y] == '?') { //네 방향 모두 탐색해줌 
			for (int i = 0; i < 4; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				
				if (nx == 0) nx = R;
				else if (nx == R+1) nx = 0;
				
				if (ny == 0) ny = C;
				else if (ny == C+1) ny = 0;
				
				return dfs(nx, ny, i);
				
			}
		}
		else //?이 아닌 경우에는 위에서 설정한 규칙에 맞게 탐색한다. 
			return dfs(nx, ny, dir);
		
		return false;
		
		
	}
}
