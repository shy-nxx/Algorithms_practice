package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1961 {
	//숫자 배열 회전 
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
			 * 90도 회전 -> 180도 -> 270도 (90도씩 계속 회전한것과같다.)
			 * 
			 * 
			 */
			
			int[][] map_rotate_90 = map_rotate(map);
			int[][] map_rotate_180 = map_rotate(map_rotate_90);
			int[][] map_rotate_270 = map_rotate(map_rotate_180);
			
			for (int i = 0; i < N; i++) { 
				for (int j = 0; j < N; j++) {
					sf.append(map_rotate_90[i][j]);
				}
				sf.append(" ");
				for (int j = 0; j < N; j++) {
					sf.append(map_rotate_180[i][j]);
				}
				sf.append(" ");
				for (int j = 0; j < N; j++) {
					sf.append(map_rotate_270[i][j]);
				}
				sf.append("\n");
			}
		}
		System.out.println(sf.toString());
	}
	static int[][] map_rotate(int[][] map) {
		int[][] temp = new int[N][N];
		
		for (int col = 0; col < N; col++) {
			for (int row = 0; row < N; row++ ) {
				temp[col][row] = map[N-row-1][col];
			}
		}
		return temp;
	}
}
