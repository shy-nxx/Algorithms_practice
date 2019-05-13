package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1974 {
	static final int R = 10, C = 10;
	static int[][] map;
	static boolean[] number;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		StringBuffer sf = new StringBuffer();

		int T = Integer.parseInt(st.nextToken());

		for (int k = 0; k < T; k++) {
			sf.append("#" + (k+1) + " ");

			map = new int[R][C];

			for (int i = 1; i < R; i++ ) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1;  j < C; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int ans = 1;

			for (int i = 1; i < R; i++) {
				flag = true;
				number = new boolean[10]; //1부터 9까지 있는 지 확인 (중복되면 false로 만든다. )
				for (int j = 1; j < C; j++) {
					if (number[map[i][j]]) {
						flag= false;
						break;
					}
					else {
						number[map[i][j]] = true;
					}
				}
				if (!flag) break;

			}
			if (flag) {
				ans = 1;
			}
			else {
				ans = 0;
			}

			if (ans == 0) {
				sf.append(ans + "\n");
				continue;
			}
			
			for (int i = 1; i < R; i++) {
				flag = true;
				number = new boolean[10]; //1부터 9까지 있는 지 확인 (중복되면 false로 만든다. )
				for (int j = 1; j < C; j++) {
					if (number[map[j][i]]) {
						flag= false;
						break;
					}
					else {
						number[map[j][i]] = true;
					}
				}
				if (!flag) break;

			}
			if (flag) {
				ans = 1;
			}
			else {
				ans = 0;
			}

			if (ans == 0) {
				sf.append(ans + "\n");
				continue;
			}

			int cnt = 0;
			flag = true;
			boolean[][] check = new boolean[3][10];
			for (int i = 1; i < R; i++) {
				if ((i-1) % 3 == 0) {
					check = new boolean[3][10];
				}
				for (int j = 1; j < C; j++) {

					int n = j/3;
					if (j % 3 == 0) {
						n--;
					}

					if (check[n][map[i][j]]) flag = false;
					else {
						check[n][map[i][j]] = true;
					}

					
				}
				if (!flag) break;

			}
			if (flag) {
				ans = 1;
			}
			else {
				ans = 0;
			}

			sf.append(ans + "\n");
		}
		System.out.println(sf.toString());
	}
}
