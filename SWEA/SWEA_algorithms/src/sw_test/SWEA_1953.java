package sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953 {
	static int N, M, R, C, L;
	static int[][] map;

	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	static boolean[][] visited;

	static final int up = 0;
	static final int down = 1;
	static final int left = 2;
	static final int right = 3;
	
	static StringBuffer sf = new StringBuffer();
	/**
	 * 2. 지하 터널 지도의 세로 크기 N, 가로 크기 M은 각각 5 이상 50 이하이다. (5 ≤ N, M ≤ 50)

3. 맨홀 뚜껑의 세로 위치 R 은 0 이상 N-1이하이고 가로 위치 C 는 0 이상 M-1이하이다. (0 ≤ R ≤ N-1, 0 ≤ C ≤ M-1)

4. 탈출 후 소요된 시간 L은 1 이상 20 이하이다. (1 ≤ L ≤ 20)

5. 지하 터널 지도에는 반드시 1개 이상의 터널이 있음이 보장된다.

6. 맨홀 뚜껑은 항상 터널이 있는 위치에 존재한다.
	 */

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T =  Integer.parseInt(st.nextToken());

		
		for (int k=0; k < T; k++) {
			sf.append("#" + (k+1) + " ");
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			visited = new boolean[N][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			bfs();

		}
		System.out.println(sf.toString());

	}
	static void bfs() {
		Queue<Position> q = new LinkedList<>();

		q.add(new Position(R,C));

		visited[R][C] = true;

		int time = 0;

		while (!q.isEmpty()) {
			
			time++;

			if (time == L) break;

			int n = q.size();

			for (int j = 0; j < n; j++) {
				Position p = q.poll();
				
				int x = p.x;
				int y = p.y;


				boolean dir[] = new boolean[4]; //상하좌우
				
				//현재 위치에서 갈 수 있는 곳 확인하기 
				
				switch(map[x][y]) {
				case 1: 
					Arrays.fill(dir, true);
					break;
				case 2:
					dir[up] = true;
					dir[down] = true;
					break;

				case 3:
					dir[left] = true;
					dir[right] = true;
					break;

				case 4:
					dir[up] = true;
					dir[right] = true;
					break;

				case 5:
					dir[down] = true;
					dir[right] = true;
					break;

				case 6:
					dir[down] = true;
					dir[left] = true;
					break;

				case 7:
					dir[up] = true;
					dir[left] = true;
					break;
					
				}
				
				/* 각 맨홀마다 갈 수 없는 곳이 있다.그것만 제한해주면 될 것 같은.ㄷ..
				 * 1번 -> 없음 
				 * 2번 -> 좌우
				 * 3번 -> 상하
				 * 4번 -> 하좌
				 * 5번 -> 상좌
				 * 6번 -> 상우
				 * 7번 -> 하우 
				 * 
				 * 2번의 경우에는 옆에 나오는 것은 아무것도 못간다. (위 - 5번, 6번 가능 / 아래 나머지 )
				 * 3번의 경우에는 위 아래에 있는 것 아무것도 못 간다. (오른쪽 - 4,5/왼 - 6,7)
				 * 4번의 경우에는 왼쪽, 아래쪽은 못 간다.
				 * 5번 - 위,왼쪽 
				 * 6번 - 위, 오른쪽
				 * 7번 - 아래 오른쪽 
				 */
				//동서북남 
				for (int i = 0; i < dx.length; i++) {
					if (!dir[i]) continue; //현재 위치에서 갈 수 없는 곳 판단 
					
					int nx = x + dx[i];
					int ny = y + dy[i];

					if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;
					
					if (map[nx][ny] == 0) continue;

					if (checkThrough(nx, ny, i)) {
						q.add(new Position(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}
		}
		
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j])
					count++;
			}
		}
		sf.append(count + "\n");
		

	}

	/**
	 *  * 2번의 경우에는 옆에 나오는 것은 아무것도 못간다. (위 - 5번, 6번 가능 / 아래 나머지 )
	 * 3번의 경우에는 위 아래에 있는 것 아무것도 못 간다. (오른쪽 - 4,5/왼 - 6,7) 
	 * 4번의 경우에는 왼쪽, 아래쪽은 못 간다. - 오른쪽 : 1,3,6,7/ 
	 * 5번 - 위,왼쪽 - 오른쪽 : 1,3,6,7/
	 * 6번 - 위, 오른쪽 -오른쪽 X / 
	 * 7번 - 아래 오른쪽 -오른쪽 X / 
	 * 
	 */
	static boolean checkThrough(int nx, int ny, int dir) {
		if (dir == right) { //동 -> 이미이전 노드는 오른쪽으로 올 수 있는 상황이다. 
			if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7 ) return true;
			
		} else if (dir == left) { //서
			if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5) return true;
			
		} else if (dir == up) {
			//북
			if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6) return true;
			

		} else if (dir == down){
			if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7) return true;
			
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
