package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1288 {
	static int VISIT = 1 << 10;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());

			int[] visited = new int[10];

			int result = 1;
			boolean flag = false;
			while(true) {

				int temp = N*result;

				while(temp > 0) {
					for (int i = 0; i <= 9; i++) {
						visited[temp%10] |= 1<<temp%10;
					}

					temp /= 10;
				}

				for (int j = 0; j <= 9; j++) {

					if( visited[j] == 0) {
						flag = false;
						break;
					}

					flag = true;
				}


				if (flag) break;
				result++;
			}

			sf.append("#" + tc + " " + result * N + "\n");
		}
		System.out.println(sf.toString());
	}
}
