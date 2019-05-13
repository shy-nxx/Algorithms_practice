package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1926_easy_369_game {
	/**
	 * 10:20 ~ 40
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		for (int i =1; i <= N; i++) {
			int a = i / 10, b = i % 10;
			if (a == 3 || a == 6 || a == 9) {
				if (b == 3 || b == 6 || b == 9) {
					System.out.print("-- ");
					continue;
				}
				System.out.print("- ");
			}
			else if (b == 3 || b == 6 || b == 9)
				System.out.print("- ");
			else {
				System.out.print(i + " ");
			}
		}

	}
}
