package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1954 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();

		for (int k = 0; k < T; k++) {
			sf.append("#" + (k+1) + "\n");
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());

			int[][] map = new int[N][N];

			int index = 0;
			int i = 1;
			int row, col;
			for (int j =	N; j > 0; j-=2) {

				/**
				 * 3 -> 2 -> 2 -> 1
				 * 1,1 -> 1,2 -> 1,3 -> 2,3 -> 3,3 -> 3,2 -> 3,1->2,1 -> 2,2
				 */
				for (row =0; row < j; row++) { //->
					map[index][index+row] = i++;
				}
				for (col = 1; col < j; col++) { //오른쪽 아래 
					map[index + col][N-index-1] = i++;
				}
				for (row =1; row < j; row++) { //<-
					map[N-index-1][N-index-row-1] = i++;
				}
				for (col = 1; col < j-1; col++) { //왼쪽 위 
					map[N-index-col-1][index] = i++;
				}
				index++;
			}
			
			for (int z = 0; z < N; z++) {
				for (int j = 0; j < N; j++) {
					sf.append(map[z][j] + " ");
				}
				sf.append("\n");
			}
		}
		System.out.println(sf.toString());
		
	}
}
