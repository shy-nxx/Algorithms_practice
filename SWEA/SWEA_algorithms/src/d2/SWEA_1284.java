package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1284 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			//9 100 20 3 10
			//8 300 100 10 250
			
			int min = W * P; //90 2000
			
			int result = 0;
			if (W <= R) {
				min = Math.min(min, Q);
			} else {
				result = (W-R)*S + Q;
				min = Math.min(min, result);
			}
			
			sf.append("#" + tc + " " + min + "\n");
		}
		System.out.println(sf.toString());
	}
}
