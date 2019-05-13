package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1948 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuffer sf = new StringBuffer();

		for (int k = 0; k < T; k++) {

			st = new StringTokenizer(br.readLine());

			int fm = Integer.parseInt(st.nextToken());
			int fd = Integer.parseInt(st.nextToken());
			int sm = Integer.parseInt(st.nextToken());
			int sd = Integer.parseInt(st.nextToken());
			
			int[] day_per_month = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
			
			int result = 0;
			if (fm == sm ) {
				result = sd - fd + 1;
				
			} else {
				result = 0;
				for (int i = fm-1; i <= sm-1; i++) {
					result += day_per_month[i];
				}
				result -= fd;
				result -= day_per_month[sm-1] - sd;
				result += 1;
				
			}
			sf.append("#" + (k+1) + " " + result + "\n");
		}
		System.out.println(sf.toString());
		
	}
}
