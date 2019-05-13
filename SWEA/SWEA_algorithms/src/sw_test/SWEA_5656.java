package sw_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5656 {
	static int N, H, W;

	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};

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
			N = Integer.parseInt(st.nextToken()); //구슬의 개수 
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			int[][] matrix = new int[H][W];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			MIN = Integer.MAX_VALUE;
			
			bizDown(0,matrix);

			sf.append(MIN + "\n");

		}
		System.out.println(sf.toString());
	}
	static void bizDown(int count, int[][] matrix) {
		int[][] copyMap= new int[H][W];
		
		if (count == N) { // 구슬 다 씀 
//			printMap(matrix);
			
			int ans = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (matrix[i][j] != 0) ans++;
				}
			}
			MIN = Math.min(MIN, ans);
//			System.out.println(ans);
			return;
		}
		
		for (int i = 0; i < W; i++) {//구슬 떨어뜨릴 곳알아보기 
			copyMap(copyMap, matrix);
			
			for (int j = 0; j < H; j++) {
				if (copyMap[j][i] != 0) {
					if (copyMap[j][i] == 1) { //1개면 그냥 혼자만 터짐 
						copyMap[j][i] = 0;
						break;
					}
					else {
						boolean[][] visited = new boolean[H][W];
						copyMap = bomb(j, i, copyMap[j][i], copyMap, visited);
						break;
					}
				}
			}
			pangpang(copyMap);
			
			bizDown(count + 1, copyMap);
		}
		
	}
	
	static void pangpang(int[][] copyMap) {
		Queue<Integer> q = new LinkedList<>();
		
		for (int i = 0; i < W; i++ ){
			for (int j = H-1; j >= 0; j--) {
				if (copyMap[j][i] != 0) {
					q.add(copyMap[j][i]);
				}
				copyMap[j][i] = 0;
			}
			int n = q.size();
			for (int j = H-1; j >= H - n; j--) {
				copyMap[j][i] = q.poll();
			}
		}
		
	}
	static int[][] bomb(int x, int y, int value, int[][] c_map, boolean[][] visit) {
		c_map[x][y] = 0;
		
		//left
		for (int i = value; i >= y-value && i >= 0; i--) {
			if (c_map[x][i] == 1) {
				c_map[x][i] = 0;
				
			}
			else if (c_map[x][i] > 1) {
				visit[x][i] = true;//0으로 만들어버리면 수를 유지할 수 없으므로 visit만 표시한다. 
			}
		}
		
		//right
		for (int i = y; i <= y + value && i < W; i++) {
			if (c_map[x][i] == 1) {
				c_map[x][i] = 0;
				
			}
			else if (c_map[x][i] > 1) {
				visit[x][i] = true;
			}
		}
		
		//up
		for (int i = value; i >= x - value && i >= 0; i--) {
			if (c_map[i][y] == 1) {
				c_map[i][y] = 0;
			} else if (c_map[i][y] > 1) {
				visit[i][y] = true;
			}
		}
		
		//down
		for (int i = x; i <= x + value && i < H; i++) {
			if (c_map[i][y] == 1) {
				c_map[i][y] = 0;
			}
			else if (c_map[i][y] > 1) {
				visit[i][y] = true; 
			}
		}
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (visit[i][j]) { //더 터트릴 곳이 있으면 터트린다. 
					visit[i][j] = false;
					bomb(i, j, c_map[i][j], c_map, visit);
				}
			}
		}
		return c_map;
	}
	static void copyMap(int[][] copyMap, int[][] matrix) {
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				copyMap[i][j] = matrix[i][j];
			}
		}
	}
	static void printMap(int[][] matrix) {
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
