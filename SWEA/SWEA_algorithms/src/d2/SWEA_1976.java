package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1976 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		StringBuffer sf = new StringBuffer();
		for (int k=0; k < T; k++) {
			sf.append("#" + (k+1) + " ");
			st = new StringTokenizer(br.readLine());
			
			int H1 = Integer.parseInt(st.nextToken());
			int M1 = Integer.parseInt(st.nextToken());
			int H2 = Integer.parseInt(st.nextToken());
			int M2 = Integer.parseInt(st.nextToken());
			
//			if (!(1 <= H1 && H1 <= 12) || !(1 <= H2 && H2 <= 12) || !(0 <= M1 && M1 <= 59) || !(0 <= M2 && M2 <= 59)) {
//				System.out.println("input error");
//			}
			
			int H, M;
			if (H1 + H2 <= 12) {
				H = (H1+H2);
			}
			else {
				H = (H1+H2 - 12);
			}
			
			if (M1 + M2 < 60) {
				M = (M1+M2);
			}
			else {
				M = (M1+M2) - 60;
				H++;
			}
			sf.append(H + " " + M + "\n");
		}
		System.out.println(sf.toString());
	}
	
}
