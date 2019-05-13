package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1962 {
	static int N;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();

		for (int k = 0; k < T; k++) {
			sf.append("#" + (k+1) + "\n");
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			 
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			/**
			 * 90도 회전 -> 오른쪽으로 2칸씩 (만약 N이라면 칸을 바꿔야한다. )
			 * 
			 */
			int[][] map_90 = new int[N][N];
			int[][] map_180 = new int[N][N];
			int[][] map_270 = new int[N][N];
			
			rotate(1, map, map_90);
			rotate(2, map, map_180);
			rotate(3, map, map_270);
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sf.append(map_90[i][j]);
					
				}
				sf.append(" ");
				for (int j = 0; j < N; j++) {
					sf.append(map_180[i][j]);
				}
				sf.append(" ");
				for (int j = 0; j < N; j++) {
					sf.append(map_270[i][j]);
				}
				sf.append("\n");
			}
			
		}
		System.out.println(sf.toString());
	}
	static void rotate(int dir, int[][] map, int[][] map_rotate) {
		if (dir == 1) {
			for (int col = 0; col < N; col++) {
				for (int row = 0; row < N; row++) {
					map_rotate[col][row] = map[N-row-1][col]; 
				}
			}
		}
		else if (dir == 2) {
			for (int col = 0; col < N; col++) {
				for (int row = 0; row < N; row++) {
					map_rotate[col][row] = map[N-col-1][N-row-1]; 
				}
			}
		}
		else {
			for (int col = 0; col < N; col++) {
				for (int row = 0; row < N; row++) {
					map_rotate[col][row] = map[row][N-col-1]; 
				}
			}
		}
	}
}
