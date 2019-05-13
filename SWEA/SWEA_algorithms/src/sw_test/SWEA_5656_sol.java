package sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5656_sol {
	//백준 puyopuyo와 비슷 
	static int N, W, H;

	static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		StringBuffer sf = new StringBuffer();

		int T = Integer.parseInt(st.nextToken());

		for (int k = 0; k < T; k++) {
			sf.append("#" + (k+1) + " ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			int[][] map = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] =  Integer.parseInt(st.nextToken());
				}
			}

			MIN = Integer.MAX_VALUE;

			dropPinBall(0,map); // 0 -> 떨어뜨린 구슬의 개수 

			sf.append(MIN + "\n");
		}
		System.out.println(sf.toString());
	}
	
	static void dropPinBall(int count, int[][] map) {
		int[][] c_map = new int[H][W];

		if (count == N) {
			int ans = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (map[i][j] != 0) ans++;
				}
			}
			MIN = Math.min(MIN, ans);
			return;
		}

		for (int i = 0; i < W; i++) {
			copyMap(c_map, map);
			for (int j = 0; j < H; j++) { //깊이 파고들어야 하기 때문에 
				if (c_map[j][i] != 0) {
					if (c_map[j][i] == 1) {
						c_map[j][i] = 0;
						break;
					}
					else {
						boolean[][] visited = new boolean[H][W];
						bomb(j, i, c_map[j][i], c_map, visited );
					}
				}
			}
			pangpang(c_map);
			dropPinBall(count+1, c_map); 
		}
	}
	static void copyMap(int[][] copy_map, int[][] prev_map) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) { //깊이 파고들어야 하기 때문에 
				copy_map[i][j] = prev_map[i][j];

			}
		}
	}

	static void bomb(int x, int y, int value, int[][] c_map, boolean[][] visited) {
		c_map[x][y] = 0; //일단 0으로 만들고 value를 가지고 작업 
		//여기서 오른쪽. 왼쪽, 아래쪽, 위쪽 작업을 끝냄 
		//오른쪽 
		for (int i = y; i < y + value && i < W; i++ ) {
			if (c_map[x][i] == 1) 
				c_map[x][i] = 0;

			else
				visited[x][i] = true; //당장 0으로 만들어버리면 value가 사라지기 때문에 visited로 표시만 해둠 
		}

		//왼쪽  
		for (int i = y; i > y - value && i >= 0; i-- ) {
			if (c_map[x][i] == 1) 
				c_map[x][i] = 0;

			else
				visited[x][i] = true; //당장 0으로 만들어버리면 value가 사라지기 때문에 visited로 표시만 해둠 
		}
		//위쪽  
		for (int i = x; i > x - value && i >= 0; i-- ) {
			if (c_map[i][y] == 1) 
				c_map[i][y] = 0;

			else
				visited[i][y] = true; //당장 0으로 만들어버리면 value가 사라지기 때문에 visited로 표시만 해둠 
		}
		//아래쪽  
		for (int i = x; i < x+ value && i < H; i++ ) {
			if (c_map[i][y] == 1) 
				c_map[i][y] = 0;

			else
				visited[i][y] = true; //당장 0으로 만들어버리면 value가 사라지기 때문에 visited로 표시만 해둠 
		}
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (visited[i][j]) { //value가 1 이상인 것들은 계속 이어서 터져야 함 
					visited[i][j] = false;
					bomb(i, j, c_map[i][j], c_map, visited);
				}
			}
		}
	}
	
	static void pangpang(int[][] c_map) {
		Queue<Integer> q = new LinkedList<>();
		
		for (int i = 0; i < W; i++) {
			for (int j = H-1; j >= 0; j--) {
				if (c_map[j][i] != 0) {
					q.add(c_map[j][i]); //0이 아닌 것들을 세로 순서로 담음 
				}
				c_map[j][i] = 0;
			}
			int n = q.size();
			for (int j = H-1; j >= H - n; j--) {
				c_map[j][i] = q.poll();
			}
		}
		
		
	}
}
