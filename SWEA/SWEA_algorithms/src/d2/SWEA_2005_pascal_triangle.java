package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2005_pascal_triangle {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		
		for (int k = 0; k < T; k++) {
			st= new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			
			int[][] triangle = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= i; j++) {
					if (i == j) {
						triangle[i][j] = 1;
					}
					else if (j == 0) {
						triangle[i][j] = 1;
					}
					else {
						triangle[i][j] = triangle[i-1][j] + triangle[i-1][j-1];
					}
				}
			}
			System.out.println("#" + (k+1));
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= i; j++) {
					System.out.print(triangle[i][j] + " ");
				}
				System.out.println();
			}
			
		}
	}
}
